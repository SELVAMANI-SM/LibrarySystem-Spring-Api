package com.librarybooksystem.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.librarybooksystem.model.TakenBookDetails;



@Repository
public interface TakenBookDetailsRepository extends JpaRepository<TakenBookDetails, Integer> {
@SuppressWarnings("unchecked")
//	@Transactional
//	@Modifying
//	@Query("update TakenBookDetails u set u.status = :status where u.id=:id")
//	void updateDate(@Param("id") Integer id,@Param("user_type") String user_type,@Param("book_id") String book_id,@Param("book_taken_date") String book_taken_date,@Param("book_status") String book_status) ;
//	
	TakenBookDetails save(TakenBookDetails taken);
//@Transactional
//@Modifying
//@Query("update TakenBookDetails u set u.returnDate=:book_return_date, u.status = :book_status where u.userId =: userId")
//void updateDetailsBook(@Param("userId") Integer userId,@Param("book_return_date") LocalDate date ,@Param("book_status")String book_status);

List<TakenBookDetails> findByUserId(Integer id);

List<TakenBookDetails> findByUserType(String id);

	
}

