package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.StageService;
import services.TripService;
import domain.Finder;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController {

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private FinderService finderService;
	
	
	
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
		
		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int categoryId){
		ModelAndView result;
		Collection<Trip> trips;
		
		trips = tripService.findTripsByCategory(categoryId);
		
		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/category/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/finder/list", method = RequestMethod.GET)
	public ModelAndView listFinder(){
		ModelAndView result;
		Collection<Trip> trips;
		Integer resId = finderService.resId();
		Finder finder = this.finderService.findOne(resId);
		String singleKey;
		singleKey = finder.getSingleKey();
		trips = finderService.findSearchSingleKey(singleKey);
		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/finder/list.do");
		return result;
	}
	
	@RequestMapping(value = "/finder/explorer/list", method = RequestMethod.GET)
	public ModelAndView listFinderExplorer(){
		ModelAndView result;
		Collection<Trip> trips;
		Integer resId = finderService.resId();
		Finder finder = this.finderService.findOne(resId);
		String singleKey;
		singleKey = finder.getSingleKey();
		Date start = finder.getStart();
		Date end = finder.getEnd();
		Double minPrice = finder.getMinPrice();
		Double maxPrice = finder.getMaxPrice();
		trips = finderService.findSearchCriterial(singleKey, start, end, minPrice, maxPrice);
		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/finder/explorer/list.do");
		return result;
	}
}
