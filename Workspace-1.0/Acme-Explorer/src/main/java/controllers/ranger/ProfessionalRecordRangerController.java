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

import services.ProfessionalRecordService;
import controllers.AbstractController;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/professionalRecord/ranger")
public class ProfessionalRecordRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ProfessionalRecordService professionalRecordService;

	// Constructors ---------------------------------------------------------

	public ProfessionalRecordRangerController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int professionalRecordId) {
		ModelAndView result;
		ProfessionalRecord t;

		t = professionalRecordService.findOne(professionalRecordId);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ProfessionalRecord professionalRecord, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(professionalRecord,
					"professionalRecord.params.error");
		} else
			try {
				this.professionalRecordService.save(professionalRecord);
				res = new ModelAndView("redirect:../../curriculum/ranger/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(professionalRecord,
						"professionalRecord.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ProfessionalRecord professionalRecord, BindingResult binding) {
		ModelAndView res;

		try {
			this.professionalRecordService.delete(professionalRecord);
			res = new ModelAndView("redirect:../../curriculum/ranger/display.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(professionalRecord,
					"professionalRecord.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ProfessionalRecord t;

		t = this.professionalRecordService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(professionalRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final ProfessionalRecord professionalRecord,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("professionalRecord/edit");
		result.addObject("professionalRecord", professionalRecord);
		result.addObject("message", message);
		return result;
	}
}
