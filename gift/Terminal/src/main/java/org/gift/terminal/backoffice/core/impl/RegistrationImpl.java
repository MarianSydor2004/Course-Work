package org.gift.terminal.backoffice.core.impl;

import org.gift.terminal.backoffice.core.manager.RegistrationManager;
import org.gift.terminal.backoffice.dao.user.UserDao;
import org.gift.terminal.common.dto.UserDto;

public class RegistrationImpl implements RegistrationManager {
    private UserDao userDao;

    public RegistrationImpl() {
        userDao = new UserDao();
    }

    @Override
    public UserDto logIn(UserDto dto) {
        var user = userDao.get(dto.getEmail());
        if (user.isPresent()) {
            var entity = user.get();
            return new UserDto(entity.getEmail(), entity.getPassword(), entity.getUserIdentity().getShopId(),
                    entity.getUserIdentity().getCountryId(), entity.getAccessLevel(), entity.getUserIdentity().getUserStatus());
        }
        return null;
    }
}
