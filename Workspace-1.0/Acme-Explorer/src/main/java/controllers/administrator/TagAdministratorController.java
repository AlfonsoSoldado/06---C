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

import services.TagService;
import controllers.AbstractController;
import domain.Tag;

@Controller
@RequestMapping("/tag/administrator")
public class TagAdministratorController extends AbstractController {
	// Services -------------------------------------------------------------

	@Autowired
	private TagService tagService;
	
	// Constructors ---------------------------------------------------------

	public TagAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Tag> tags;
		
		tags = tagService.findAll();
		
		result = new ModelAndView("tag/list");
		result.addObject("tag", tags);
		
		return result;
	}
	
	// Editing ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tagId) {
		ModelAndView result;
		Tag tag;

		tag = tagService.findOne(tagId);
		Assert.notNull(tag);
		result = this.createEditModelAndView(tag);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tag tag,
			final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(tag, "tag.params.error");
		else
			try {
				this.tagService.save(tag);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(tag, "tag.commit.error");
			}

		return res;
	}
	
	// Creating ---------------------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Tag tag;

			tag = this.tagService.create();
			result = this.createEditModelAndView(tag);

			return result;
		}
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Tag tag) {
		ModelAndView result;

		result = this.createEditModelAndView(tag, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Tag tag,
			final String message) {
		ModelAndView result;
//		final Collection<Value> value;
//		value = this.valueService.findAll();
		result = new ModelAndView("tag/administrator/edit");
//		result.addObject("value", value);
		result.addObject("tag", tag);
		result.addObject("message", message);
		return result;
	}
	
}
