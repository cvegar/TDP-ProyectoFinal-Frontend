package com.colegio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.colegio.Bean.BeanUsuario;
import com.colegio.entity.UsuarioHasRol;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.colegio.entity.TipoUsuario;
import com.colegio.entity.Usuario;
import com.google.gson.Gson;

@Controller
public class UsuarioController {
	
	String URL="http://localhost:8091/api/rest/usuario";


	@RequestMapping("/consultarDni")
	@ResponseBody
	public BeanUsuario consultarUsuarioDni(@RequestParam("dni") String dni) {
		BeanUsuario bean=null;
		try
		{
			RestTemplate rt= new RestTemplate();
			ResponseEntity<BeanUsuario> response= rt.getForEntity("https://apiperu.dev/api/dni/" +
					dni+"?api_token=f96f51fb37cb68d787caab0144ada38df7230bf194282aac732d86f73d3dfe77", BeanUsuario.class);
			bean=response.getBody();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return bean;
	}


	@RequestMapping("/consultaCrudUsuario")
	public String listarusuario(Model model ) {
	try 
	{
		RestTemplate rt= new RestTemplate();
		ResponseEntity<Usuario[]> response= rt.getForEntity(URL, Usuario[].class);
		Usuario[] lista=response.getBody();
		System.out.println(lista);
		model.addAttribute("data",lista);
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	 return "crudUsuario";
	}
	
	@RequestMapping("/listaDepartamento")
	@ResponseBody
	public String[] listaDepartamento() {
		String[] lista=null;
	try 
	{
		RestTemplate rt= new RestTemplate();
		ResponseEntity<String[]> response= rt.getForEntity(URL + "/listaDepartamentos", String[].class);
		lista=response.getBody();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	 return lista;
	}
	
	@RequestMapping("/listaProvincia")
	@ResponseBody
	public String[] listaProvincia(@RequestParam("departamento") String departamento) {
		String[] lista=null;
	try 
	{
		RestTemplate rt= new RestTemplate();
		ResponseEntity<String[]> response= rt.getForEntity(URL + "/listaProvincias/"+departamento, String[].class);
		lista=response.getBody();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	 return lista;
	}
	@RequestMapping("/listaDocentes")
	@ResponseBody
	public Usuario[] listaDocentes() {
		Usuario[] lista=null;
		try
		{
			RestTemplate rt= new RestTemplate();
			ResponseEntity<Usuario[]> response= rt.getForEntity(URL + "/listaDocentes", Usuario[].class);
			lista=response.getBody();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

	@RequestMapping("/listaTipoUsuario")
	@ResponseBody
	public TipoUsuario[] listaTipoUsuario() {
		TipoUsuario[] lista=null;
	try 
	{
		RestTemplate rt= new RestTemplate();
		ResponseEntity<TipoUsuario[]> response= rt.getForEntity(URL + "/listaTipoUsuario", TipoUsuario[].class);
		lista=response.getBody();
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	 return lista;
	}
	/*@RequestMapping(value = "/registraActualizaCrudUsuario")
	public String saveAula(@RequestBody UsuarioHasRol usuarioHasRol, RedirectAttributes model) {

		System.out.println("Cuerpo Json=" + new Gson().toJson(usuarioHasRol));
		try {
			Usuario bean=new Usuario();
			bean.setIdUsuario(usuarioHasRol.getUsuario().getIdUsuario());
			bean.setNombres(usuarioHasRol.getUsuario().getNombres());
			bean.setApellidos(usuarioHasRol.getUsuario().getApellidos());
			bean.setFechaNacimiento(usuarioHasRol.getUsuario().getFechaNacimiento());
			bean.setEmail(usuarioHasRol.getUsuario().getEmail());
			bean.setEstado(usuarioHasRol.getUsuario().getEstado());
			bean.setCelular(usuarioHasRol.getUsuario().getCelular());
			bean.setDireccion(usuarioHasRol.getUsuario().getDireccion());
			bean.setDni(usuarioHasRol.getUsuario().getDni());
			bean.setLogin(usuarioHasRol.getUsuario().getLogin());
			bean.setPassword(usuarioHasRol.getUsuario().getPassword());
			bean.setTipoUsuario(new TipoUsuario(usuarioHasRol.getUsuario().getIdUsuario()));
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			System.out.println("Cuerpo Json=" + gson.toJson(bean));
			RestTemplate rt=new RestTemplate();
			if(usuarioHasRol.getUsuario().getIdUsuario()==0) {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.postForObject(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro guardado correctamente...");
			}
			else {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.put(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro actualizado correctamente...");


			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/consultaCrudUsuario";
	}*/
	@RequestMapping(value = "/registraActualizaCrudUsuario")
	public String saveUsuario(@RequestParam("idUsuario") int idUsuario,
						   @RequestParam("nombres") String nombres,
						   @RequestParam("apellidos") String apellidos,
						   @RequestParam("fechaNacimiento") String fechaNacimiento,
						   @RequestParam("email") String email,
						   @RequestParam("estado") String estado,
						   @RequestParam("celular") String celular,
						   @RequestParam("direccion") String direccion,
						   @RequestParam("dni") String dni,
						   @RequestParam("login") String login,
						   @RequestParam("password") String password,
						   @RequestParam("tipoUsuario") int tipoUsuario,
						   RedirectAttributes model) {
		try {
			UsuarioHasRol usuarioHasRol = new UsuarioHasRol();
			Usuario bean = new Usuario();
			bean.setIdUsuario(idUsuario);
			bean.setNombres(nombres);
			bean.setApellidos(apellidos);
			bean.setFechaNacimiento(fechaNacimiento);
			bean.setEmail(email);
			bean.setEstado(estado);
			bean.setCelular(celular);
			bean.setDireccion(direccion);
			bean.setDni(dni);
			bean.setLogin(login);
			bean.setPassword(password);
			bean.setTipoUsuario(new TipoUsuario(tipoUsuario));

			usuarioHasRol.setUsuario(bean);
			Gson gson=new Gson();
			String json=gson.toJson(usuarioHasRol);
			RestTemplate rt=new RestTemplate();
			if(idUsuario==0) {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.postForObject(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro guardado correctamente...");
			}
			else {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.postForObject(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro actualizado correctamente...");


			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/consultaCrudUsuario";
	}
	/*
	*
	* @RequestMapping(value = "/registraActualizaCrudUsuario")
	public String saveAula(@RequestParam("idUsuario") int idUsuario,
								  @RequestParam("nombres") String nombres,
								  @RequestParam("apellidos") String apellidos,
								  @RequestParam("fechaNacimiento") String fechaNacimiento,
								  @RequestParam("email") String email,
								  @RequestParam("estado") String estado,
								  @RequestParam("celular") String celular,
								  @RequestParam("direccion") String direccion,
								  @RequestParam("dni") String dni,
								  @RequestParam("login") String login,
								  @RequestParam("password") String password,
								  @RequestParam("tipoUsuario") int tipoUsuario,
								  RedirectAttributes model) {
		try {
			Usuario bean=new Usuario();
			bean.setIdUsuario(idUsuario);
			bean.setNombres(nombres);
			bean.setApellidos(apellidos);
			bean.setFechaNacimiento(fechaNacimiento);
			bean.setEmail(email);
			bean.setEstado(estado);
			bean.setCelular(celular);
			bean.setDireccion(direccion);
			bean.setDni(dni);
			bean.setLogin(login);
			bean.setPassword(password);
			bean.setTipoUsuario(new TipoUsuario(tipoUsuario));
			Gson gson=new Gson();
			String json=gson.toJson(bean);
			RestTemplate rt=new RestTemplate();
			if(idUsuario==0) {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.postForObject(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro guardado correctamente...");
			}
			else {
				HttpHeaders header=new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request=new HttpEntity<String>(json,header);
				rt.put(URL, request, String.class);
				model.addFlashAttribute("MENSAJE","Registro actualizado correctamente...");


			}



		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/consultaCrudUsuario";
	}
	*
	* */

	@RequestMapping(value = "/eliminaCrudUsuario")
	public String delete(@RequestParam("id") int cod, RedirectAttributes model){
		try {
			RestTemplate rt=new RestTemplate();
			rt.delete(URL+"/"+cod);
			model.addFlashAttribute("MENSAJE","Registro eliminado correctamente...");
		} catch (Exception e) {
			e.printStackTrace();
			model.addFlashAttribute("MENSAJE","Error en la eliminaciòn...");
		}
		return "redirect:/consultaCrudUsuario";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		

		session.invalidate();

		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-cache");

		request.setAttribute("mensaje", "El usuario salió de sesión");
		return "login";

	}

}
