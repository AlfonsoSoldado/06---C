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

import services.PersonalRecordService;
import controllers.AbstractController;
import domain.PersonalRecord;

@Controller
@RequestMapping("/personalRecord/ranger")
public class PersonalRecordRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private PersonalRecordService personalRecordService;

	// Constructors ---------------------------------------------------------

	public PersonalRecordRangerController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int personalRecordId) {
		ModelAndView result;
		PersonalRecord t;

		t = personalRecordService.findOne(personalRecordId);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PersonalRecord personalRecord, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(personalRecord,
					"personalRecord.params.error");
		} else
			try {
				this.personalRecordService.save(personalRecord);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(personalRecord,
						"personalRecord.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(PersonalRecord personalRecord, BindingResult binding) {
		ModelAndView res;

		try {
			this.personalRecordService.delete(personalRecord);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(personalRecord,
					"personalRecord.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		PersonalRecord t;

		t = this.personalRecordService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(personalRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final PersonalRecord personalRecord,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("personalRecord/edit");
		result.addObject("personalRecord", personalRecord);
		result.addObject("message", message);
		return result;
	}
}
