package com.librarybooksystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.model.QueryReply;

@RestController
public interface QueryReplyRepository extends JpaRepository<QueryReply, Integer> {
@SuppressWarnings("unchecked")
QueryReply save(QueryReply query);


List<QueryReply> findAll();




//@Transactional
//@Modifying
//@Query("update QueryReply u set u.reply = :reply where u.userId=:userId AND u.userQuery =: userQuery")
//void queryReply(@Param("userId") Integer userId,@Param("reply") String reply,@Param("userQuery") String userQuery);

List<QueryReply> findByUserId(Integer userId);
}
