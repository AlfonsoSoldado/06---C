package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.Curriculum;
import domain.EducationRecord;
import domain.Ranger;

@Service
@Transactional
public class EducationRecordService {
	
	// Managed repository

	@Autowired
	private EducationRecordRepository educationRecordRepository;
	
	// Supporting services
	
	@Autowired
	private RangerService rangerService;
	
	// Constructors
	
	public EducationRecordService(){
		super();
	}
	
	// Simple CRUD methods
	
	public EducationRecord create(){
		EducationRecord res;
		
		res = new EducationRecord();
		return res;
	}
	
	public EducationRecord findOne(int educationRecord) {
		Assert.isTrue(educationRecord != 0);
		EducationRecord res;
		res = this.educationRecordRepository.findOne(educationRecord);
		return res;
	}
	
	public Collection<EducationRecord> findAll() {
		Collection<EducationRecord> res;
		res = this.educationRecordRepository.findAll();
		return res;
	}
	
	public EducationRecord save(EducationRecord educationRecord) {
		Assert.notNull(educationRecord);
		
		EducationRecord res;
				
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<EducationRecord> conj = c.getEducationRecord();
		
		conj.add(educationRecord);
		c.setEducationRecord(conj);
		
		res = this.educationRecordRepository.save(educationRecord);
		
		return res;
	}
	
	public void delete(EducationRecord educationRecord) {
		Assert.notNull(educationRecord);
		Assert.isTrue(educationRecord.getId() != 0);
		Assert.isTrue(this.educationRecordRepository.exists(educationRecord.getId()));
		
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<EducationRecord> conj = c.getEducationRecord();
		
		conj.remove(educationRecord);
		c.setEducationRecord(conj);
		
		this.educationRecordRepository.delete(educationRecord);
	}
	
	// Other business methods
	
}
