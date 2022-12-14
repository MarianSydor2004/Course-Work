package org.gift.terminal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gift.terminal.common.constants.AccessLevel;
import org.gift.terminal.common.constants.UserStatus;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private Long shopId;
    private Long countryId;
    private AccessLevel accessLevel;
    private UserStatus userStatus;

    public boolean validate() {
        //TODO Return a number of wrong fields;
        if (accessLevel == null || accessLevel == AccessLevel.UNKNOWN) {
            return false;
        }
        if (isValidString(email)) {
            return false;
        }
        if (countryId == null) {
            return false;
        }
        if (userStatus == null || userStatus == UserStatus.BANNED) {
            return false;
        }
        return true;
    }

    private boolean isValidString(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }
}

