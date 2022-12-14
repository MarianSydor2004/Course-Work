package org.gift.terminal.common.dto.items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
public class BoxDto extends AbstractItemDto {
    private Integer colorCode;
    private Double scale;
    private Double width;
    private Double length;
    private String color;
    private ItemCommonDto commonDto;

    public BoxDto(Double weight, String code, Integer colorCode,
                  Double scale, Double width, Double length, String color, ItemCommonDto commonDto) {
        super(weight, code);
        this.colorCode = colorCode;
        this.scale = scale;
        this.width = width;
        this.length = length;
        this.color = color;
        this.commonDto = commonDto;
    }
}
