package com.im.zemel.repository;

import com.im.zemel.pojo.Relation;
import com.im.zemel.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RelationRepository extends JpaRepository<Relation,Integer> {
    List<Relation> findByUid(Integer uid);
    @Query(nativeQuery = true, value = "select user.* from user join relation on user.uid=relation.uid where relation.uid=?1")
    public List<User> findUserByUid(Integer uid);
    @Query(nativeQuery = true, value = "select * from relation where (uid=?1 and fid=?2) or (uid=?2 and fid=?1)")
    public List<Relation> getRelation(Integer uid,Integer fid);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from relation where (uid=?1 and fid=?2) or (uid=?2 and fid=?1)")
    void deleteRelation(Integer uid, Integer fid);
}
