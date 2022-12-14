package org.gift.terminal.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gift.terminal.backoffice.model.AbstractEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AbstractItemEntity extends AbstractEntity {
    private double weight;
    private String code;
    private ItemCommonData itemData;

    public AbstractItemEntity(long id, double weight, String code, ItemCommonData itemData) {
        super(id);
        this.weight = weight;
        this.code = code;
        this.itemData = itemData;
    }
}
