package br.com.jhage.sew_with_me.domain_service.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class OrderException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT= "ERROR_ACTION_ABRUPT";

	public Logger inicializarLogger() {
		return LogManager.getLogger(OrderException.class);
	}

	public OrderException() {
		super(DEFAULT);
		this.inicializarLogger().error(DEFAULT);
	}
	
	public OrderException(String message) {
		super(message);
		this.inicializarLogger().error(message);
	}

	public OrderException(final Exception cause) {

		super(DEFAULT, cause);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
	}

	public OrderException(final Throwable cause) {

		super(DEFAULT);
		this.inicializarLogger().error(
				DEFAULT + " " + cause.getMessage());
	}

	public ResponseEntity<RespostaErro> respostaErro(){
		
		return new ResponseEntity<RespostaErro>(new RespostaErro(this.getMessage()),  HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Override
	public String getMessage() {

		String message = super.getMessage();
		if (message == null || message.isEmpty()) {
			message = DEFAULT;
		}
		return message;
	}

}