package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Emergency;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Integer> {

	@Query("select e from Explorer m join m.emergency e where m.id = ?1")
	Collection<Emergency> findEmergencyByExplorer(int id);

}
