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

import services.MiscellaneousRecordService;
import controllers.AbstractController;
import domain.MiscellaneousRecord;

@Controller
@RequestMapping("/miscellaneousRecord/ranger")
public class MiscellaneousRecordRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private MiscellaneousRecordService miscellaneousRecordService;

	// Constructors ---------------------------------------------------------

	public MiscellaneousRecordRangerController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int miscellaneousRecordId) {
		ModelAndView result;
		MiscellaneousRecord t;

		t = miscellaneousRecordService.findOne(miscellaneousRecordId);
		Assert.notNull(t);
		result = this.createEditModelAndView(t);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MiscellaneousRecord miscellaneousRecord, BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(miscellaneousRecord,
					"miscellaneousRecord.params.error");
		} else
			try {
				this.miscellaneousRecordService.save(miscellaneousRecord);
				res = new ModelAndView("redirect:../../curriculum/ranger/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(miscellaneousRecord,
						"miscellaneousRecord.commit.error");
			}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(MiscellaneousRecord miscellaneousRecord, BindingResult binding) {
		ModelAndView res;

		try {
			this.miscellaneousRecordService.delete(miscellaneousRecord);
			res = new ModelAndView("redirect:../../curriculum/ranger/display.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(miscellaneousRecord,
					"miscellaneousRecord.commit.error");
		}

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MiscellaneousRecord t;

		t = this.miscellaneousRecordService.create();
		result = this.createEditModelAndView(t);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord) {
		ModelAndView result;
		result = this.createEditModelAndView(miscellaneousRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final MiscellaneousRecord miscellaneousRecord,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("miscellaneousRecord/edit");
		result.addObject("miscellaneousRecord", miscellaneousRecord);
		result.addObject("message", message);
		return result;
	}
}
