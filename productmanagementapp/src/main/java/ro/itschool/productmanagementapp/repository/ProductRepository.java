package ro.itschool.productmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.productmanagementapp.entity.ProductModel;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel , Integer> {

    @Transactional //Needed for modifing database data
    @Modifying //Needed for modifing database data
    @Query(value = "select * from productmanagement.products where last_name like :param%", nativeQuery = true)
    List<ProductModel> searchByLastNameSQL(@Param("param") String param);

}
