package org.gift.terminal.backoffice.core.manager;

import org.gift.terminal.common.dto.UserDto;

public interface RegistrationManager {
    UserDto logIn(UserDto dto);
}
