package com.cleverpy.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaBean {
	
	private int id;
    private String nombre;
    private String ano;
    
}
