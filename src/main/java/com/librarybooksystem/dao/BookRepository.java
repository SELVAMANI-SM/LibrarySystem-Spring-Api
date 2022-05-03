package com.librarybooksystem.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.librarybooksystem.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


@SuppressWarnings("unchecked")
Book save(Book book);

@Transactional
@Modifying
@Query("delete from Book u where u.id=:id")
void deleteByBookId(@Param("id") Integer id);
@Transactional
@Modifying
//@Query("delete from Book u where u.author =:author ")
//@Query("delete from Book u where u.author=:author")
void deleteByAuthorContaining(String author);
@Transactional
@Modifying
@Query("delete from Book u where u.name=:name")
void deleteByName(@Param("name")String name);
@Transactional
@Modifying
@Query("delete from Book u where u.department=:department")
void deleteByDepartment(@Param("department") String department);

//@Query("delete from Book u where u.department=:department")


List<Book>findAll();
Optional<Book>findById(Integer id);
List<Book>findByDepartment(String department);
List<Book>findByNameContaining(String name);

//@Transactional
//@Modifying
//@Query("update Book u set u.name = :name,u.department=:department,u.author=:author,u.status=:status,u.eBookLink=:eBookLink,u.videoLink=:videoLink,u.imageLink=:imageLink where u.id=:id")
//void updateByBookId(@Param("id") Integer id,@Param("name") String name,
//		@Param("department") String department,@Param("author") String author,@Param("status") String status,@Param("eBookLink") String eBookLink,@Param("videoLink") String videoLink,@Param("imageLink") String imageLink);
//
//Book updateById(Book book);
//@Transactional
//@Modifying
//@Query("select u form com.librarybooksystem.model u where u.id=:id")
//List<Book> findByIds(@Param("id") Integer id);
@Transactional
@Modifying
@Query("delete from Book u where u.author=:author")
void deleteByAuthor(String author);




//Book save(Book book);

//	
//	//select * from users;
//	List<Book> findAll();
//	
//	//select * from users where email = ? and password = ?
//	User findByEmailAndPassword(String email, String password);
//	
//	Optional<User> findById(Integer id);
//	
//	
//	@Modifying
//	@Query("delete from User u where u.id=:id")
//	void deleteById(@Param("id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query("update User u set u.password = :password where u.id=:id")
//	void changePassword(@Param("id") Integer id, @Param("password") String password);
//	

}
