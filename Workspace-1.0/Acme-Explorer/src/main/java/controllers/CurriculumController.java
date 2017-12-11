package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EducationRecordService;
import services.EndorserRecordService;
import services.MiscellaneousRecordService;
import services.PersonalRecordService;
import services.ProfessionalRecordService;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CurriculumService curriculumService;

	@Autowired
	private PersonalRecordService personalRecordService;

	@Autowired
	private ProfessionalRecordService professionalRecordService;

	@Autowired
	private EducationRecordService educationRecordService;

	@Autowired
	private MiscellaneousRecordService miscellaneousRecordService;

	@Autowired
	private EndorserRecordService endorserRecordService;

	// Constructors ---------------------------------------------------------

	public CurriculumController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curriculum> curriculums;
		Collection<PersonalRecord> personalR;
		Collection<ProfessionalRecord> professionalR;
		Collection<EducationRecord> educationR;
		Collection<MiscellaneousRecord> miscellaneousR;
		Collection<EndorserRecord> endorserR;

		curriculums = curriculumService.findAll();
		personalR = personalRecordService.findAll();
		professionalR = professionalRecordService.findAll();
		educationR = educationRecordService.findAll();
		miscellaneousR = miscellaneousRecordService.findAll();
		endorserR = endorserRecordService.findAll();

		result = new ModelAndView("curriculum/display");
		result.addObject("curriculum", curriculums);
		result.addObject("personalRecord", personalR);
		result.addObject("professionalRecord", professionalR);
		result.addObject("educationRecord", educationR);
		result.addObject("miscellaneousRecord", miscellaneousR);
		result.addObject("endorserRecord", endorserR);
		result.addObject("requestURI", "curriculum/display.do");

		return result;
	}
}
