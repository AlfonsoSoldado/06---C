package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.TagService;
import services.ValueService;
import domain.Tag;
import domain.Value;

@Controller
@RequestMapping("/tag")
public class TagController extends AbstractController {
	// Services -------------------------------------------------------------

	@Autowired
	private TagService tagService;
	
	@Autowired
	private ValueService valueService;

	// Constructors ---------------------------------------------------------

	public TagController() {
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
		result.addObject("requestURI", "tag/administrator/list.do");
		
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
	
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Tag tag) {
		ModelAndView result;

		result = this.createEditModelAndView(tag, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Tag tag,
			final String message) {
		ModelAndView result;
		final Collection<Value> value;
		value = this.valueService.findAll();
		result = new ModelAndView("tag/administrator/edit");
		result.addObject("value", value);
		result.addObject("tag", tag);
		result.addObject("message", message);
		return result;
	}
	
}
