package br.com.jhage.sew_with_me.domain.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.sew_with_me.domain.domain.OrderController;
import br.com.jhage.sew_with_me.domain.exception.OrderException;
import br.com.jhage.sew_with_me.domain.exception.RespostaErro;
import br.com.jhage.sew_with_me.domain.model.Order;


@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3000)
@RequestMapping("/order")
@RestController
public class OrderControllerImpl implements OrderController{
	
	
	@ExceptionHandler(OrderException.class)
	public  ResponseEntity<RespostaErro> campoObrigatorioExceptionHandler(OrderException ex){
		return ex.respostaErro();
	}
	
	@PostMapping(path= "/newOrder")
	@Transactional
	public @ResponseBody Order newOrder(@RequestBody Order order) throws OrderException{
		
		System.out.println("Funcionou");
		
		return null;
	}

}
