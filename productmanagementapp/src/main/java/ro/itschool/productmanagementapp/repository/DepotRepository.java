package ro.itschool.productmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.productmanagementapp.entity.DepotModel;

import javax.transaction.Transactional;

@Repository
public interface DepotRepository  extends JpaRepository<DepotModel, Integer> {

    @Transactional //Needed for modifing database data
    @Modifying //Needed for modifing database data
    @Query(value="delete from usermanagement.department where name = :departmentName", nativeQuery = true)
    void deleteDeparmentByName(@Param("departmentName") String name);
}
