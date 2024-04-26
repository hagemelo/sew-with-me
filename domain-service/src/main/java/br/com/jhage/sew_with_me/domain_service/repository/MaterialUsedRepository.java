package br.com.jhage.sew_with_me.domain_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhage.sew_with_me.domain_service.model.MaterialUsed;

/***
 * 
 * @author Alexsander Melo
 * @since 31/03/2024
 *
 */
@Repository
public interface MaterialUsedRepository extends JpaRepository<MaterialUsed, Long>{

}
