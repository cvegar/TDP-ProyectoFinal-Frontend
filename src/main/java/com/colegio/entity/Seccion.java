package com.colegio.entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seccion {



	private Integer idSeccion;
	private String codigo;
	private String estado;
	private Aula aula;

	public Seccion(int seccion) {
		idSeccion=seccion;
	}
	public Seccion() {}
}
