package controllers.explorer;

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

import services.StoryService;
import services.TripService;
import controllers.AbstractController;
import domain.Story;
import domain.Trip;

@Controller
@RequestMapping("/story/explorer")
public class StoryExplorerController extends AbstractController {

	// Services -------------------------------------------------------

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private TripService tripService;

	// Constructors -------------------------------------------------------

	public StoryExplorerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Story> stories;

		stories = storyService.findAll();

		result = new ModelAndView("story/explorer/list");
		result.addObject("story", stories);
		result.addObject("requestURI", "story/explorer/list.do");

		return result;

	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int storyId) {
		ModelAndView result;
		Story story;

		story = storyService.findOne(storyId);
		Assert.notNull(story);
		result = this.createEditModelAndView(story);

		return result;
	}

	// Saving ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Story story,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(story, "story.commit.error");
		else
			try {
				this.storyService.save(story);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(story, "story.commit.error");
			}

		return res;
	}

	// Deleting ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Story story, BindingResult binding) {
		ModelAndView res;

		try {
			this.storyService.delete(story);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(story, "story.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Story story;

		story = this.storyService.create();
		res = this.createEditModelAndView(story);

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Story story) {
		ModelAndView result;

		result = this.createEditModelAndView(story, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Story story,
			final String message) {
		ModelAndView result;
		Collection<Trip> trip;
		trip = this.tripService.findAll();
		result = new ModelAndView("story/explorer/edit");
		result.addObject("trip", trip);
		result.addObject("story", story);
		result.addObject("message", message);

		return result;
	}

}
