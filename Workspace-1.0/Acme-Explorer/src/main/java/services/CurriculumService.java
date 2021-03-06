package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.PersonalRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class CurriculumService {
	
	// Managed repository

	@Autowired
	private CurriculumRepository curriculumRepository;
	
	// Supporting services
	
	@Autowired
	private RangerService rangerService;
	
	// Constructors
	
	public CurriculumService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Curriculum create() {
		Curriculum res = new Curriculum();
		Collection<ProfessionalRecord> professionalRecord = new ArrayList<ProfessionalRecord>();
		Collection<EducationRecord> educationRecord = new ArrayList<EducationRecord>();
		Collection<EndorserRecord> endorserRecord = new ArrayList<EndorserRecord>();
		Collection<MiscellaneousRecord> miscellaneousRecord = new ArrayList<MiscellaneousRecord>();
		PersonalRecord personalRecord = new PersonalRecord();
		
		res.setTicker(this.generatedTicker());
		res.setProfessionalRecord(professionalRecord);
		res.setEducationRecord(educationRecord);
		res.setEndorserRecord(endorserRecord);
		res.setMiscellaneousRecord(miscellaneousRecord);
		res.setPersonalRecord(personalRecord);
		
		return res;
	}
	
	public Collection<Curriculum> findAll() {
		//rangerService.checkAuthority();
		
		Collection<Curriculum> res;
		res = this.curriculumRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum findOne(int curriculum) {
		//rangerService.checkAuthority();
		
		Assert.isTrue(curriculum != 0);
		Curriculum res;
		res = this.curriculumRepository.findOne(curriculum);
		Assert.notNull(res);
		return res;
	}
	
	public Curriculum save(Curriculum curriculum) {
		
		Ranger ranger;
		ranger = this.rangerService.findByPrincipal();
		
		Assert.notNull(curriculum);
		curriculum.setRanger(ranger);
		
		Curriculum res;
		res = this.curriculumRepository.save(curriculum);
		
		ranger.setCurriculum(res);
		res.setRanger(ranger);
		
		return res;
	}
	
	public void delete(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Assert.isTrue(curriculum.getId() != 0);
		Assert.isTrue(this.curriculumRepository.exists(curriculum.getId()));
		
		this.curriculumRepository.delete(curriculum);
	}
	
	// Other business methods
	
	public String generatedTicker() {
		String ticker;
		LocalDate date;
		String letters;
		Random r;
		
		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		r = new Random();
		date = new LocalDate();
		
		ticker = String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) + 
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear())
					+ String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-";
		for (int i = 0; i < 4; i++)
			ticker = ticker + letters.charAt(r.nextInt(letters.length()));

		return ticker;
	}
	
	public Collection<Curriculum> getCurriculumByRanger(int rangerId){
		Collection<Curriculum> res;
		
		res = this.curriculumRepository.findCurriculumByRanger(rangerId);
		
		return res;
	}
	
}
