package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LegalTextRepository;
import domain.LegalText;
import domain.Trip;

@Service
@Transactional
public class LegalTextService {

	// Managed repository

	@Autowired
	private LegalTextRepository legalTextRepository;

	// Supporting services

	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public LegalTextService() {
		super();
	}

	// Simple CRUD methods

	public LegalText create() {
		administratorService.checkAuthority();

		LegalText res = new LegalText();
		Date moment = new Date(System.currentTimeMillis() - 1);
		Collection<Trip> trip = new ArrayList<Trip>();		
		res.setDraftMode(true);
		res.setTrip(trip);
		res.setMoment(moment);
		return res;
	}

	public Collection<LegalText> findAll() {
		Collection<LegalText> res;
		res = this.legalTextRepository.findAll();
		return res;
	}

	public LegalText findOne(int legalText) {
		Assert.isTrue(legalText != 0);
		LegalText res;
		res = this.legalTextRepository.findOne(legalText);
		Assert.notNull(res);
		return res;
	}

	public LegalText save(LegalText legalText) {
		Assert.notNull(legalText);
		Assert.notNull(this.administratorService.findByPrincipal());
		if(legalText.getId() != 0){
			if(legalText.getDraftMode()==true && !legalText.getTrip().isEmpty()){
				Assert.isTrue(legalText.getDraftMode()==true && legalText.getTrip().isEmpty());
			}
			LegalText legalTextInDB = this.findLegalTextInDB(legalText.getId());
			Assert.isTrue(legalTextInDB.getDraftMode() == true);
		}
		
		LegalText res;
		res = this.legalTextRepository.save(legalText);
		return res;
	}

	public void delete(LegalText legalText) {
		administratorService.checkAuthority();

		Assert.isTrue(legalText.getDraftMode() == true);
		
		Collection<Trip> trips;
		trips = legalText.getTrip();
		
		for(Trip t: trips){
			t.setLegalText(null);
		}
		
		this.legalTextRepository.delete(legalText);
	}

	// Other business methods

	// 14.2

	public Collection<LegalText> findLegalTextsByTrip(Trip trip) {
		administratorService.checkAuthority();
		Assert.notNull(trip);
		Collection<LegalText> res = new ArrayList<LegalText>();
		res = legalTextRepository.findLegalTextsByTrip(trip.getId());
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Trip> findTripsWithoutLegalText(){
		Collection<Trip> res = new ArrayList<Trip>();
		res = legalTextRepository.findTripsWithoutLegalText();
		return res;
	}
	
	public LegalText findLegalTextInDB(int legalTextId){
		LegalText res;
		res = legalTextRepository.findLegalTextInDB(legalTextId);
		return res;
	}
}
