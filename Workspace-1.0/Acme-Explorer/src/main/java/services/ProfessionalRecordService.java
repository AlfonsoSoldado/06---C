package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.Curriculum;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class ProfessionalRecordService {

	// Managed repository

	@Autowired
	private ProfessionalRecordRepository profesionalRecordRepository;

	// Supporting services
	
	@Autowired
	private RangerService rangerService;

	// Constructors

	public ProfessionalRecordService() {
		super();
	}

	// Simple CRUD methods
	
	public ProfessionalRecord create(){
		ProfessionalRecord res;
		
		res = new ProfessionalRecord();
		return res;
	}
	
	public ProfessionalRecord findOne(int profesionalRecord) {
		Assert.isTrue(profesionalRecord != 0);
		ProfessionalRecord res;
		res = this.profesionalRecordRepository.findOne(profesionalRecord);
		//Assert.notNull(res);
		return res;
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> res;
		
		res = this.profesionalRecordRepository.findAll();
		//Assert.notNull(res);
		return res;
	}

	public ProfessionalRecord save(ProfessionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		
		ProfessionalRecord res;
		
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<ProfessionalRecord> conj = c.getProfessionalRecord();
		conj.add(profesionalRecord);
		
		res = this.profesionalRecordRepository.save(profesionalRecord);
		c.setProfessionalRecord(conj);
		return res;
	}

	public void delete(ProfessionalRecord profesionalRecord) {
		Assert.notNull(profesionalRecord);
		Assert.isTrue(profesionalRecord.getId() != 0);
		Assert.isTrue(this.profesionalRecordRepository.exists(profesionalRecord
				.getId()));
		
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<ProfessionalRecord> conj = c.getProfessionalRecord();
		
		conj.remove(profesionalRecord);
		c.setProfessionalRecord(conj);
		
		this.profesionalRecordRepository.delete(profesionalRecord);
	}

	// Other business methods

}
