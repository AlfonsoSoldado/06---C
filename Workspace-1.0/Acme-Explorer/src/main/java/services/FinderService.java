package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Configuration;
import domain.Finder;
import domain.Trip;

@Service
@Transactional
public class FinderService {
	
	// Managed repository

	@Autowired
	private FinderRepository finderRepository;
	
	@Autowired
	private ConfigurationService configurationService;
	
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

		Finder res = null;

		Configuration configuration;
		Integer lastOne = configurationService.resId();

		configuration = configurationService.findOne(lastOne);

		Integer cache = configuration.getCache() * 3600000;

		Integer actual = LocalDateTime.now().getMillisOfSecond();

		Collection<Finder> finders = findAll();

		if (finders.isEmpty()) {
			res = this.finderRepository.save(finder);
		} else {
			for (Finder f : finders) {
				Long diff = actual - f.getCache().getTime();

				if (diff > cache) {
					delete(f);
					res = this.finderRepository.save(finder);
				} else if (f.getSingleKey().equals(finder.getSingleKey())
						&& diff < cache) {
					res = findOne(f.getId());
				}
			}
			if(res == null){
				res = this.finderRepository.save(finder);
			}
		}
		
		return res;
	}
	
	public void delete(Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(this.finderRepository.exists(finder.getId()));
		this.finderRepository.delete(finder);
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
//		Collection<Finder> finders = findAll();
//		
//		for (Finder f : finders) {
//			if (f.getSingleKey().equals(singleKey)) {
//				res.addAll(f.getTrip());
//			} else {
//				res.addAll(finderRepository.resultFinder(singleKey));
//			}
//
//		}
		
		return res;
	}
	
	public Integer resId() {
		Integer res;
		res = finderRepository.resId();
		return res;
	}
}
