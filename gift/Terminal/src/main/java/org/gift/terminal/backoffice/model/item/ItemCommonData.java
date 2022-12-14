package org.gift.terminal.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.terminal.backoffice.model.AbstractEntity;
import org.gift.terminal.common.constants.ItemCategory;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ItemCommonData extends AbstractEntity {
    private long shopId;
    private double price;
    private boolean isAvailable;
    private ItemCategory category;
    private String brand;
    private String label;
    private String description;

    public ItemCommonData(long id, long shopId, double price, boolean isAvailable,
                          ItemCategory category, String brand, String label, String description) {
        super(id);
        this.shopId = shopId;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
        this.brand = brand;
        this.label = label;
        this.description = description;
    }
}
