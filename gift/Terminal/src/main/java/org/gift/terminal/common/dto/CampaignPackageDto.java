package org.gift.terminal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.gift.terminal.common.dto.items.BoxDto;
import org.gift.terminal.common.dto.items.ChocolateDto;

import java.util.List;

@Data
@AllArgsConstructor
public class CampaignPackageDto {
    private Long id;
    private BoxDto box;
    private List<ChocolateDto> chocolates;

    //TODO Make all validation methods by one template
    public boolean validate() {
        return box != null;
    }
}
