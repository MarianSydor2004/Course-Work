package org.gift.terminal.backoffice.core.manager;

import org.gift.terminal.backoffice.model.campaign.CampaignPackageEntity;

import java.util.List;

public interface CampaignPackageManager {
    void start();
    CampaignPackageEntity create();
    CampaignPackageEntity edit(CampaignPackageEntity campaignPackage);
    List<CampaignPackageEntity> view();
}
