package ro.itschool.productmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.productmanagementapp.entity.ProductModel;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel , Integer> {

    //o metoda unde poti sa vezi in baza de date produsele din depozit dupa id
    @Query(value = "select * from productmanagementapp.products where depot_id = :param", nativeQuery = true)
    List<ProductModel> searchByDepotID(@Param("param") int param);

}
