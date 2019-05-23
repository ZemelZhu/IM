package com.im.zemel.repository;


import com.im.zemel.pojo.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/2/22
 * \* Description:
 * \
 */
public interface NoteRepository extends JpaRepository<Note,Integer> {

    @Query(nativeQuery = true, value = "select * from note where author_id = ?1 limit ?2,9;")
    List<Note> getNoteByUidAndPage(Integer uid,int pageIndex);

    List<Note> findByAuthorId(Integer authorId);
}


