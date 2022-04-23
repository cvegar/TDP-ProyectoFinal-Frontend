package com.colegio.entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aula {

	
	
	private Integer idAula;
	private int cantMin;
	private int cantMax;
	private String aulaName;

	public Aula(int aula) {
		idAula=aula;
	}
	public Aula() {
	}

	
}
