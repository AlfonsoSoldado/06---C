package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Emergency;
import domain.Explorer;
import domain.Finder;
import domain.Story;
import domain.Survival;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ExplorerServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ExplorerService explorerService;

	// Supporting services -----------------------

	@Autowired
	private SurvivalService survivalService;

	@Autowired
	private StoryService storyService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EmergencyService emergencyService;

	@Autowired
	private FinderService finderService;

	// Test --------------------------------------

	@Test
	public void testCreateExplorer() {
		Explorer explorer;
		explorer = this.explorerService.create();
		Assert.notNull(explorer);
	}

	@Test
	public void testFindAllExplorer() {
		Collection<Explorer> explorers;
		explorers = this.explorerService.findAll();
		Assert.notNull(explorers);
	}

	@Test
	public void testFindOneExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		Assert.notNull(explorer);
	}

	@Test
	public void testSaveExplorer() {
		authenticate("explorer1");
		Explorer explorer;
		explorer = this.explorerService.create();

		Collection<Emergency> emergency = new ArrayList<Emergency>();
		Collection<Story> story = new ArrayList<Story>();
		Collection<Survival> survival = new ArrayList<Survival>();
		Finder finder1;
		Emergency emergency1;
		Story story1;
		Survival survival1;
		Collection<Application> application = new ArrayList<Application>();
		Application application1;

		finder1 = this.finderService.findOne(super.getEntityId("finder1"));
		emergency1 = this.emergencyService.findOne(super
				.getEntityId("emergency1"));
		story1 = this.storyService.findOne(super.getEntityId("story1"));
		survival1 = this.survivalService
				.findOne(super.getEntityId("survival1"));
		application1 = this.applicationService.findOne(super
				.getEntityId("application1"));

		emergency.add(emergency1);
		story.add(story1);
		survival.add(survival1);
		application.add(application1);

		explorer.setFinder(finder1);
		explorer.setEmergency(emergency);
		explorer.setStories(story);
		explorer.setApplication(application);
		explorer.setSurvival(survival);
		explorer.setName("Ana");
		explorer.setSurname("Martin");
		explorer.setEmail("anaexpl@hotmail.com");

		this.explorerService.save(explorer);
		unauthenticate();
	}

	@Test
	public void testDeleteExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		this.explorerService.delete(explorer);
	}

	@Test
	public void testFindByPrincipalExplorer() {
		authenticate("explorer1");
		Explorer explorer;
		explorer = this.explorerService.findByPrincipal();
		Assert.notNull(explorer);
		unauthenticate();
	}
}
