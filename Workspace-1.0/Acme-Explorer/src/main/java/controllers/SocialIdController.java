package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SocialIdService;
import domain.Actor;
import domain.SocialId;

@Controller
@RequestMapping("/socialId")
public class SocialIdController extends AbstractController {

	// Services -------------------------------------------------------

	@Autowired
	SocialIdService socialIdService;

	@Autowired
	ActorService actorService;

	// Constructors -----------------------------------------------------------

	public SocialIdController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<SocialId> socialId;

		socialId = socialIdService.findSocialIds();

		res = new ModelAndView("socialId/list");
		res.addObject("socialId", socialId);

		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialIdId) {
		ModelAndView result;
		SocialId socialId;

		socialId = this.socialIdService.findOne(socialIdId);
		result = this.createEditModelAndView(socialId);

		return result;
	}

	// Saving ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialId socialId,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(socialId,
					"socialId.params.error");
		else
			try {
				this.socialIdService.save(socialId);
				result = new ModelAndView("redirect:../socialId/list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(socialId,
						"socialId.commit.error");
			}
		return result;
	}

	// Deleting ------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SocialId socialId,
			final BindingResult binding) {
		ModelAndView result;

		try {
			this.socialIdService.delete(socialId);
			result = new ModelAndView("redirect:../socialId/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(socialId,
					"socialId.commit.error");
		}
		return result;
	}

	// Creating ------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		SocialId socialId;
		socialId = this.socialIdService.create();
		result = this.createEditModelAndView(socialId);
		return result;
	}

	// Ancillary methods ---------------------------------------------------------

	protected ModelAndView createEditModelAndView(final SocialId socialId) {
		ModelAndView result;
		result = this.createEditModelAndView(socialId, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final SocialId socialId,
			final String messageCode) {
		ModelAndView result;
		Collection<SocialId> socialIds;
		Actor actor;
		actor = this.actorService.findByPrincipal();
		socialIds = actor.getSocialId();
		result = new ModelAndView("socialId/edit");
		result.addObject("socialIds", socialIds);
		result.addObject("socialId", socialId);
		result.addObject("message", messageCode);
		return result;
	}
}
