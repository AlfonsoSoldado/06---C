package controllers.ranger;

import java.util.ArrayList;
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

import services.CurriculumService;
import services.RangerService;
import controllers.AbstractController;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Controller
@RequestMapping("/curriculum/ranger")
public class CurriculumRangerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private RangerService rangerService;

	// Constructors ---------------------------------------------------------

	public CurriculumRangerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Collection<Curriculum> curriculums = new ArrayList<Curriculum>();
		Collection<PersonalRecord> personalR = new ArrayList<PersonalRecord>();
		Collection<ProfessionalRecord> professionalR = new ArrayList<ProfessionalRecord>();
		Collection<EducationRecord> educationR = new ArrayList<EducationRecord>();
		Collection<MiscellaneousRecord> miscellaneousR = new ArrayList<MiscellaneousRecord>();
		Collection<EndorserRecord> endorserR = new ArrayList<EndorserRecord>();

		Ranger r = rangerService.findByPrincipal();
		int rangerId = r.getId();
		curriculums = curriculumService.getCurriculumByRanger(rangerId);

		for (Curriculum c : curriculums) {
			personalR.add(c.getPersonalRecord());
			professionalR.addAll(c.getProfessionalRecord());
			educationR.addAll(c.getEducationRecord());
			miscellaneousR.addAll(c.getMiscellaneousRecord());
			endorserR.addAll(c.getEndorserRecord());

		}

		Ranger currentRanger = rangerService.findByPrincipal();
		int currentRangerId = currentRanger.getId();

		int currentCurriculumId = 0;

		if (currentRanger.getCurriculum() != null) {
			currentCurriculumId = currentRanger.getCurriculum().getId();
		}

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculums);
		result.addObject("currentRangerId", currentRangerId);
		result.addObject("currentCurriculumId", currentCurriculumId);
		result.addObject("personalRecord", personalR);
		result.addObject("professionalRecord", professionalR);
		result.addObject("educationRecord", educationR);
		result.addObject("miscellaneousRecord", miscellaneousR);
		result.addObject("endorserRecord", endorserR);
		result.addObject("requestURI", "curriculum/ranger/display.do");

		return result;
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
			res = this.createEditModelAndView(curriculum,
					"curriculum.params.error");
		} else
			try {
				this.curriculumService.save(curriculum);
				res = new ModelAndView(
						"redirect:../../curriculum/ranger/display.do");
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
			res = new ModelAndView(
					"redirect:../../curriculum/ranger/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(curriculum, "curriculum.commit.error");
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
