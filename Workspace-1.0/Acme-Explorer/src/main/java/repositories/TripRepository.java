package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	// 12.1 (listing)
	@Query("select t from Trip t where t.manager.id = ?1")
	Collection<Trip> findTripsByManager(int id);

	// 13.1
	@Query("select t from Trip t join t.application a where a.explorer.id = ?1")
	Collection<Trip> findTripsByExplorer(int id);

	// 13.4
	@Query("select t from Trip t join t.application a where a.status = 'ACCEPTED' and t.tripStart > CURRENT_DATE")
	Collection<Trip> findTripsAccepted();

	// 10.2
	@Query("select t from Trip t where t.publication!=null and t.cancelled=false")
	Collection<Trip> browseTripsByActor();

	// 10.4 
	@Query("select t from Trip t join t.category c where c.id = ?1")
	Collection<Trip> browseTripsByCategories(int id);

	// 10.3
	@Query("select t from Trip t where t.ticker like ?1 or t.title like ?1 or t.description like ?1")
	Collection<Trip> findTrips(String search);
	
	// 12.3
	@Query("select t from Trip t where t.publication < CURRENT_DATE and t.tripStart < CURRENT_DATE and t.cancelled = false")
	Collection<Trip> cancelTrip();
	
	//b3
	@Query("select count(trip1)/((select count(trip2) from Trip trip2)+0.0) from Trip trip1 where cast((select count(audit) from Audit audit where audit.trip=trip1) as int )=1")
	Double avgMinMaxSqtr5();
}
