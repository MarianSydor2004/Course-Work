package org.gift.terminal.backoffice.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.terminal.backoffice.dao.Dao;
import org.gift.terminal.backoffice.dao.JDBCProcessor;
import org.gift.terminal.backoffice.model.user.UserEntity;
import org.gift.terminal.backoffice.model.user.UserIdentityEntity;
import org.gift.terminal.common.constants.AccessLevel;
import org.gift.terminal.common.constants.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserDao implements Dao<UserEntity, String> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_USER = "select * from user_data inner join access_level al on al.id = user_data.access_level_id inner join user_identity ui on user_data.id = ui.id where user_data.email = ?";
    private static final String GET_ALL_USER = "select * from user_data inner join access_level al on al.id = user_data.access_level_id inner join user_identity ui on user_data.id = ui.id";
    private static final String SAVE_USER = "insert into user_data (is_verified, email, password, access_level_id) values (?, ?, ?, ?)";
    private static final String SAVE_IDENTITY = "insert into user_identity (shop_id, country_id, status) values (?,?,?)";

    private static final String DELETE_USER = "delete from user_data ud where ud.id = ?";

    @Override
    public Optional<UserEntity> get(String key) {
        var user = new UserEntity();
        var identity = new UserIdentityEntity();
        int index = 1;
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getUserStatement = connection.prepareStatement(GET_USER)) {
            getUserStatement.setString(1, key);
            var resUser = getUserStatement.executeQuery();
            if (!resUser.next()) {
                throw new SQLException("User not found");
            }
            user.setId(resUser.getLong(index++));
            user.setVerified(resUser.getBoolean(index++));
            user.setEmail(resUser.getString(index++));
            user.setPassword(resUser.getString(index++));
            user.setAccessLevel(AccessLevel.valueOf(resUser.getString("level")));
            identity.setId(user.getId());
            identity.setShopId(resUser.getLong("shop_id"));
            identity.setCountryId(resUser.getLong("country_id"));
            identity.setUserStatus(UserStatus.valueOf(resUser.getString("status")));
            user.setUserIdentity(identity);
            resUser.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get user data from database", ex);
        }
        return Optional.of(user);
    }

    @Override
    public List<UserEntity> getAll() {
        var list = new ArrayList<UserEntity>();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getUserStatement = connection.prepareStatement(GET_ALL_USER)) {
            var res = getUserStatement.executeQuery();
            while (res.next()) {
                var user = new UserEntity();
                var identity = new UserIdentityEntity();
                int index = 1;
                user.setId(res.getLong(index++));
                user.setVerified(res.getBoolean(index++));
                user.setEmail(res.getString(index++));
                user.setPassword(res.getString(index++));
                user.setAccessLevel(AccessLevel.valueOf(res.getString("level")));
                identity.setId(user.getId());
                identity.setShopId(res.getLong("shop_id"));
                identity.setCountryId(res.getLong("country_id"));
                identity.setUserStatus(UserStatus.valueOf(res.getString("status")));
                user.setUserIdentity(identity);
                list.add(user);
            }
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get all users from database", ex);
        }
        return list;
    }

    @Override
    public void save(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NoSuchElementException("User data is incorrect");
        }
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getAccessLevelByName = connection.prepareStatement("select id from access_level al where al.level = ?");
             PreparedStatement saveUserStatement = connection.prepareStatement(SAVE_USER);
             PreparedStatement saveIdentityStatement = connection.prepareStatement(SAVE_IDENTITY)) {
            int index = 1;
            saveUserStatement.setBoolean(index++, true);
            saveUserStatement.setString(index++, userEntity.getEmail());
            saveUserStatement.setString(index++, userEntity.getPassword());
//            getAccessLevelByName.setString(1, userEntity.getAccessLevel().name());
//            saveUserStatement.setLong(index++, getAccessLevelByName.executeQuery().getLong(1));
            index = 1;
            saveIdentityStatement.setLong(index++, userEntity.getUserIdentity().getShopId());
            saveIdentityStatement.setLong(index++, userEntity.getUserIdentity().getCountryId());
            saveIdentityStatement.setString(index++, userEntity.getUserIdentity().getUserStatus().name());
        } catch (SQLException ex) {
            LOG.error("Cannot delete user from database", ex);
        }
    }

    @Override
    public void update(UserEntity userEntity, String... params) {
    }

    @Override
    public void delete(UserEntity userEntity) {
        if (userEntity == null) {
            throw new NoSuchElementException("User data is incorrect");
        }
        try (Connection connection = JDBC.getConnection();
             PreparedStatement deleteUserStatement = connection.prepareStatement(DELETE_USER)) {
            deleteUserStatement.setLong(1, userEntity.getId());
            deleteUserStatement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Cannot delete user from database", ex);
        }
    }
}
