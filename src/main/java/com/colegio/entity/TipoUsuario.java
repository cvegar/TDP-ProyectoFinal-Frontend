package com.colegio.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoUsuario {

	
	private Integer idTipoUsuario;
	private String nombre;		
	private String estado;		

	public TipoUsuario(int tipoUsuario) {
		idTipoUsuario=tipoUsuario;
	}
	public TipoUsuario() {
		
	}
	
}
