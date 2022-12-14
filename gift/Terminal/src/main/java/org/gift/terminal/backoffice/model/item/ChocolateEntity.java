package org.gift.terminal.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ChocolateEntity extends AbstractItemEntity{
    private double sugar;
    private String chocolateType;
    private List<String> components;

    public ChocolateEntity(long id, double weight, String code, ItemCommonData itemData,
                           double sugar, String chocolateType, List<String> components) {
        super(id, weight, code, itemData);
        this.sugar = sugar;
        this.chocolateType = chocolateType;
        this.components = components;
    }
}
