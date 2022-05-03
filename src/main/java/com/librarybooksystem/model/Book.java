package com.librarybooksystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="library_books")
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private Integer id;
	@Column(name="NAME")
	private String name;
	@Column(name="department")
	private String department;
	@Column(name="author")
	private String author;
	@Column(name="STATUS")
	private String status;
	@Column(name="e_book_link")
	private String eBookLink;
	@Column(name="video_link")
	private String videoLink ;
	@Column(name="image_link")
	private String imageLink;
	
	

}
