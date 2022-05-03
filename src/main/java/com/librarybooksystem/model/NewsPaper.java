package com.librarybooksystem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="news_papers")
@Data
public class NewsPaper {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="news_date")
	private LocalDate date;
	@Column(name="indian_express")
	private String indianExpress;
	private String hindu;
	private String dinamalar;
}
	

