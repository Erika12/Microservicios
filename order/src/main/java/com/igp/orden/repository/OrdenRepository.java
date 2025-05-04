package com.igp.orden.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.igp.orden.entities.Orden;



public interface  OrdenRepository extends JpaRepository<Orden, Long>  {
     List<Orden> findByIdCustomer(Long idCustomer);
}
