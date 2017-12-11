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

import services.EndorserRecordService;
import controllers.AbstractController;
import domain.EndorserRecord;

@Controller
@RequestMapping("/endorserRecord/ranger")
public class EndorserRecordRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private EndorserRecordService endorserRecordService;

	// Constructors ---------------------------------------------------------

	public EndorserRecordRangerController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int endorserRecordId) {
		ModelAndView result;
		EndorserRecord t;

		t = endorserRecordService.findOne(endorserRecordId);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid EndorserRecord endorserRecord, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(endorserRecord,
					"endorserRecord.params.error");
		} else
			try {
				this.endorserRecordService.save(endorserRecord);
				res = new ModelAndView("redirect:../../curriculum/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(endorserRecord,
						"endorserRecord.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(EndorserRecord endorserRecord, BindingResult binding) {
		ModelAndView res;

		try {
			this.endorserRecordService.delete(endorserRecord);
			res = new ModelAndView("redirect:../../curriculum/display.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(endorserRecord,
					"endorserRecord.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		EndorserRecord t;

		t = this.endorserRecordService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(endorserRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final EndorserRecord endorserRecord,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("endorserRecord/edit");
		result.addObject("endorserRecord", endorserRecord);
		result.addObject("message", message);
		return result;
	}
}
