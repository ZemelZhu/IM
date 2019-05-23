package com.im.zemel.repository;

import com.im.zemel.pojo.CommentUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/14
 * \* Description:
 * \
 */
public interface CommentUserRepository extends JpaRepository<CommentUser,Integer>{
    CommentUser findByCommentIdAndUserId(Integer commentId, Integer userId);
}

