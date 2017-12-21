package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class RegisterAdministratorAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors ---------------------------------------------------------

	public RegisterAdministratorAdministratorController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register_Administrator", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Administrator administrator;

		administrator = this.administratorService.create();
		res = this.createEditModelAndView(administrator);

		return res;
	}

	@RequestMapping(value = "/register_Administrator", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(administrator, "actor.params.error");
		} else {
			try {
				this.administratorService.save(administrator);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(administrator, "actor.commit.error");
			}
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/register_Administrator");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}
}
