package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Constructors
	
	public Configuration() {
		super();
	}
	
	// Attributes
	
	private String banner;
	private String message;
	private Collection<String> spamWords;
	private Double tax;
	private String countryCode;
	private Collection<String> catalogueTag;
	private Collection<Category> treeCategory;
	private Collection<String> catalogueText;
	private Collection<String> other;
	private Integer cache;
	private Integer numberPage;
	
	@URL
	@NotBlank
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@NotBlank
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@NotEmpty
	@ElementCollection
	public Collection<String> getSpamWords() {
		return spamWords;
	}
	
	public void setSpamWords(Collection<String> spamWords) {
		this.spamWords = spamWords;
	}
	
	@Min(0)
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	@NotBlank
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@NotEmpty
	@ElementCollection
	public Collection<String> getCatalogueTag() {
		return catalogueTag;
	}
	public void setCatalogueTag(Collection<String> catalogueTag) {
		this.catalogueTag = catalogueTag;
	}
	
	@NotEmpty
	@ElementCollection
	public Collection<Category> getTreeCategory() {
		return treeCategory;
	}
	public void setTreeCategory(Collection<Category> treeCategory) {
		this.treeCategory = treeCategory;
	}
	
	@NotEmpty
	@ElementCollection
	public Collection<String> getCatalogueText() {
		return catalogueText;
	}
	public void setCatalogueText(Collection<String> catalogueText) {
		this.catalogueText = catalogueText;
	}
	
	@ElementCollection
	public Collection<String> getOther() {
		return other;
	}
	public void setOther(Collection<String> other) {
		this.other = other;
	}
	
	@Range(min = 1, max = 24)
	public Integer getCache() {
		return cache;
	}
	
	public void setCache(Integer cache) {
		this.cache = cache;
	}
	
	@Range(min = 10, max = 100)
	public Integer getNumberPage() {
		return numberPage;
	}
	
	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}
}
