package org.gift.terminal.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.terminal.backoffice.model.AbstractEntity;
import org.gift.terminal.common.constants.AccessLevel;
import org.gift.terminal.common.constants.UserStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserEntity extends AbstractEntity {
    private boolean isVerified;
    private String email;
    private String password;
    private AccessLevel accessLevel;
    private UserIdentityEntity userIdentity;

    public UserEntity(long id, boolean isVerified, String email, String password, AccessLevel accessLevel,
                      long shopId, long countryId, UserStatus userStatus) {
        super(id);
        this.isVerified = isVerified;
        this.email = email;
        this.password = password;
        this.accessLevel = accessLevel;
        this.userIdentity = new UserIdentityEntity(id, shopId, countryId, userStatus);
    }
}
