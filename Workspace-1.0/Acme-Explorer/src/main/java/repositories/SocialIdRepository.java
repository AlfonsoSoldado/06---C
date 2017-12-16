package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SocialId;

@Repository
public interface SocialIdRepository extends JpaRepository<SocialId, Integer> {

	@Query("select s from Actor a join a.socialId s where a.id=?1")
	Collection<SocialId> findSocialIds(int actorId);

}
