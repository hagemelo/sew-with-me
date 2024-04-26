package br.com.jhage.sew_with_me.domain_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jhage.sew_with_me.domain_service.model.Client;

/***
 * 
 * @author Alexsander Melo
 * @since 31/03/2024
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select c " +
		   "from Client c "+
		   "where name like :name")
	public Client loadByName(@Param("name") String name);

}
