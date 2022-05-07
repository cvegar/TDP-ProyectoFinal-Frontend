package com.colegio.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Materia {

	private Integer idMateria;
	private String codigo;
	private String nombre;
	private String estado;

	public Materia(int materia) {
		idMateria=materia;
	}
	public Materia() {}
}
