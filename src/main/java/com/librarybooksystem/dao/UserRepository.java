package com.librarybooksystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.model.User;
@RestController
public interface UserRepository extends JpaRepository<User,Integer>{
	@SuppressWarnings("unchecked")
	User save(User user);
List<User>findByuserType(String user1);
Optional<User>findById(Integer id);

User findByEmailAndPassword(String email, String password);

}
