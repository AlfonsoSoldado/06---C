package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity {

	// Constructors

	public Trip() {
		super();
	}

	// Attributes

	private String ticker;
	private String title;
	private String description;
	private Double price;
	private Collection<String> requirement;
	private Date publication;
	private Date tripStart;
	private Date tripEnd;
	private String reason;
	private Boolean cancelled;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[0-9]{6}-[A-Z]{4}")
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotEmpty
	@ElementCollection
	public Collection<String> getRequirement() {
		return requirement;
	}

	public void setRequirement(Collection<String> requirement) {
		this.requirement = requirement;
	}

	@Min(0)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPublication() {
		return publication;
	}

	public void setPublication(Date publication) {
		this.publication = publication;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getTripStart() {
		return tripStart;
	}

	public void setTripStart(Date tripStart) {
		this.tripStart = tripStart;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getTripEnd() {
		return tripEnd;
	}

	public void setTripEnd(Date tripEnd) {
		this.tripEnd = tripEnd;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	// Relationships

	private Ranger ranger;
	private Manager manager;
	private Collection<Stage> stage;
	private Category category;
	private LegalText legalText;
	private Collection<Application> application;
	private Collection<Value> value;

	@Valid
	@ManyToOne(optional = true)
	public LegalText getLegalText() {
		return legalText;
	}

	public void setLegalText(LegalText legalText) {
		this.legalText = legalText;
	}

	@Valid
	@ManyToOne(optional = true)
	public Ranger getRanger() {
		return ranger;
	}

	public void setRanger(Ranger ranger) {
		this.ranger = ranger;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Valid
	@OneToMany(mappedBy = "trip")
	public Collection<Value> getValue() {
		return value;
	}

	public void setValue(Collection<Value> value) {
		this.value = value;
	}

	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "trip",cascade = CascadeType.ALL)
	public Collection<Stage> getStage() {
		return stage;
	}

	public void setStage(Collection<Stage> stage) {
		this.stage = stage;
	}

	@Valid
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Valid
	@NotNull
	@OneToMany(mappedBy="trip")
	public Collection<Application> getApplication() {
		return application;
	}

	public void setApplication(Collection<Application> application) {
		this.application = application;
	}
}
