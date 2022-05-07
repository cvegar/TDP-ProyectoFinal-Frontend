package com.colegio.entity;

import com.colegio.entity.Seccion;
import com.colegio.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class AlumnoHasSeccion {

	private Integer idSeccion;
	
	private Integer idAlumno;
	
	
}
