package org.gift.terminal.backoffice.dao.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.terminal.backoffice.dao.Dao;
import org.gift.terminal.backoffice.dao.JDBCProcessor;
import org.gift.terminal.backoffice.model.item.ChocolateEntity;
import org.gift.terminal.backoffice.model.item.ItemCommonData;
import org.gift.terminal.common.constants.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ChocolateDao implements Dao<ChocolateEntity, Long> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_CHOCOLATE = "select * from chocolate inner join item_common_data icd on icd.id = chocolate.item_common_data_id where chocolate.id = ?";
    private static final String GET_ALL_CHOCOLATE = "select * from chocolate inner join item_common_data icd on icd.id = chocolate.item_common_data_id";
    private static final String GET_ITEM_DATA = "select id from item_common_data order by id desc limit 1";
    private static final String GET_CATEGORY_ID = "select id from item_category where category = ?";
    private static final String GET_COMPONENT_ID = "select id from component order by id desc limit 1";
    private static final String GET_COMPONENTS = "select * from component where id = ?";
    private static final String SAVE_CHOCOLATE = "insert into chocolate (weight, code, item_common_data_id, sugar, chocolate_type, component_id) values (?,?,?,?,?,?)";
    private static final String SAVE_COMPONENTS = "insert into component (name) values (?)";
    private static final String SAVE_ITEM_COMMON = "insert into item_common_data (shop_id, item_category_id, is_available, price, brand, label, description) values (?,?,?,?,?,?,?)";
    private static final String DELETE_CHOCOLATE = "delete from chocolate where id = ?";

    @Override
    public Optional<ChocolateEntity> get(Long key) {
        var chocolate = new ChocolateEntity();
        var itemCommon = new ItemCommonData();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getChocolateStatement = connection.prepareStatement(GET_CHOCOLATE);
             PreparedStatement getComponentsStatement = connection.prepareStatement(GET_COMPONENTS)) {
            getChocolateStatement.setLong(1, key);
            var res = getChocolateStatement.executeQuery();
            if (!res.next()) {
                throw new SQLException("No chocolate found");
            }
            chocolate.setId(res.getLong("chocolate.id"));
            chocolate.setWeight(res.getDouble("weight"));
            chocolate.setCode(res.getString("code"));
            chocolate.setSugar(res.getDouble("sugar"));
            chocolate.setChocolateType(res.getString("chocolate_type"));
            getComponentsStatement.setLong(1, res.getLong("component_id"));
            chocolate.setComponents(List.of(getChocolateStatement.executeQuery().getString(2)));
            itemCommon.setId(res.getLong("icd.id"));
            itemCommon.setShopId(res.getLong("shop_id"));
            itemCommon.setCategory(ItemCategory.CHOCOLATE);
            itemCommon.setAvailable(res.getBoolean("is_available"));
            itemCommon.setPrice(res.getDouble("price"));
            itemCommon.setBrand(res.getString("brand"));
            itemCommon.setLabel(res.getString("label"));
            itemCommon.setDescription(res.getString("description"));
            chocolate.setItemData(itemCommon);
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get chocolate item data from database", ex);
        }
        return Optional.of(chocolate);
    }

    @Override
    public List<ChocolateEntity> getAll() {
        var list = new LinkedList<ChocolateEntity>();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getChocolateStatement = connection.prepareStatement(GET_ALL_CHOCOLATE);
             PreparedStatement getComponentsStatement = connection.prepareStatement(GET_COMPONENTS)) {
            var res = getChocolateStatement.executeQuery();
            while (res.next()) {
                var chocolate = new ChocolateEntity();
                var itemCommon = new ItemCommonData();
                chocolate.setId(res.getLong("chocolate.id"));
                chocolate.setWeight(res.getDouble("weight"));
                chocolate.setCode(res.getString("code"));
                chocolate.setSugar(res.getDouble("sugar"));
                chocolate.setChocolateType(res.getString("chocolate_type"));
                getComponentsStatement.setLong(1, res.getLong("component_id"));
                chocolate.setComponents(List.of(getChocolateStatement.executeQuery().getString(2)));
                itemCommon.setId(res.getLong("icd.id"));
                itemCommon.setShopId(res.getLong("shop_id"));
                itemCommon.setCategory(ItemCategory.CHOCOLATE);
                itemCommon.setAvailable(res.getBoolean("is_available"));
                itemCommon.setPrice(res.getDouble("price"));
                itemCommon.setBrand(res.getString("brand"));
                itemCommon.setLabel(res.getString("label"));
                itemCommon.setDescription(res.getString("description"));
                chocolate.setItemData(itemCommon);
                list.add(chocolate);
            }
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get all chocolate item data from database", ex);
        }
        return list;
    }

    @Override
    public void save(ChocolateEntity chocolateEntity) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement saveChocolateStatement = connection.prepareStatement(SAVE_CHOCOLATE);
             PreparedStatement saveItemStatement = connection.prepareStatement(SAVE_ITEM_COMMON);
             PreparedStatement saveComponentsStatement = connection.prepareStatement(SAVE_COMPONENTS);
             PreparedStatement getComponentId = connection.prepareStatement(GET_COMPONENT_ID);
             PreparedStatement getItemId = connection.prepareStatement(GET_ITEM_DATA);
             PreparedStatement getCategoryId = connection.prepareStatement(GET_CATEGORY_ID)) {
            var item = chocolateEntity.getItemData();
            int index = 1;
            saveItemStatement.setLong(index++, item.getShopId());
            getCategoryId.setString(1, item.getCategory().name());
            saveItemStatement.setLong(index++, getCategoryId.executeQuery().getLong(1));
            saveItemStatement.setBoolean(index++, item.isAvailable());
            saveItemStatement.setDouble(index++, item.getPrice());

            saveItemStatement.setString(index++, item.getBrand());

            saveItemStatement.setString(index++, item.getLabel());

            saveItemStatement.setString(index++, item.getDescription());

            saveComponentsStatement.setString(1, chocolateEntity.getComponents().get(0));
            saveChocolateStatement.executeUpdate();
            var res = getItemId.executeQuery();
            if (!res.next()) {
                throw new SQLException("Wrong item common data saved in database");
            }
            var id = res.getLong(1);
            index = 1;
            saveChocolateStatement.setDouble(index++, chocolateEntity.getWeight());

            saveChocolateStatement.setString(index++, chocolateEntity.getCode());
            saveChocolateStatement.setLong(index++, id);
            saveChocolateStatement.setDouble(index++, chocolateEntity.getSugar());
            saveChocolateStatement.setString(index++, chocolateEntity.getChocolateType());
                 var resComp = getComponentId.executeQuery();
            if (!resComp.next()) {
                throw new SQLException("Wrong components data saved in database");
            }
            id = resComp.getLong(1);
            saveChocolateStatement.setLong(index++, id);
            saveChocolateStatement.executeUpdate();
            res.close();
            resComp.close();
        } catch (SQLException ex) {
            LOG.error("Cannot save box item data from database", ex);
        }
    }

    @Override
    public void update(ChocolateEntity chocolateEntity, String... params) {
    }

    @Override
    public void delete(ChocolateEntity chocolateEntity) {
        if (chocolateEntity == null) {
            throw new IllegalArgumentException("item is incorrect");
        }
        try (Connection connection = JDBC.getConnection();
             PreparedStatement deleteChocolateStatement = connection.prepareStatement(DELETE_CHOCOLATE)) {
            deleteChocolateStatement.setLong(1, chocolateEntity.getId());
            deleteChocolateStatement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Cannot delete chocolate item data from database", ex);
        }
    }
}
