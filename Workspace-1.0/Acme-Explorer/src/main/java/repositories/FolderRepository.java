package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
	
	@Query("select f from Actor a join a.folders f where f.customFolder = null and a.id=?1")
	Collection<Folder> findFolders(int actorId);
	
}
