package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final LegalText legalText) {
		ModelAndView result;

		result = this.createEditModelAndView(legalText, null);

		return result;
	}
	
	
	protected ModelAndView createEditModelAndView(final LegalText legalText,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("legalText/administrator/edit");
		result.addObject("legalText", legalText);
		result.addObject("message", message);
		return result;
	}
	
	
	
}
