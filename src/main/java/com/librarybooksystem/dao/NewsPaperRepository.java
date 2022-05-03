package com.librarybooksystem.dao;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.librarybooksystem.model.NewsPaper;

@Repository
public interface NewsPaperRepository extends JpaRepository<NewsPaper, Integer>{
@SuppressWarnings("unchecked")
NewsPaper save(NewsPaper news);


List<NewsPaper> findByDate(LocalDate sqlDate);


@Transactional
@Modifying
@Query("delete from NewsPaper u where u.date between :localdate and :localdate1")
void deleteByDateNews(@Param("localdate") LocalDate localdate, @Param("localdate1")LocalDate localdate1);
//
//@Transactional
//@Modifying
//@Query("select * from NewsPaper u where u.date := localdate1")
//List<NewsPaper> findByDates(@Param("localdate1") LocalDate localdate1);

//@Transactional
//@Modifying
//@Query(value="select u from NewsPaper u where u.date = ?", nativeQuery = true)
//List<NewsPaper> findDates(LocalDate localdate);
//@Modifying
//@Query(value = "update Users u set u.status = ? where u.name = ?", 
//  nativeQuery = true)






//void deleteReferenceFieldBetween(LocalDate localdate, LocalDate localdate1);



//
//@Transactional
//@Modifying
//@Query("delete from NewsPaper u where u.date=:name")
//void deleteByDate(LocalDate localdate);
////void deleteByDate(LocalDate date);




}
