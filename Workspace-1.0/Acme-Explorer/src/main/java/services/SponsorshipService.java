package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Trip;

@Service
@Transactional
public class SponsorshipService {

	// Managed repository

	@Autowired
	private SponsorshipRepository sponsorshipRepository;

	// Supporting services

	@Autowired
	private SponsorService sponsorService;

	// Constructors

	public SponsorshipService() {
		super();
	}

	// Simple CRUD methods

	public Sponsorship create() {
		Sponsorship sponsorship;
		Sponsor sponsor;
		Trip trip;
		sponsor = this.sponsorService.findByPrincipal();
		Assert.notNull(sponsor);
		sponsorship = new Sponsorship();
		trip = new Trip();
		sponsorship.setSponsor(sponsor);
		sponsorship.setTrip(trip);
		return sponsorship;
	}

	public Collection<Sponsorship> findAll() {
		Collection<Sponsorship> res;
		res = this.sponsorshipRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Sponsorship findOne(int sponsorship) {
		Assert.isTrue(sponsorship != 0);
		Sponsorship res;
		res = this.sponsorshipRepository.findOne(sponsorship);
		Assert.notNull(res);
		return res;
	}

	public Sponsorship save(Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		if(sponsorship.getId() != 0){
			Assert.isTrue(sponsorship.getSponsor().getId() == sponsorService.findByPrincipal().getId());
		}
		Sponsorship res;
		res = this.sponsorshipRepository.save(sponsorship);
		return res;
	}

	public void delete(Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Assert.isTrue(sponsorship.getId() != 0);
		Assert.isTrue(this.sponsorshipRepository.exists(sponsorship.getId()));
		if(sponsorship.getId() != 0){
			Assert.isTrue(sponsorship.getSponsor().getId() == sponsorService.findByPrincipal().getId());
		}
		this.sponsorshipRepository.delete(sponsorship);
	}

	// Other business methods
	
	public Collection<Sponsorship> findSponsorshipsBySponsor(int id) {
		Collection<Sponsorship> res = new ArrayList<Sponsorship>();

		res.addAll(sponsorshipRepository.findSponsorshipsBySponsor(id));
		Assert.notNull(res);
		return res;
	}
	
	public Sponsorship findAllSponsorship() {
		List<Sponsorship> res;
		Sponsorship spon;
		res = (List<Sponsorship>) this.findAll();
		Integer tam;
		tam = res.size();
		
		int aleatorio = (int) (Math.random() * tam);
		spon = res.get(aleatorio);
		
		return spon;

	}

}
