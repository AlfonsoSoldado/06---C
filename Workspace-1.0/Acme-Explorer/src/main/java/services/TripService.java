package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.Application;
import domain.Category;
import domain.Configuration;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Stage;
import domain.Story;
import domain.Trip;
import domain.Value;

@Service
@Transactional
public class TripService {

	// Managed repository

	@Autowired
	private TripRepository tripRepository;

	// Supporting services

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ExplorerService explorerService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private ValueService valueService;

	// Constructors
	public TripService() {
		super();
	}

	// Simple CRUD methods

	// 12.1

	public Trip create() {
		Manager m = new Manager();
		Collection<Application> applications = new ArrayList<Application>();
		Collection<Stage> stages = new ArrayList<Stage>();
		LegalText legalText = new LegalText();
		Category category = new Category();
		Ranger ranger = new Ranger();
		Collection<Value> value = new ArrayList<Value>();
		Trip trip = new Trip();
		Collection<String> requirements =  new ArrayList<String>();
		
		m = this.managerService.findByPrincipal();
		
		trip.setTicker(this.generatedTicker());

		trip.setManager(m);
		trip.setApplication(applications);
		trip.setStage(stages);
		trip.setLegalText(legalText);
		trip.setCategory(category);
		trip.setRanger(ranger);
		trip.setValue(value);
		trip.setRequirement(requirements);
		trip.setCancelled(false);
		return trip;
	}

	public Collection<Trip> findAll() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Trip findOne(int trip) {
		Assert.isTrue(trip != 0);
		Trip res;
		res = this.tripRepository.findOne(trip);
		Assert.notNull(res);
		return res;
	}

	public Trip save(Trip trip) {
		Assert.notNull(trip);
		if(trip.getId() != 0){
			Assert.isTrue(trip.getManager().getId() == managerService.findByPrincipal().getId());
		}
		Trip res;
		Double precio = 0., tax;
		
		Configuration configuration;
		Integer conf = configurationService.resId();
		configuration = configurationService.findOne(conf);
		
		if (trip.getTripStart() != null && trip.getTripEnd() != null) {
			if(trip.getPublication() == null){
				Date moment = new Date(System.currentTimeMillis() - 1);
				trip.setPublication(moment);
			}
		}
				
		precio = this.getTotalPrice(trip);
		tax = precio * configuration.getTax();
		trip.setPrice(precio + tax);
		
//		Collection<Stage> stages = trip.getStage();
//		for (Stage s : stages) {
//			s.setTrip(trip);
//		}
		

		res = this.tripRepository.save(trip);
		return res;
	}

	// 12.1

	public void delete(Trip trip) {
		Assert.notNull(trip);
		//Assert.isTrue(managerService.findByPrincipal().getId() == trip.getManager().getId());
		if(trip.getId() != 0){
			Assert.isTrue(trip.getManager().getId() == managerService.findByPrincipal().getId());
		}
		Assert.isTrue(trip.getId() != 0);
//		Assert.isTrue(trip.getPublication().after(new Date())
//				|| trip.getPublication() == null);
		
		Collection<Application> applications;
		applications = new ArrayList<Application>();

		applications = trip.getApplication();
		for(Application a: applications){
			a.setTrip(null);
		}
		
		Collection<Stage> stages;
		stages = new ArrayList<Stage>(this.stageService.findStageByTrip(trip.getId()));
		for (final Stage s : stages)
			this.stageService.delete(s);
		
		Collection<Value> values;
		values = new ArrayList<Value>(this.valueService.findValueByTrip(trip.getId()));
		for (final Value v : values)
			this.valueService.delete(v);
		
		Collection<Story> stories = storyService.findStoryByTrip(trip.getId());
		for(Story s: stories){
			s.setTrip(null);
		}
		this.tripRepository.delete(trip);
	}

	// Other business methods

	// 12.1

	public Collection<Trip> findTripsByManager(int id) {
		managerService.checkAuthority();

		Collection<Trip> res = new ArrayList<Trip>();
		res = tripRepository.findTripsByManager(id);
		Assert.notNull(res);
		return res;
	}


	// 13.1

	public Collection<Trip> findTripsByExplorer(int id) {
		Collection<Trip> res = new ArrayList<Trip>();
		res.addAll(tripRepository.findTripsByExplorer(id));
		Assert.notNull(res);
		return res;
	}

	// 10.2

	public Collection<Trip> browseTripsByActor() {
		Collection<Trip> res = new ArrayList<Trip>();
		res = this.tripRepository.browseTripsByActor();
		Assert.notNull(res);
		return res;
	}

	// 10.3

	public Collection<Trip> findTrips(String search) {
		Assert.notNull(search);
		Assert.isTrue(search.length() != 0);
		return this.tripRepository.findTrips(search);
	}

	// 10.4

	public Collection<Trip> findTripsByCategory(int categoryId) {
		Collection<Trip> res;
		res = new ArrayList<Trip>();
		Assert.isTrue(categoryId != 0);
		res.addAll(tripRepository.browseTripsByCategories(categoryId));

		return res;
	}

	// 12.3

	public void cancelTrip(Trip trip) {
		managerService.checkAuthority();
//		Assert.isTrue(trip.getPublication().before(new Date()) && trip.getTripStart().after(new Date()));
		Collection<Trip> trips = new ArrayList<Trip>();
		trips = tripRepository.cancelTrip();
		for (Trip t : trips) {
			if (t.getId() == trip.getId()) {
				t.setCancelled(true);
			}
		}
//		if(trip.getId() != 0){
//			Trip tripInDB = this.findTripInDB(trip.getId());
//			Collection<Stage> stages = new ArrayList<Stage>();
//			stages.addAll(tripInDB.getStage());
//			System.out.println(stages);
//			trip.setStage(stages);
//			System.out.println(trip.getStage());
//			Trip res = this.save(trip);
//			res.setStage(stages);
//			System.out.println(res.getStage());
//			
//		}
	}

	// 13.4

	public void tripApplicationExplorer(Trip trip) {
		explorerService.checkAuthority();
		Assert.notNull(trip);
		Collection<Trip> trips = new ArrayList<Trip>();
		trips = tripRepository.findTripsAccepted();

		for (Trip t : trips) {
			if (t.equals(trip)) {
				t.setCancelled(true);
			}
		}
	}
	
	public String generatedTicker() {
		String ticker;
		LocalDate date;
		String letters;
		Random r;
		
		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		r = new Random();
		date = new LocalDate();
		
		ticker = String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) + 
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear())
					+ String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-";
		for (int i = 0; i < 4; i++)
			ticker = ticker + letters.charAt(r.nextInt(letters.length()));

		return ticker;
	}
	
	public Double getTotalPrice(Trip trip) {
		Double res;
		res = 0.;
		for (Stage stage : trip.getStage()) {
			res = res + stage.getPrice();
		}
		return res;
	}

	public Trip findTripInDB(int tripId){
		Trip res;
		res = tripRepository.findTripInDB(tripId);
		return res;
	}
}
