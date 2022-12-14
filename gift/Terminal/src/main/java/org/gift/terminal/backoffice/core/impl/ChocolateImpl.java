package org.gift.terminal.backoffice.core.impl;

import org.gift.terminal.backoffice.core.manager.ItemManager;
import org.gift.terminal.backoffice.dao.item.ChocolateDao;
import org.gift.terminal.backoffice.model.item.ChocolateEntity;
import org.gift.terminal.backoffice.model.item.ItemCommonData;
import org.gift.terminal.common.dto.items.ChocolateDto;
import org.gift.terminal.common.dto.items.ItemCommonDto;

import java.util.ArrayList;
import java.util.List;

public class ChocolateImpl implements ItemManager<ChocolateDto> {
    private ChocolateDao chocolateDao;

    public ChocolateImpl() {
        this.chocolateDao = new ChocolateDao();
    }

    public void setChocolateDao(ChocolateDao chocolateDao) {
        this.chocolateDao = chocolateDao;
    }

    @Override
    public ChocolateDto create(ChocolateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Incorrect chocolate dto");
        }
        var chocolate = new ChocolateEntity();
        chocolate.setWeight(dto.getWeight());
        chocolate.setCode(dto.getCode());
        var itemDto = dto.getCommonDto();
        chocolate.setItemData(new ItemCommonData(0, 1, itemDto.getPrice(),
                itemDto.isAvailable(), itemDto.getCategory(), itemDto.getBrand(),
                itemDto.getLabel(), itemDto.getDescription()));
        chocolate.setSugar(dto.getSugar());
        chocolate.setChocolateType(dto.getChocolateType());
        chocolate.setComponents(dto.getComponents());
        chocolateDao.save(chocolate);
        return dto;
    }

    @Override
    public ChocolateDto edit(ChocolateDto dto) {
        var list = chocolateDao.getAll();
        for (ChocolateEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                chocolateDao.delete(entity);
                return create(dto);
            }
        }
        return null;
    }

    @Override
    public void delete(ChocolateDto dto) {
        var list = chocolateDao.getAll();
        for (ChocolateEntity entity : list) {
            if (entity.getCode().equals(dto.getCode())) {
                chocolateDao.delete(entity);
            }
        }
    }

    @Override
    public List<ChocolateDto> view() {
        var list = chocolateDao.getAll();
        var dtoList = new ArrayList<ChocolateDto>(list.size());
        for (ChocolateEntity entity : list) {
            var common = entity.getItemData();
            var dto = new ChocolateDto(entity.getWeight(), entity.getCode(), entity.getSugar(), entity.getChocolateType(), entity.getComponents(),
                    new ItemCommonDto(common.getPrice(), common.isAvailable(), common.getCategory(), common.getBrand(), common.getLabel(), common.getDescription()));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
