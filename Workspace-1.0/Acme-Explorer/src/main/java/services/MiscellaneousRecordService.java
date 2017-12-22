package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.Curriculum;
import domain.MiscellaneousRecord;
import domain.Ranger;

@Service
@Transactional
public class MiscellaneousRecordService {

	// Managed repository

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;

	// Supporting services
	
	@Autowired
	private RangerService rangerService;

	// Constructors

	public MiscellaneousRecordService() {
		super();
	}

	// Simple CRUD methods

	public MiscellaneousRecord create() {
		MiscellaneousRecord res;

		res = new MiscellaneousRecord();
		return res;
	}

	public MiscellaneousRecord findOne(int miscellaneousRecord) {
		Assert.isTrue(miscellaneousRecord != 0);
		MiscellaneousRecord res;
		res = this.miscellaneousRecordRepository.findOne(miscellaneousRecord);
		return res;
	}

	public Collection<MiscellaneousRecord> findAll() {
		Collection<MiscellaneousRecord> res;

		res = this.miscellaneousRecordRepository.findAll();
		return res;
	}

	public MiscellaneousRecord save(MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);

		MiscellaneousRecord res;
		
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<MiscellaneousRecord> conj = c.getMiscellaneousRecord();
		conj.add(miscellaneousRecord);
		
		res = this.miscellaneousRecordRepository.save(miscellaneousRecord);
		c.setMiscellaneousRecord(conj);
		return res;
	}

	public void delete(MiscellaneousRecord miscellaneousRecord) {
		Assert.notNull(miscellaneousRecord);
		Assert.isTrue(miscellaneousRecord.getId() != 0);
		Assert.isTrue(this.miscellaneousRecordRepository
				.exists(miscellaneousRecord.getId()));
		
		Ranger r = rangerService.findByPrincipal();
		Curriculum c = r.getCurriculum();
		Collection<MiscellaneousRecord> conj = c.getMiscellaneousRecord();
		
		conj.remove(miscellaneousRecord);
		c.setMiscellaneousRecord(conj);

		this.miscellaneousRecordRepository.delete(miscellaneousRecord);
	}

	// Other business methods

}
