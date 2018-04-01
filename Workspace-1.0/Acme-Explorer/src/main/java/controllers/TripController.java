package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationService;
import services.FinderService;
import services.ManagerService;
import services.SponsorshipService;
import services.TripService;
import domain.Configuration;
import domain.Finder;
import domain.Sponsorship;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController extends AbstractController{

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private FinderService finderService;
	
	@Autowired
	private SponsorshipService sponsorshipService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Autowired
	private ManagerService managerService;
	
	//Constructors ---------------------------------------------------------
	
	public TripController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Trip> trips;
		
		trips = tripService.findAll();
		
		int currentManagerId = 0;
		
		try {
			if(managerService.findByPrincipal().getId() != 0){
				currentManagerId = managerService.findByPrincipal().getId();
			}	
		} catch(IllegalArgumentException e){
			
		}
		
		result = new ModelAndView("trip/list");
		result.addObject("numPage", 5);
		result.addObject("trips", trips);
		result.addObject("currentManagerId", currentManagerId);
		result.addObject("requestURI", "trip/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tripId){
		ModelAndView result;
		Trip trip;
		
		trip = this.tripService.findOne(tripId);
		
		Sponsorship sponsorship;
		sponsorship = sponsorshipService.findAllSponsorship();
		
		
		result = new ModelAndView("trip/display");
		result.addObject("sponsorship", sponsorship);
		result.addObject("displayTrip", trip);
		result.addObject("requestURI", "trip/display.do");
		
		return result;
	}
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int categoryId){
		ModelAndView result;
		Collection<Trip> trips;
		
		trips = tripService.findTripsByCategory(categoryId);
		
		result = new ModelAndView("trip/list");
		result.addObject("numPage", 5);
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/category/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/finder/list", method = RequestMethod.GET)
	public ModelAndView listFinder(@RequestParam int finderId){
		ModelAndView result;
		Collection<Trip> trips;
		Finder finder = this.finderService.findOne(finderId);
		String singleKey;
		singleKey = finder.getSingleKey();
		if (singleKey.isEmpty()) {
			trips = tripService.findAll();
		} else {
			trips = finderService.findSearchSingleKey(singleKey);
		}
		result = new ModelAndView("trip/list");
		
		Configuration configuration;
		
		Integer confId = configurationService.resId();
		
		configuration = configurationService.findOne(confId);
		
		Integer np = configuration.getNumberPage();
		
		result.addObject("numPage", np);
		
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/finder/list.do");
		return result;
	}
	
	@RequestMapping(value = "/finder/explorer/list", method = RequestMethod.GET)
	public ModelAndView listFinderExplorer(@RequestParam int finderId){
		ModelAndView result;
		Collection<Trip> trips;
		Finder finder = this.finderService.findOne(finderId);
		String singleKey;
		singleKey = finder.getSingleKey();
		Date start = finder.getStart();
		Date end = finder.getEnd();
		Double minPrice = finder.getMinPrice();
		Double maxPrice = finder.getMaxPrice();
		if (singleKey.isEmpty()) {
			trips = tripService.findAll();
		} else {
			trips = finderService.findSearchCriterial(singleKey, start, end, minPrice, maxPrice);
		}
		result = new ModelAndView("trip/list");
		
		Configuration configuration;
		
		Integer confId = configurationService.resId();
		
		configuration = configurationService.findOne(confId);
		
		Integer np = configuration.getNumberPage();
		
		result.addObject("numPage", np);
		
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/finder/explorer/list.do");
		return result;
	}
}
