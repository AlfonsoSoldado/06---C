package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Ranger extends Actor {

	// Constructors

	public Ranger() {
		super();
	}
	
	// Attributes
	
//	private Boolean suspicious;
//
//	@NotNull
//	public Boolean getSuspicious() {
//		return suspicious;
//	}
//
//	public void setSuspicious(Boolean suspicious) {
//		this.suspicious = suspicious;
//	}

	// Relationships

	private Curriculum curriculum;
	private Collection<Trip> trip;

	@Valid
	@OneToOne(mappedBy = "ranger", optional = true)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Valid
	@OneToMany(mappedBy = "ranger")
	public Collection<Trip> getTrip() {
		return trip;
	}

	public void setTrip(Collection<Trip> trip) {
		this.trip = trip;
	}

}
