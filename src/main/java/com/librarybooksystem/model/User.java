package com.librarybooksystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_details")
@Data
public class User {

   private String name;

   private String email;

   private String password;

   private String mobile;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   @Column
   private Integer id;
   @Column(name="user_type")
   private String userType;
   private int fine;
   

}
