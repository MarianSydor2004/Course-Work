package org.gift.terminal.common.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractItemDto {
    private Double weight;
    private String code;
}
