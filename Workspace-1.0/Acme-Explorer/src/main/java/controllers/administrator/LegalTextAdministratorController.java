package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LegalTextService;
import controllers.AbstractController;
import domain.LegalText;

@Controller
@RequestMapping("/legalText/administrator")
public class LegalTextAdministratorController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private LegalTextService legalTextService;
	
	// Constructors ---------------------------------------------------------

	public LegalTextAdministratorController() {
		super();
	}
	
	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<LegalText> legalTexts;
		
		legalTexts = legalTextService.findAll();
		
		result = new ModelAndView("legalText/list");
		result.addObject("legalText", legalTexts);
		
		return result;
	}
	
	// Editing ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int legalTextId) {
		ModelAndView result;
		LegalText legalText;

		legalText = legalTextService.findOne(legalTextId);
		Assert.notNull(legalText);
		result = this.createEditModelAndView(legalText);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final LegalText legalText,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(legalText, "legalText.params.error");
		else
			try {
				this.legalTextService.save(legalText);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(legalText, "legalText.commit.error");
			}

		return res;
	}
	
	// Deleting ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(LegalText legalText, BindingResult binding) {
		ModelAndView res;

		try {
			this.legalTextService.delete(legalText);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(legalText, "legalText.commit.error");
		}

		return res;
	}
	
	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LegalText legalText;

		legalText = this.legalTextService.create();
		result = this.createEditModelAndView(legalText);

		return result;
	}
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final LegalText legalText) {
		ModelAndView result;

		result = this.createEditModelAndView(legalText, null);

		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(final LegalText legalText,
			final String message) {
		ModelAndView result;
		//final Collection<Trip> trips;
		//trips = this.tripService.findAll();
		result = new ModelAndView("legalText/administrator/edit");
		//result.addObject("trip", trips);
		result.addObject("legalText", legalText);
		result.addObject("message", message);
		return result;
	}
	
	
	
}
