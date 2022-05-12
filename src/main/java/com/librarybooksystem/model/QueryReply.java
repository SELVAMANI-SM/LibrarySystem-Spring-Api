package com.librarybooksystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="query_reply")
@Data
public class QueryReply {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(name="user_id")
	private Integer userId;
	@Column(name="user_query")
	private String userQuery;
	@Column(name="reply")
	private String reply;
	
}
