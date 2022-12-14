package org.gift.terminal.backoffice.model.campaign;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gift.terminal.backoffice.model.AbstractEntity;
import org.gift.terminal.backoffice.model.item.BoxEntity;
import org.gift.terminal.backoffice.model.item.ChocolateEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CampaignPackageEntity extends AbstractEntity {
    private BoxEntity box;
    private List<ChocolateEntity> chocolates;

    public CampaignPackageEntity(long id, BoxEntity box, List<ChocolateEntity> chocolates) {
        super(id);
        this.box = box;
        this.chocolates = chocolates;
    }
}
