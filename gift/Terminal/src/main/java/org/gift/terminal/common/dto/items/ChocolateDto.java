package org.gift.terminal.common.dto.items;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChocolateDto extends AbstractItemDto {
    private Double sugar;
    private String chocolateType;
    private List<String> components;
    private ItemCommonDto commonDto;

    public ChocolateDto(Double weight, String code, Double sugar, String chocolateType, List<String> components, ItemCommonDto commonDto) {
        super(weight, code);
        this.sugar = sugar;
        this.chocolateType = chocolateType;
        this.components = components;
        this.commonDto = commonDto;
    }
}
