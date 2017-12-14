package controllers.ranger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import controllers.AbstractController;
import domain.Curriculum;

@Controller
@RequestMapping("/curriculum/ranger")
public class CurriculumRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CurriculumService curriculumService;

	// Constructors ---------------------------------------------------------

	public CurriculumRangerController() {
		super();
	}
	
	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;

		curriculum = curriculumService.findOne(curriculumId);
		Assert.notNull(curriculum);
		result = this.createEditModelAndView(curriculum);

		return result;
	}
	
	// Saving --------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldError());
			res = this.createEditModelAndView(curriculum,
					"curriculum.params.error");
		} else
			try {
				this.curriculumService.save(curriculum);
				res = new ModelAndView("redirect:../../curriculum/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(curriculum,
						"curriculum.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Curriculum curriculum, BindingResult binding) {
		ModelAndView res;

		try {
			this.curriculumService.delete(curriculum);
			res = new ModelAndView("redirect:../../curriculum/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(curriculum,
					"curriculum.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Curriculum t;

		t = this.curriculumService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
		ModelAndView result;
		result = this.createEditModelAndView(curriculum, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Curriculum curriculum,
			final String message) {
		
		ModelAndView result;
		result = new ModelAndView("curriculum/ranger/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", message);
		return result;
	}
}
