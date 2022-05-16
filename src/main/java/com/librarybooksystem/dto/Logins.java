package com.librarybooksystem.dto;

import lombok.Data;

@Data
public class Logins {
 private int number;
 private String type;
 public Logins(int number,String type) {
		super();
		this.number=number;
		
		this.type = type;
	}
}
