package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RangerService;
import domain.Ranger;

@Controller
@RequestMapping("/ranger")
public class RegisterRangerController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private RangerService rangerService;


	// Constructors ---------------------------------------------------------

	public RegisterRangerController() {
		super();
	}
	
	// Registering ----------------------------------------------------------
	
	@RequestMapping(value = "/register_Ranger", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		Ranger ranger;
		
		ranger = this.rangerService.create();
		res = this.createEditModelAndView(ranger);
		
		return res;
	}
	
	@RequestMapping(value = "/register_Ranger", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Ranger ranger,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(ranger, "actor.params.error");
		} else {
			try {
				this.rangerService.save(ranger);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(ranger, "actor.commit.error");
			}
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
		protected ModelAndView createEditModelAndView(final Ranger ranger) {
			ModelAndView result;

			result = this.createEditModelAndView(ranger, null);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final Ranger ranger,
				final String message) {
			ModelAndView result;

			result = new ModelAndView("ranger/register_Ranger");
			result.addObject("ranger", ranger);
			result.addObject("message", message);
			
			return result;
		}
}
