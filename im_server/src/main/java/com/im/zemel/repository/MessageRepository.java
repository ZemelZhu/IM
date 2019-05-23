package com.im.zemel.repository;

import com.im.zemel.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    //    @Query(nativeQuery = true, value = "select * from message where (from_id=?1 or to_id=?1) and status !=1")
    @Query(nativeQuery = true, value = "select * from message where (to_id=?1 and status !=1) or(from_id=?1 and status!=1)")
    List<Message> getMessage(Integer uid);

    @Query(nativeQuery = true, value = "select * from message where (to_id=?1 and from_id=?2 and status !=1) or(from_id=?1 and to_id=?2 and status!=1)")
    List<Message> getMessage(Integer uid, Integer fid);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from message where (to_id=?1 and from_id=?2 ) or(from_id=?1 and to_id=?2 )")
    void deleteMessage(Integer uid, Integer fid);
}
