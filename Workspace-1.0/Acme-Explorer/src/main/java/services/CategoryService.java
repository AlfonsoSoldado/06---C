package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Trip;

@Service
@Transactional
public class CategoryService {
	
	// Managed repository

	@Autowired
	private CategoryRepository categoryRepository;
	
	// Supporting services
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructors
	
	public CategoryService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Category create() {
		administratorService.checkAuthority();
		
		Category res;
		res = new Category();
		
		Collection<Category> categories = new ArrayList<Category>();
		Collection<Trip> trip = new ArrayList<Trip>();
		Trip trip1;
		trip1 = new Trip();
		trip.add(trip1);
		
		res.setCategories(categories);
		res.setTrip(trip);
		
		return res;
	}
	
	public Collection<Category> findAll() {
		Collection<Category> res;
		res = this.categoryRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Category findOne(int category) {
		Assert.isTrue(category != 0);
		Category res;
		res = this.categoryRepository.findOne(category);
		Assert.notNull(res);
		return res;
	}
	
	public Category save(Category category) {
		administratorService.checkAuthority();
		Assert.notNull(category);
		Collection<String> names = new ArrayList<String>();
		Collection<Category> categories;
		categories = category.getCategoryParent().getCategories();
		String name;
		for(Category c : categories) {
			name = c.getName();
			names.add(name);
			Assert.isTrue(!names.contains(category.getName()));
		}
		Category res;
		res = this.categoryRepository.save(category);
		return res;
	}
	
	public void delete(Category category) {
		administratorService.checkAuthority();
		Assert.notNull(category);
		Assert.isTrue(category.getId() != 0);
		Assert.isTrue(this.categoryRepository.exists(category.getId()));
		this.categoryRepository.delete(category);
	}
	
	// Other business methods
	
	public Collection<Category> getCategoryChildren() {
		Collection<Category> categories;
		Category c;
		c = this.categoryRepository.findCategories();
		Assert.notNull(c);
		categories = c.getCategories();
		Assert.notNull(categories);
		return categories;
	}
	
	public Category getCategoryRoot() {
		Category c;
		c = this.categoryRepository.findCategories();
		Assert.notNull(c);
		return c;
	}
	
}
