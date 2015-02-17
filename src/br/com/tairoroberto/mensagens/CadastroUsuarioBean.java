package br.com.tairoroberto.mensagens;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class CadastroUsuarioBean {
	private String nome;
	private String email;
	private String senha;

	
	public void cadastrar() {
		if (this.getNome() == null || this.getNome().length() < 10) {
			this.adicionarMessagem("formCadastro:nome", FacesMessage.SEVERITY_ERROR, 
					"Nome incompleto", "Por favor informar seu nome completo.");
		}
		
		if (hojeDiaDescanso()) {
			this.adicionarMessagem(null, FacesMessage.SEVERITY_WARN, 
					"Dia de descanso", "Você não pode cadastrar usuários hoje");
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (!context.getMessages().hasNext()) {
			//Aqui poderia gravar o usuario no banco de dados
			//...
			//...
			this.adicionarMessagem(null, FacesMessage.SEVERITY_INFO, 
					"Usuário cadastrado", "Usuário cadastrado com sucesso...!");
		}
		
	}
	
	
	//Method to add message
	private void adicionarMessagem(String clienteId, Severity severity, String sumary, String detail){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, sumary, detail);
		
		//add message to queue
		context.addMessage(clienteId, message);
	}
	
	//method to get day off
	private boolean hojeDiaDescanso() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
	}
	
	//Getters and Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
