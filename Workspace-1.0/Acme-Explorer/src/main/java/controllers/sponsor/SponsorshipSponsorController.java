package controllers.sponsor;

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

import services.SponsorshipService;
import controllers.AbstractController;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/sponsor")
public class SponsorshipSponsorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	SponsorshipService sponsorshipService;

	// Constructors ---------------------------------------------------------

	public SponsorshipSponsorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Sponsorship> sponsorship;

		sponsorship = sponsorshipService.findAll();

		result = new ModelAndView("sponsorship/list");
		result.addObject("sponsorship", sponsorship);
		result.addObject("requestURI", "sponsorship/sponsor/list.do");

		return result;
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sponsorshipId) {
		ModelAndView result;
		Sponsorship sponsorship;

		sponsorship = sponsorshipService.findOne(sponsorshipId);
		Assert.notNull(sponsorship);
		result = this.createEditModelAndView(sponsorship);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsorship sponsorship,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println("Binding");
			res = this
					.createEditModelAndView(sponsorship, "sponsorship.params.error");
		} else
			try {
				System.out.println("Try");
				this.sponsorshipService.save(sponsorship);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sponsorship,
						"sponsorship.commit.error");
			}

		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Sponsorship sponsorship,
			final BindingResult binding) {
		ModelAndView res;

		try {
			this.sponsorshipService.delete(sponsorship);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this
					.createEditModelAndView(sponsorship, "sponsorship.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsorship sponsorship;

		sponsorship = this.sponsorshipService.create();
		result = this.createEditModelAndView(sponsorship);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsorship sponsorship) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsorship, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Sponsorship sponsorship, final String messageCode) {
		ModelAndView result;
		result = new ModelAndView("sponsorship/edit");
		result.addObject("sponsorship", sponsorship);
		result.addObject("message", messageCode);
		return result;
	}

}
