package com.colegio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.colegio.entity.Opcion;
import com.colegio.entity.Rol;
import com.colegio.entity.Usuario;

@Controller
public class LoginController {
	String URL="http://localhost:8091/api/rest";
	
	@RequestMapping("/verLogin")
	public String verLogin() { return "login"; }
	
	//No estra a tu codigo a nada, nada de lo que esta en azul
	//amor si entra se queda aqui nomas

	@RequestMapping("/login")
	public String login(@RequestParam("login") String usuario,@RequestParam("password") String password, HttpSession  session, HttpServletRequest request) {
		
		RestTemplate rt=new RestTemplate();
		ResponseEntity<Usuario> response=rt.getForEntity(URL+"/login/"+usuario+"/"+password,Usuario.class); //aqui entra
		System.out.println(usuario);
		System.out.println(URL+"/login/"+usuario+"/"+password);
		Usuario bean=response.getBody();
		System.out.println(bean);
		System.out.println(response);
	
		if(bean==null){
			session.setAttribute("mensaje","Usuario y/o clave incorrectos...");
			return "login";
		}
		else {
			//obtener el rol del usuario actual
			int id;
			id=bean.getIdUsuario();
			//privilegios

			//acceder al servicio donde la ruta es "listAllMarca" le pertenece a "@GetMapping"
			ResponseEntity<Rol[]> respuesta=rt.getForEntity(URL+"/privilegios/"+id, Rol[].class);
			ResponseEntity<Opcion[]> respueop=rt.getForEntity(URL+"/opcion/"+id, Opcion[].class);
			//leer el objeto response
			Rol[] lista=respuesta.getBody();
			Opcion[] listaOp=respueop.getBody();
			//crear sesiòn
			//atributo de tipo session
			session.setAttribute("objUsuario", bean);
			session.setAttribute("objRoles", lista);
			session.setAttribute("objMenus", listaOp);
			return "redirect:home";
		}

	}
	
	@RequestMapping("/home")
	public String salida() {
		return "intranetHome";
	}
/*
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		//Destruir todos los objetos de la sesion mediante programación
		session.invalidate();

		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("mensaje", "El usuario salió de sesión");
		return "login";

	}*/
	
}
