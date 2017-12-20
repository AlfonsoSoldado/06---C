package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Explorer;
import domain.Manager;

@Service
@Transactional
public class ApplicationService {

	// Managed repository

	@Autowired
	private ApplicationRepository applicationRepository;

	// Supporting services

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ExplorerService explorerService;

	// Constructors

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods

	public Application create() {
		Application res = new Application();
		
		String status;
		status = "PENDING";
		String comment = new String();
		
		Explorer explorer = new Explorer();
		explorer = explorerService.findByPrincipal();
		res.setExplorer(explorer);
		
		Date moment = new Date(System.currentTimeMillis() - 1);
		res.setMoment(moment);
		
		res.setStatus(status);
		res.setComment(comment);
		return res;
	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);
		Application res;
		res = this.applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}

	public Application save(Application application) {
		Application res;
		
		res = this.applicationRepository.save(application);
		Explorer explorer = new Explorer();
		explorer = res.getExplorer();
		Collection<Application> applications = new ArrayList<Application>();
		applications = explorer.getApplication();
		applications.add(res);
		return res;
	}

	public void delete(Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(this.applicationRepository.exists(application.getId()));
		this.applicationRepository.delete(application);
	}

	// Other business methods

	// 12.2 (changing)
	
	 public void changingStatus(Application a, String status) {
		 managerService.checkAuthority();
		 Assert.notNull(a);
		 Assert.notNull(status);
		 Assert.isTrue(status.equals("REJECTED") || status.equals("DUE"));
		 
		 Manager m = this.managerService.findByPrincipal();
		 
		 Assert.notNull(m);
		 
		 Collection<Application> applications = new ArrayList<Application>();
		 applications = applicationRepository.findListApplicationPending(m.getId());
		 
		 if(applications.contains(a)){
			 a.setStatus(status);
		 }
	 }

	// 12.2 (listing)
	 
	public Collection<Application> findListApplication(Manager manager) {
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findApplicationByManager(manager.getId()));
		
		return res;
	}

	// 13.2
	
	public Collection<Application> findApplicationByExplorer(Explorer explorer) {
		explorerService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		
		res.addAll(applicationRepository.findApplicationByExplorer(explorer.getId()));
		Assert.notNull(res);
		
		return res;
	}
	
	// 13.3
	
	public void applicationAccepted(Application application){
		explorerService.checkAuthority();
		Assert.notNull(application);
		
		if(application.getCreditCard() != null){
			application.setStatus("ACCEPTED");
		}
	}
	
	public boolean aplicationAcceptedStartingDateNotPassed(Application application){
		Boolean res = false;
		Date date = new Date(System.currentTimeMillis());
		if(application.getTrip().getTripStart().after(date)){
			res = true;
		}
		return res;
	}
	
//	public void applicationAccepted(CC creditCard, Application application){
//		explorerService.checkAuthority();
//		Assert.notNull(creditCard);
//		Assert.notNull(application);
//		
//		CC creditCardIntermedia = application.getCreditCard();
//		
//		Collection<Application> applications = new ArrayList<>();
//		applications = applicationRepository.findListApplicationDue();
//		
//		if(applications.contains(application)){
//			application.setCreditCard(creditCard);
//			if(application.getCreditCard().equals(creditCardIntermedia)){
//				application.setStatus("ACCEPTED");
//			}
//		}
//	}
}
