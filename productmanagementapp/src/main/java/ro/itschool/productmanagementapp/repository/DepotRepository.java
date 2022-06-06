package ro.itschool.productmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.productmanagementapp.entity.DepotModel;


@Repository
public interface DepotRepository  extends JpaRepository<DepotModel, Integer> {


}
