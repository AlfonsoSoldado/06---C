package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Value;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>{
	
	@Query("select v from Value v join v.trip t where t.id = ?1")
	Collection<Value> findValueByTrip(int tripId);

}
