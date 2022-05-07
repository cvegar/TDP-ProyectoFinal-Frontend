package com.colegio.controller;

import com.colegio.entity.*;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class MatriculaController {

    String URL = "http://localhost:8091/api/rest/matricula";
    String URLSeccion = "http://localhost:8091/api/rest/seccion";
    String URLMatricula = "http://localhost:8091/api/rest/matricula";
    @RequestMapping("/consultaCrudMatricula")
    public String listarSeccion(Model model) {
        try
        {
            RestTemplate rt= new RestTemplate();
            ResponseEntity<Seccion[]> response= rt.getForEntity(URLSeccion+"/habiles", Seccion[].class);
            Seccion[] lista=response.getBody();
            System.out.println(lista);
            model.addAttribute("matricula",lista);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "crudMatricula";
    }

    @RequestMapping(value = "/registraActualizaCrudMatricula")
    public String saveUsuario(@RequestParam("idAlumno") String idUsuario,
                              @RequestParam("idSeccion") String idSeccion,
                              RedirectAttributes model) {
        try {

            AlumnoHasSeccion bean = new AlumnoHasSeccion();
            bean.setIdAlumno(Integer.valueOf(idUsuario));
            bean.setIdSeccion(Integer.valueOf(idSeccion));
            Gson gson=new Gson();
            String json=gson.toJson(bean);
            RestTemplate rt=new RestTemplate();

                HttpHeaders header=new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request=new HttpEntity<String>(json,header);
                rt.postForObject(URL, request, String.class);
                model.addFlashAttribute("MENSAJE","Registro guardado correctamente...");



        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/consultaCrudMatricula";
    }
    @RequestMapping("/consultaMatriculados")
    public String listarCursosMatriculados() {

        return "crudCursosMatriculados";
    }

    @RequestMapping("/consultaCrudCursosMatriculados")
    public String listarCursosMatriculados(Model model,@RequestParam("idAlumno") String idUsuario) {
        try
        {
            RestTemplate rt= new RestTemplate();
            ResponseEntity<Seccion[]> response= rt.getForEntity(URLMatricula+"/listaCursosMatriculados?idAlumno="+idUsuario, Seccion[].class);
            Seccion[] lista=response.getBody();
            model.addAttribute("matriculados",lista);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "crudCursosMatriculados";
    }
}
