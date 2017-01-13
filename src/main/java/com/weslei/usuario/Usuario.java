package com.weslei.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@Column(name="id_usuario")
	private Integer id_usuario;
	
	@Column(name="login")
	private String login;

	@Column(name="senha")
	private String senha;
	
	public Integer getUsuarioid() {
		return id_usuario;
	}

	public void setUsuarioid(int usuarioid) {
		this.id_usuario = usuarioid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
