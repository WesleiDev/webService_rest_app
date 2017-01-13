package com.weslei.usuario;

import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weslei.util.CriptoUtil;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	UsuarioRepository repo;
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public List<Usuario> getUsuario(){
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Usuario getUsuario(@PathVariable Integer id){
		return repo.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public boolean isLoggedIn(@QueryParam("usuario") String usuario, @QueryParam("senha") String senha){
		
		try{
			Usuario user =(Usuario) em.createQuery(" select u from Usuario u where u.login =:login and u.senha =:senha").
			setParameter("login", usuario).setParameter("senha", CriptoUtil.criptoStringHexMD5(senha)).getSingleResult();
			
			System.out.println(user.getSenha());
			
			if(user.getLogin().equals(usuario.toString()) && 
					(user.getSenha().equals(CriptoUtil.criptoStringHexMD5(senha).toString()))){
				return true;
			}else
			{
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Usuário não encontrado: " + e.getMessage());
			return false;
		}		
	}
	
	//Testes abaixo
	
	@RequestMapping(method = RequestMethod.GET, value="/param/{texto}")
	public String getAlo(@PathVariable String texto){
		return "Texto informado: " + texto;
	}
		
	@RequestMapping(method = RequestMethod.GET, value="/alomundo")
	public String aloMundo(){
		return "Alo mundo";
	}

	
	@RequestMapping (method = RequestMethod.POST, value="/json/", produces="application/json", consumes="application/json")
	public List<Usuario> testeJson(@RequestBody List<Usuario> user){
		for(Usuario u :user ){
			System.out.println("Nome usuario: " + u.getLogin()+" Senha: "+ u.getSenha());
		}
		return user;
	}
	

	

}
