package co.edu.udem.devops.donation.repository;

import co.edu.udem.devops.donation.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByUserId(Long userId);

    List<Donation> findAllByCampaignId(Long campaignId);
}
