package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	
	@Query("select c from Curriculum c join c.ranger r where r.id = ?1")
	Collection<Curriculum> findCurriculumByRanger(int rangerId);
	
}
