package org.gift.terminal.common.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gift.terminal.common.constants.ItemCategory;

@Data
@AllArgsConstructor
public class ItemCommonDto {
    private double price;
    private boolean isAvailable;
    private ItemCategory category;
    private String brand;
    private String label;
    private String description;
}
