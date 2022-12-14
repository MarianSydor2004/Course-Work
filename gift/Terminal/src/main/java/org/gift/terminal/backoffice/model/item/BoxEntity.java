package org.gift.terminal.backoffice.model.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BoxEntity extends AbstractItemEntity {
    private int colorCode;
    private double scale;
    private double width;
    private double length;
    private String color;

    public BoxEntity(long id, double weight, String code, ItemCommonData itemData,
                     int colorCode, double scale, double width, double length, String color) {
        super(id, weight, code, itemData);
        this.colorCode = colorCode;
        this.scale = scale;
        this.width = width;
        this.length = length;
        this.color = color;
    }
}
