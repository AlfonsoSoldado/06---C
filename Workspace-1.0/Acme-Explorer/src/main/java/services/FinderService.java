package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {
	
	// Managed repository

	@Autowired
	private FinderRepository finderRepository;
	
	// Constructors
	
	public FinderService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Finder create() {
		Finder res;
		Date d = new Date(System.currentTimeMillis() - 1);
		Collection<Trip> trip = new ArrayList<Trip>();
		res = new Finder();
		res.setCache(d);
		res.setTrip(trip);
		return res;
	}
	
	public Collection<Finder> findAll() {
		Collection<Finder> res;
		res = this.finderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Finder findOne(int finder) {
		Assert.isTrue(finder != 0);
		Finder res;
		res = this.finderRepository.findOne(finder);
		Assert.notNull(res);
		return res;
	}

	public Finder save(Finder finder) {
		Assert.notNull(finder);
		Finder res;
		res = this.finderRepository.save(finder);
		return res;
	}

	// Other business methods
	
	// 34.1

	public Collection<Trip> findSearchCriterial(String singleKey, Date start, Date end, Double minPrice, Double maxPrice){
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(finderRepository.resultFinderExplorer(singleKey, start, end, minPrice, maxPrice));
		return res;
	}
	
	public Collection<Trip> findSearchSingleKey(String singleKey) {
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(finderRepository.resultFinder(singleKey));
		return res;
	}
	
	public Integer resId() {
		Integer res;
		res = finderRepository.resId();
		return res;
	}
}
