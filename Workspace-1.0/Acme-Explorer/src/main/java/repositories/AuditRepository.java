package repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer>{
	
	//b2
	@Query("select  min(cast((select count(audit) from Audit audit where audit.trip=t1) as int )), max(cast((select count(audit) from Audit audit where audit.trip=t1) as int )), avg(cast((select count(audit) from Audit audit where audit.trip=t1) as float )), sqrt(sum((select count(audit) from Audit audit where audit.trip=t1)*(select count(audit) from Audit audit where audit.trip=t1))/(select count(t2) from Trip t2)-avg(cast((select count(audit) from Audit audit where audit.trip=t1) as float ))*avg(cast((select count(audit) from Audit audit where audit.trip=t1) as float ))) from Trip t1")
	Double[] avgMinMaxSqtr4();
}
