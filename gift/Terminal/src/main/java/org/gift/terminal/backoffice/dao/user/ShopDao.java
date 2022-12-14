package org.gift.terminal.backoffice.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.terminal.backoffice.dao.Dao;
import org.gift.terminal.backoffice.dao.JDBCProcessor;
import org.gift.terminal.backoffice.model.user.ShopEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopDao implements Dao<ShopEntity, Long> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_SHOP = "select * from shop where id = ?";
    private static final String SAVE_SHOP = "insert into shop (brand, description) values (?, ?)";
    private static final String DELETE_SHOP = "delete from shop where id = ?";
    @Override
    public Optional<ShopEntity> get(Long key) {
        var shop = new ShopEntity();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getShopStatement = connection.prepareStatement(GET_SHOP)) {
            getShopStatement.setLong(1, key);
            var res = getShopStatement.executeQuery();
            if (!res.next()) {
                throw new SQLException("No one shop found");
            }
            shop.setId(res.getLong("id"));
            shop.setBrand(res.getString("brand"));
            shop.setDescription(res.getString("description"));
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get shop data from database", ex);
        }
        return Optional.of(shop);
    }

    @Override
    public List<ShopEntity> getAll() {
        //Business logic let only one shop get in user session!
        return new ArrayList<>();
    }

    @Override
    public void save(ShopEntity shopEntity) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement saveShopStatement = connection.prepareStatement(SAVE_SHOP)) {
            saveShopStatement.setString(1, shopEntity.getBrand());
            saveShopStatement.setString(2, shopEntity.getDescription());
            saveShopStatement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Cannot save shop data from database", ex);
        }
    }

    @Override
    public void update(ShopEntity shopEntity, String... params) {
    }

    @Override
    public void delete(ShopEntity shopEntity) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement deleteShopStatement = connection.prepareStatement(DELETE_SHOP)) {
            deleteShopStatement.setLong(1, shopEntity.getId());
            deleteShopStatement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Cannot delete shop data from database", ex);
        }
    }
}
