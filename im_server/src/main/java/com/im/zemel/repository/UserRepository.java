package com.im.zemel.repository;

import com.im.zemel.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM user where user.uid in " +
            "((SELECT relation.fid from relation where relation.uid=?1))" +
            "or  user.uid in" +
            "(SELECT relation.uid from relation where relation.fid=?1);")
    public List<User> findUserByUid(Integer uid);

    List<User> findByEmail(String email);
    @Query(nativeQuery = true, value = "SELECT * FROM user where user.uid in " +
            "((SELECT relation.fid from relation where relation.uid=?1))" +
            "or  user.uid in" +
            "(SELECT relation.uid from relation where relation.fid=?1) limit ?2,9;")
    List<User> findUserByUidAndPage(Integer uid, int page);
}
