package com.librarybooksystem.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.model.User;
@RestController
public interface UserRepository extends JpaRepository<User,Integer>{
	@SuppressWarnings("unchecked")
	User save(User user);
List<User>findByuserType(String user1);
Optional<User>findById(Integer id);

User findByEmailAndPassword(String email, String password);
@Transactional
@Modifying
@Query("update User u set u.fine = :fine where u.id=:id")
void updateFine(@Param("id") Integer id,@Param("fine") int fine);

}
