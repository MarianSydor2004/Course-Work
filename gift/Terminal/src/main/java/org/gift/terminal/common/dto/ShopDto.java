package org.gift.terminal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopDto {
    private Long id;
    private String brand;
    private String description;
}
