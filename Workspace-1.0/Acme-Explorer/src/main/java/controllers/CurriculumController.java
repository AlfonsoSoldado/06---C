package controllers;

import java.util.ArrayList;
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
import services.RangerService;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CurriculumService curriculumService;
	
	@Autowired
	private RangerService rangerService;

	// Constructors ---------------------------------------------------------

	public CurriculumController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView list() {
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
		
		for(Curriculum c: curriculums){
			personalR.add(c.getPersonalRecord());
			professionalR.addAll(c.getProfessionalRecord());
			educationR.addAll(c.getEducationRecord());
			miscellaneousR.addAll(c.getMiscellaneousRecord());
			endorserR.addAll(c.getEndorserRecord());
			
		}

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
