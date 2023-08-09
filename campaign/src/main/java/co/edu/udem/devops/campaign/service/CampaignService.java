package co.edu.udem.devops.campaign.service;

import co.edu.udem.devops.campaign.model.Campaign;
import co.edu.udem.devops.campaign.repository.CampaignRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Optional<Campaign> findCampaignById(Long campaignId) {
        return campaignRepository.findById(campaignId);
    }

    public Optional<Campaign> findCampaignByUserId(Long userId) {
        return campaignRepository.findCampaignByUserId(userId);
    }

    public List<Campaign> findAllCampaigns() {
        return campaignRepository.findAll();
    }

    public void updateCampaignStatus(Boolean newStatus, Long campaignId) {
        campaignRepository.updateCampaignStatus(newStatus, campaignId);
    }

    @Transactional
    public void updateCampaignDetails(Long campaignId, Campaign campaign) {
        campaignRepository.updateCampaignDetails(
                campaign.getDescription(),
                campaign.getGoal(),
                campaign.getDeadline(),
                campaignId
        );

    }

    @Transactional
    public void deleteCampaign(Long campaignId) {
        campaignRepository.deleteById(campaignId);
    }
}
