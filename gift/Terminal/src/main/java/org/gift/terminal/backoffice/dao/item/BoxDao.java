package org.gift.terminal.backoffice.dao.item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gift.terminal.backoffice.dao.Dao;
import org.gift.terminal.backoffice.dao.JDBCProcessor;
import org.gift.terminal.backoffice.model.item.BoxEntity;
import org.gift.terminal.backoffice.model.item.ItemCommonData;
import org.gift.terminal.common.constants.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BoxDao implements Dao<BoxEntity, Long> {
    private static final Logger LOG = LogManager.getLogger();
    private static final JDBCProcessor JDBC = new JDBCProcessor();

    private static final String GET_BOX = "select * from box inner join item_common_data icd on icd.id = box.item_common_data_id where box.id = ?";
    private static final String GET_ALL_BOX = "select * from box inner join item_common_data icd on icd.id = box.item_common_data_id";
    private static final String GET_ITEM_DATA = "select id from item_common_data order by id desc limit 1";
    private static final String GET_CATEGORY_ID = "select id from item_category where category = ?";
    private static final String SAVE_BOX = "insert into box (weight, code, item_common_data_id, color_code, scale, width, length, color) values (?,?,?,?,?,?,?,?)";
    private static final String SAVE_ITEM_COMMON = "insert into item_common_data (shop_id, item_category_id, is_available, price, brand, label, description) values (?,?,?,?,?,?,?)";
    private static final String DELETE_BOX = "delete from box where id = ?";

    @Override
    public Optional<BoxEntity> get(Long key) {
        var box = new BoxEntity();
        var itemCommon = new ItemCommonData();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getBoxStatement = connection.prepareStatement(GET_BOX)) {
            getBoxStatement.setLong(1, key);
            var res = getBoxStatement.executeQuery();
            if (!res.next()) {
                throw new SQLException("No box found");
            }
            box.setId(res.getLong("box.id"));
            box.setWeight(res.getDouble("weight"));
            box.setCode(res.getString("code"));
            box.setColorCode(Integer.parseInt(res.getString("color_code"), 16));
            box.setScale(res.getDouble("scale"));
            box.setWidth(res.getDouble("width"));
            box.setLength(res.getDouble("length"));
            box.setColor(res.getString("color"));
            itemCommon.setId(res.getLong("icd.id"));
            itemCommon.setShopId(res.getLong("shop_id"));
            itemCommon.setCategory(ItemCategory.BOX);
            itemCommon.setAvailable(res.getBoolean("is_available"));
            itemCommon.setPrice(res.getDouble("price"));
            itemCommon.setBrand(res.getString("brand"));
            itemCommon.setLabel(res.getString("label"));
            itemCommon.setDescription(res.getString("description"));
            box.setItemData(itemCommon);
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get box item data from database", ex);
        }
        return Optional.of(box);
    }

    @Override
    public List<BoxEntity> getAll() {
        var list = new LinkedList<BoxEntity>();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement getBoxStatement = connection.prepareStatement(GET_ALL_BOX)) {
            var res = getBoxStatement.executeQuery();
            while (res.next()) {
                var box = new BoxEntity();
                var itemCommon = new ItemCommonData();
                box.setId(res.getLong("box.id"));
                box.setWeight(res.getDouble("weight"));
                box.setCode(res.getString("code"));
                box.setColorCode(Integer.parseInt(res.getString("color_code"), 16));
                box.setScale(res.getDouble("scale"));
                box.setWidth(res.getDouble("width"));
                box.setLength(res.getDouble("length"));
                box.setColor(res.getString("color"));
                itemCommon.setId(res.getLong("icd.id"));
                itemCommon.setShopId(res.getLong("shop_id"));
                itemCommon.setCategory(ItemCategory.BOX);
                itemCommon.setAvailable(res.getBoolean("is_available"));
                itemCommon.setPrice(res.getDouble("price"));
                itemCommon.setBrand(res.getString("brand"));
                itemCommon.setLabel(res.getString("label"));
                itemCommon.setDescription(res.getString("description"));
                box.setItemData(itemCommon);
                list.add(box);
            }
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot get all box item data from database", ex);
        }
        return list;
    }

    @Override
    public void save(BoxEntity boxEntity) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement saveBoxStatement = connection.prepareStatement(SAVE_BOX);
             PreparedStatement saveItemStatement = connection.prepareStatement(SAVE_ITEM_COMMON);
             PreparedStatement getItemId = connection.prepareStatement(GET_ITEM_DATA);
             PreparedStatement getCategoryId = connection.prepareStatement(GET_CATEGORY_ID)) {
            var item = boxEntity.getItemData();
            int index = 1;
            saveItemStatement.setLong(index++, item.getShopId());
//            getCategoryId.setString(1, item.getCategory().name());
//            saveItemStatement.setLong(index++, getCategoryId.executeQuery().getLong(1));
//            saveItemStatement.setBoolean(index++, item.isAvailable());
            saveItemStatement.setDouble(index++, item.getPrice());
            saveItemStatement.setString(index++, item.getBrand());
            saveItemStatement.setString(index++, item.getLabel());
            saveItemStatement.setString(index++, item.getDescription());
            saveItemStatement.executeUpdate();
            var res = getItemId.executeQuery();
            if (!res.next()) {
                throw new SQLException("Wrong item common data saved in database");
            }
            var id = res.getLong(1);
            index = 1;
            saveBoxStatement.setDouble(index++, boxEntity.getWidth());
            saveBoxStatement.setString(index++, boxEntity.getCode());
            saveBoxStatement.setLong(index++, id);
            saveBoxStatement.setString(index++, String.valueOf(boxEntity.getColorCode()));
            saveBoxStatement.setDouble(index++, boxEntity.getScale());
            saveBoxStatement.setDouble(index++, boxEntity.getWidth());
            //     saveBoxStatement.setDouble(index++, boxEntity.getLength());
            saveBoxStatement.setString(index++, boxEntity.getColor());
            saveBoxStatement.executeUpdate();
            res.close();
        } catch (SQLException ex) {
            LOG.error("Cannot save box item data from database", ex);
        }
    }

    @Override
    public void update(BoxEntity boxEntity, String... params) {
    }

    @Override
    public void delete(BoxEntity boxEntity) {
        if (boxEntity == null) {
            throw new IllegalArgumentException("item is incorrect");
        }
        try (Connection connection = JDBC.getConnection();
        PreparedStatement deleteBoxStatement = connection.prepareStatement(DELETE_BOX)) {
            deleteBoxStatement.setLong(1, boxEntity.getId());
            deleteBoxStatement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Cannot delete box item data from database", ex);
        }
    }
}
