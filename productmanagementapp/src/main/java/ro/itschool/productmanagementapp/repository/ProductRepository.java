package ro.itschool.productmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.productmanagementapp.entity.ProductModel;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel , Integer> {


}
