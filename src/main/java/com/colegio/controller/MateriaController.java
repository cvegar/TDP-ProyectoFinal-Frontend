package com.colegio.controller;

import com.colegio.entity.Aula;
import com.colegio.entity.Materia;
import com.colegio.entity.Materia;
import com.colegio.entity.Seccion;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MateriaController {

    String URL="http://localhost:8091/api/rest/materia";

    @RequestMapping("/consultaCrudMateria")
   public String listarMatricula(Model model){

        RestTemplate rt= new RestTemplate();
        ResponseEntity<Materia[]> response= rt.getForEntity(URL+"/listaMateria", Materia[].class);
        Materia[] lista=response.getBody();
        System.out.println(lista);
        model.addAttribute("materia",lista);

        return "crudMateria";
    }

    @RequestMapping(value = "/registraActualizaCrudMateria")
    public String saveMateria(@RequestParam("idMateria") int idMateria,
                          @RequestParam("codigo") String codigo,
                          @RequestParam("nombre") String nombre,
                          @RequestParam("estado") String estado,
                          RedirectAttributes model) {
        try {
            //crear objeto de la clase Computadora
            Materia bean=new Materia();
            bean.setIdMateria(idMateria);
            bean.setCodigo(codigo);
            bean.setNombre(nombre);
            bean.setEstado(estado);
            Gson gson=new Gson();
            String json=gson.toJson(bean);
            //clase para acceder a un servicio
            RestTemplate rt=new RestTemplate();
            //validar
            if(idMateria==0) {//registrar====>post
                //crear un objeto de la clase HttpHeaders para indicar que se envia un JSON
                HttpHeaders header=new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON);
                //crear un objeto de la clase HttpEntity
                HttpEntity<String> request=new HttpEntity<String>(json,header);
                //acceder al servicio donde la ruta es "saveComputadora" le pertenece a "@PostMapping"
                rt.postForObject(URL, request, String.class);
                model.addFlashAttribute("MENSAJE","Registro guardado correctamente...");
            }
            else {//actualizar====>put
                //crear un objeto de la clase HttpHeaders para indicar que se envia un JSON
                HttpHeaders header=new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON);
                //crear un objeto de la clase HttpEntity
                HttpEntity<String> request=new HttpEntity<String>(json,header);
                //acceder al servicio donde la ruta es "updateComputadora" le pertenece a "@PutMapping"
                rt.put(URL, request, String.class);
                model.addFlashAttribute("MENSAJE","Registro actualizado correctamente...");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/consultaCrudMateria";
    }

    @RequestMapping("/listaMateria")
    @ResponseBody
    public Materia[] listaMateria() {
        Materia[] lista=null;
        try
        {
            RestTemplate rt= new RestTemplate();
            ResponseEntity<Materia[]> response= rt.getForEntity(URL + "/listaMateria", Materia[].class);
            lista=response.getBody();
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }

    @RequestMapping(value = "/eliminaCrudMateria/{id}")
    public String delete(@PathVariable("id") int cod, RedirectAttributes model){
        try {
            RestTemplate rt=new RestTemplate();
            rt.delete(URL+"/"+cod);
            model.addFlashAttribute("MENSAJE","Registro eliminado correctamente...");
        } catch (Exception e) {
            e.printStackTrace();
            model.addFlashAttribute("MENSAJE","Error en la eliminaciòn...");
        }
        return "redirect:/consultaCrudMateria";
    }
}
