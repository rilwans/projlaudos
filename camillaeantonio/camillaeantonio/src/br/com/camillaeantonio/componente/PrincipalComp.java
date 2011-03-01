package br.com.camillaeantonio.componente;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.camillaeantonio.domain.Confirma;
import br.com.camillaeantonio.system.Facade;
import br.com.framework.hibernate.HibernateOperation;

@ManagedBean
@ViewScoped
public class PrincipalComp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5994435271121606696L;

	private String email = "";

	private String texto = "";

	private String nome = "";
	
	private String convidado = "";

	private String effect = "fade";

	private List<String> images = new ArrayList<String>();

	public PrincipalComp() {
		email = "";
		texto = "";
		nome = "";
		effect = "fade";

	}

	public String getEmail() {
		return email;
	}

	public List<String> getImages() {
		String dir = "D:/workspace/camillaeantonio/WebContent/fotos";

		File diretorio = new File(dir);
		File fList[] = diretorio.listFiles();

		for (int i = 0; i < fList.length; i++) {
			images.add(fList[i].getName());
		}

		return images;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
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

				email.setFrom(getEmail(), getNome()); // remetente
				email.setSubject("Mensagens aos Noivos"); // assunto do e-mail
				email.setMsg("Enviado por: " + getNome().trim() + " [" + getEmail().trim() + "]" + "\n\n MENSAGEM \n\n"
						+ getTexto().trim()); // conteudo
				email.setAuthentication("camillaeantonio", "casamento23");
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
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atençao!", "Mensagem Enviada com Sucesso!"));
	}

	
	public void confirmacao(){
		if (getConvidado()==null || getConvidado().equals("")){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "Nome do Convidado vazio!"));
			return;
		}
			
		Confirma conf = new Confirma();
		conf.setDtConfirma(new Date());
		conf.setNmConvidado(getConvidado());
		
		HibernateOperation hibernateOperation = new HibernateOperation();
		Criteria criteria;
		try {
			criteria = hibernateOperation.getCriteria(Confirma.class);
				
			criteria.add(Restrictions.eq("nmConvidado", getConvidado()));
			
			@SuppressWarnings("unchecked")
			List<Confirma> c = (List<Confirma>) hibernateOperation.listByCriteria(criteria);
		
			if (c.size()!=0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "Convidado já Confirmado"));
				return;
				}
		
			Facade.getInstance().insert(conf);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atençao!", "Não foi possivel confirmar o convidado!"));
			e.printStackTrace();
			return;
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atençao!", "Confirmação Realizada!"));
		
		setConvidado("");
	}

	public String getConvidado() {
		return convidado;
	}

	public void setConvidado(String convidado) {
		this.convidado = convidado;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	/**
	 * @param effect
	 *            the effect to set
	 */

}
