package br.com.jhage.sew_with_me.domain_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhage.sew_with_me.domain_service.model.Order;

/***
 * 
 * @author Alexsander Melo
 * @since 31/03/2024
 *
 */
@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{

}
