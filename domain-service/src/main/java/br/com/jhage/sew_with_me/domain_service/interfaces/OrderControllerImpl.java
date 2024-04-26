package br.com.jhage.sew_with_me.domain_service.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.sew_with_me.domain_service.domain.NewOrderUseCase;
import br.com.jhage.sew_with_me.domain_service.domain.OrderController;
import br.com.jhage.sew_with_me.domain_service.exception.OrderException;
import br.com.jhage.sew_with_me.domain_service.exception.RespostaErro;
import br.com.jhage.sew_with_me.domain_service.model.Order;


@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@RequestMapping("/order")
@RestController
public class OrderControllerImpl implements OrderController{
	
	@Autowired
	private NewOrderUseCase useCase;
	
	
	@ExceptionHandler(OrderException.class)
	public  ResponseEntity<RespostaErro> campoObrigatorioExceptionHandler(OrderException ex){
		return ex.respostaErro();
	}
	
	@PostMapping(path= "/newOrder")
	@Transactional
	public @ResponseBody Order newOrder(@RequestBody Order order) throws OrderException{
		
		return this.useCase.execute(order);
	}

}
