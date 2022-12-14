package org.gift.terminal.backoffice.core.manager;

import org.gift.terminal.common.dto.ShopDto;
import org.gift.terminal.common.dto.UserDto;

public interface AdminManager {
    UserDto createRetailer(UserDto user);

    UserDto editRetailer(UserDto user);

    ShopDto createShop(ShopDto shop);
}