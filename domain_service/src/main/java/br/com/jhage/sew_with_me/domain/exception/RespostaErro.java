package br.com.jhage.sew_with_me.domain.exception;

public class RespostaErro {
	
	private String mensagem;

	public RespostaErro() {
	}

	public RespostaErro(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
