package br.com.camillaeantonio.componente;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@ManagedBean
@ViewScoped
public class ContatoComp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 935873234066132938L;
	
	
	
	private String email = "";
	
	private String assunto = "";

	private String texto = "";

	private String nome = "";
	
	private String convidado = "";
	
	
	public void enviarMsg() {
		SimpleEmail email = new SimpleEmail();
		if (getNome() == null || getNome().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "O Campo Nome é Obrigatorio!"));
		}
		if (getEmail() == null || getEmail().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "O Campo E-mail é Obrigatorio!"));
		}
		if (getAssunto() == null || getAssunto().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "O Campo Assunto é Obrigatorio!"));
		}
		if (getTexto() == null || getTexto().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "O Campo Mensagem é Obrigatorio!"));
		} else {

			try {
				email.setHostName("smtp.gmail.com"); // o servidor SMTP para
														// envio
														// do
														// e-mail

				email.addTo("paduamendes@gmail.com", "padua");

				email.setFrom(getEmail(), getNome()+" [paduamendes.web]"); // remetente
				email.setSubject(getAssunto().trim()); // assunto do e-mail
				email.setMsg("Enviado por: " + getNome().trim() + " [" + getEmail().trim() + "]" + "\n\n MENSAGEM \n\n"
						+ getTexto().trim()); // conteudo
				email.setAuthentication("paduamendes.web", "apm441000");
				email.setSmtpPort(465);
				email.setSSL(true);
				email.setTLS(true);
				email.send();
			} catch (EmailException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", e.getMessage()));
				return;
			}
		} // destinatário
		setEmail("");
		setNome("");
		setTexto("");
		setAssunto("");
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atençao!", "Mensagem Enviada com Sucesso!"));
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getConvidado() {
		return convidado;
	}


	public void setConvidado(String convidado) {
		this.convidado = convidado;
	}


	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}


	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

}
