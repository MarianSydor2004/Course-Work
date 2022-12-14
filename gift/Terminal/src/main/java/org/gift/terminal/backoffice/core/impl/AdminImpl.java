package org.gift.terminal.backoffice.core.impl;

import org.gift.terminal.backoffice.core.manager.AdminManager;
import org.gift.terminal.backoffice.dao.user.ShopDao;
import org.gift.terminal.backoffice.dao.user.UserDao;
import org.gift.terminal.backoffice.model.user.ShopEntity;
import org.gift.terminal.backoffice.model.user.UserEntity;
import org.gift.terminal.backoffice.model.user.UserIdentityEntity;
import org.gift.terminal.common.dto.ShopDto;
import org.gift.terminal.common.dto.UserDto;

public class AdminImpl implements AdminManager {
    private UserDao userDao;
    private ShopDao shopDao;

    public AdminImpl() {
        userDao = new UserDao();
        shopDao = new ShopDao();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setShopDao(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public UserDto createRetailer(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("User dto is incorrect");
        }
        var userEntity = new UserEntity();
        userEntity.setVerified(true);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setAccessLevel(user.getAccessLevel());
        var identity = new UserIdentityEntity();
        identity.setShopId(user.getShopId());
        identity.setCountryId(user.getCountryId());
        identity.setUserStatus(user.getUserStatus());
        userEntity.setUserIdentity(identity);
        userDao.save(userEntity);
        return user;
    }

    @Override
    public UserDto editRetailer(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("User dto is incorrect");
        }
        var userEntity = new UserEntity();
        userEntity.setVerified(true);
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setAccessLevel(user.getAccessLevel());
        var identity = new UserIdentityEntity();
        identity.setShopId(user.getShopId());
        identity.setCountryId(user.getCountryId());
        identity.setUserStatus(user.getUserStatus());
        userEntity.setUserIdentity(identity);
        userDao.save(userEntity);
        return user;
    }

    @Override
    public ShopDto createShop(ShopDto shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop dto is incorrect");
        }
        var entity = new ShopEntity();
        entity.setBrand(shop.getBrand());
        entity.setDescription(shop.getDescription());
        shopDao.save(entity);
        return shop;
    }
}
