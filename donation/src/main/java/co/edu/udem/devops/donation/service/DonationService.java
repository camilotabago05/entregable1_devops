package co.edu.udem.devops.donation.service;

import co.edu.udem.devops.donation.model.Donation;
import co.edu.udem.devops.donation.repository.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Optional<Donation> findDonationById(Long donationId) {
        return donationRepository.findById(donationId);
    }

    public List<Donation> findAllDonationsByUserId(Long userId) {
        return donationRepository.findAllByUserId(userId);
    }

    public List<Donation> findAllDonationsByCampaignId(Long campaignId) {
        return donationRepository.findAllByCampaignId(campaignId);
    }
}
