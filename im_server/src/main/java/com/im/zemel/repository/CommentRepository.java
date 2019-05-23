package com.im.zemel.repository;

import com.im.zemel.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/13
 * \* Description:
 * \
 */
public interface CommentRepository extends JpaRepository<Comment,Integer>{


     List<Comment> findByParentId(Integer parentId);

     List<Comment> findByTargetUserId(Integer commentId);

     List<Comment> findByParentArticle(Integer parentArticle);

     List<Comment> findByOwnerUserId(Integer userId);
}

