package org.gift.terminal.backoffice.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.terminal.backoffice.model.AbstractEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ShopEntity extends AbstractEntity {
    private String brand;
    private String description;

    public ShopEntity(long id, String brand, String description) {
        super(id);
        this.brand = brand;
        this.description = description;
    }
}
