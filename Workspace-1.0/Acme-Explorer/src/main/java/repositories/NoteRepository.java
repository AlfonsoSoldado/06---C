package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	// 33
	@Query("select n from Note n where n.auditor.id = ?1")
	Collection<Note> findNotesByAuditor(int id);
	
	//b1
	@Query("select  min(cast((select count(note) from Note note where note.trip=t1) as int )), max(cast((select count(note) from Note note where note.trip=t1) as int )), avg(cast((select count(note) from Note note where note.trip=t1) as float )), sqrt(sum((select count(note) from Note note where note.trip=t1)*(select count(note) from Note note where note.trip=t1))/(select count(trip) from Trip trip)-avg(cast((select count(note) from Note note where note.trip=t1) as float ))*avg(cast((select count(note) from Note note where note.trip=t1) as float ))) from Trip t1")
	Double[] avgMinMaxSqtr3();
}
