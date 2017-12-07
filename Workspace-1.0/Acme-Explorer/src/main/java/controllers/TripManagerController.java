package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.LegalTextService;
import services.RangerService;
import services.TripService;
import services.ValueService;
import domain.Category;
import domain.LegalText;
import domain.Ranger;
import domain.Trip;
import domain.Value;

@Controller
@RequestMapping("/trip/manager")
public class TripManagerController {

	
	//Services -------------------------------------------------------------
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private RangerService rangerService;
	
	@Autowired
	private LegalTextService legalTextService;
	
	@Autowired
	private ValueService valueService;
	
	@Autowired
	private CategoryService categoryService;
	
	//Constructors ---------------------------------------------------------
	
	public TripManagerController(){
		super();
	}
	
	//Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int trip){
		ModelAndView result;
		Trip t;
		
		t = tripService.findOne(trip);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);
		
		return result;
	}
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Trip trip, final String message) {
		ModelAndView result;
		//final Collection<LegalText> legalTexts;
		final Collection<Category> category;
		//Collection<Ranger> rangers;
		//final Collection<Value> values;

		//rangers = this.rangerService.findAll();
		//legalTexts = this.legalTextService.findAll();
		//values = this.valueService.findAll();
		category = this.categoryService.findAll();

		result = new ModelAndView("trip/edit");
		//result.addObject("legalTexts", legalTexts);
		result.addObject("category", category);
		//result.addObject("rangers", rangers);
	//	result.addObject("values", values);
		result.addObject("trip", trip);
		result.addObject("message", message);

		return result;

	}
}
