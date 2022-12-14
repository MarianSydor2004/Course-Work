package org.gift.terminal.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.terminal.backoffice.model.AbstractEntity;
import org.gift.terminal.common.constants.UserStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserIdentityEntity extends AbstractEntity {
    private long shopId;
    private long countryId;
    private UserStatus userStatus;

    public UserIdentityEntity(long id, long shopId, long countryId, UserStatus userStatus) {
        super(id);
        this.shopId = shopId;
        this.countryId = countryId;
        this.userStatus = userStatus;
    }
}
