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
@Table(name="book_taken_deatils")
@Data
public class TakenBookDetails {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Integer id;
@Column(name="user_id")
private Integer userId;
@Column(name="user_type")
private String userType;
@Column(name="book_id")
private Integer bookId;
@Column(name="book_taken_date")
private LocalDate takenDate;
@Column(name="book_return_date")
private LocalDate returnDate;
@Column(name="book_status")
private String status;
}
