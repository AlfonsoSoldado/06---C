package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/administrator")
public class RegisterSponsorAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private SponsorService sponsorService;

	// Constructors ---------------------------------------------------------

	public RegisterSponsorAdministratorController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register_Sponsor", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Sponsor sponsor;

		sponsor = this.sponsorService.create();
		res = this.createEditModelAndView(sponsor);

		return res;
	}

	@RequestMapping(value = "/register_Sponsor", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(sponsor, "actor.params.error");
		} else {
			try {
				this.sponsorService.save(sponsor);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				System.out.println(oops.getCause());
				System.out.println(oops.getLocalizedMessage());
				System.out.println(oops.getMessage());
				System.out.println(oops.fillInStackTrace());
				res = this.createEditModelAndView(sponsor, "actor.commit.error");
			}
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/register_Sponsor");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);

		return result;
	}
}
