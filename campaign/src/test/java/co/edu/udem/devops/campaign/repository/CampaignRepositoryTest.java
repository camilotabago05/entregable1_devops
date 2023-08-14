package co.edu.udem.devops.campaign.repository;

import co.edu.udem.devops.campaign.model.Campaign;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CampaignRepositoryTest {
    @Autowired
    private CampaignRepository campaignRepository;

    @Test
    public void CampaignRepository_PostCampaign_ReturnsSavedCampaign() {
        Campaign campaign = Campaign.builder()
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .userId(1L)
                .build();

        Campaign savedCampaign = campaignRepository.save(campaign);

        Assertions.assertNotNull(savedCampaign);
        Assertions.assertTrue(savedCampaign.getCampaignId() > 0);
    }

    @Test
    public void CampaignRepository_GetCampaign_ReturnsCampaignById() {
        Campaign campaign = Campaign.builder()
                .title("Campaña #1")
                .description("Propuesta de Campaña #1")
                .goal(100.0)
                .deadline(LocalDate.parse("2023-06-01"))
                .userId(1L)
                .build();

        Campaign savedCampaign = campaignRepository.save(campaign);

        Optional<Campaign> foundCampaign = campaignRepository.findById(savedCampaign.getCampaignId());

        Assertions.assertTrue(foundCampaign.isPresent());
        Assertions.assertSame(savedCampaign.getCampaignId(), foundCampaign.get().getCampaignId());
    }
}
