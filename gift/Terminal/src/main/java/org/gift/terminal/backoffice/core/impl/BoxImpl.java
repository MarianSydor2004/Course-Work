package org.gift.terminal.backoffice.core.impl;

import org.gift.terminal.backoffice.core.manager.ItemManager;
import org.gift.terminal.backoffice.dao.item.BoxDao;
import org.gift.terminal.backoffice.model.item.BoxEntity;
import org.gift.terminal.backoffice.model.item.ItemCommonData;
import org.gift.terminal.common.dto.items.BoxDto;
import org.gift.terminal.common.dto.items.ItemCommonDto;

import java.util.ArrayList;
import java.util.List;

public class BoxImpl implements ItemManager<BoxDto> {
    private BoxDao boxDao;

    public BoxImpl() {
        this.boxDao = new BoxDao();
    }

    public void setBoxDao(BoxDao boxDao) {
        this.boxDao = boxDao;
    }

    @Override
    public BoxDto create(BoxDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Incorrect box dto");
        }
        var box = new BoxEntity();
        box.setWeight(dto.getWeight());
        box.setCode(dto.getCode());
        var itemDto = dto.getCommonDto();
        box.setItemData(new ItemCommonData(0, 1, itemDto.getPrice(),
                itemDto.isAvailable(), itemDto.getCategory(), itemDto.getBrand(),
                itemDto.getLabel(), itemDto.getDescription()));
        box.setColorCode(dto.getColorCode());
        box.setScale(dto.getScale());
        box.setWeight(dto.getWeight());
        box.setLength(dto.getLength());
        box.setColor(dto.getColor());
        boxDao.save(box);
        return dto;
    }

    @Override
    public BoxDto edit(BoxDto dto) {
        var list = boxDao.getAll();
        for (BoxEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                boxDao.delete(entity);
                return create(dto);
            }
        }
        return null;
    }

    @Override
    public void delete(BoxDto dto) {
        var list = boxDao.getAll();
        for (BoxEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                boxDao.delete(entity);
            }
        }
    }

    @Override
    public List<BoxDto> view() {
        var list = boxDao.getAll();
        var dtoList = new ArrayList<BoxDto>(list.size());
        for (BoxEntity entity : list) {
            var common = entity.getItemData();
            var dto = new BoxDto(entity.getWeight(), entity.getCode(), entity.getColorCode(), entity.getScale(), entity.getWidth(), entity.getLength(), entity.getColor(),
                    new ItemCommonDto(common.getPrice(), common.isAvailable(), common.getCategory(), common.getBrand(), common.getLabel(), common.getDescription()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
