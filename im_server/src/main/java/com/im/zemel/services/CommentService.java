package com.im.zemel.services;

import com.im.zemel.pojo.Article;
import com.im.zemel.pojo.Comment;
import com.im.zemel.pojo.CommentUser;
import com.im.zemel.repository.ArticleRepository;
import com.im.zemel.repository.CommentRepository;
import com.im.zemel.repository.CommentUserRepository;
import com.im.zemel.util.SessionUntil;
import com.im.zemel.util.TimeUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/13
 * \* Description:
 * \
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentUserRepository commentUserRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public String newComment(Comment comment, HttpSession session) {
        if (comment.getContent() == "") {
            return "评论内容不能为空";
        }
        Article article = articleRepository.findOne(comment.getParentArticle());
        article.setCommentNumber(article.getCommentNumber() + 1);
        articleRepository.save(article);
        Integer userId = SessionUntil.getSessionUser(session);
        comment.setOwnerUserId(userId);
        comment.setCreatedAt(TimeUntil.getNowTime());
        commentRepository.save(comment);
        return "评论成功";
    }

    public List<Comment> getAllComment(Integer parentArticle) {

        return commentRepository.findByParentArticle(parentArticle);
    }

    @Transactional
    public String commentAddPraise(Integer commentId, HttpSession session) {
        CommentUser commentUser;
        Integer userId = SessionUntil.getSessionUser(session);
        commentUser = commentUserRepository.findByCommentIdAndUserId(commentId, userId);
        Comment comment = commentRepository.findOne(commentId);
        String flat = "0";
        if (commentUser != null) {
            commentUserRepository.delete(commentUser);
            comment.setLikeCount(comment.getLikeCount() - 1);

        } else {
            commentUser = new CommentUser();
            commentUser.setUserId(userId);
            commentUser.setCommentId(commentId);
            commentUserRepository.save(commentUser);
            comment.setLikeCount(comment.getLikeCount() + 1);
            flat = "1";
        }

        commentRepository.save(comment);
        return flat;
    }

    @Transactional
    public String newChildComment(Comment comment, HttpSession session) {
        if (comment.getContent() == "")
            return "评论内容不能为空";
        Comment commentParent = commentRepository.findOne(comment.getParentId());
        Article article = articleRepository.findOne(commentParent.getParentArticle());
        article.setCommentNumber(article.getCommentNumber() + 1);
        articleRepository.save(article);
        Integer userId = SessionUntil.getSessionUser(session);
        comment.setOwnerUserId(userId);
        comment.setCreatedAt(TimeUntil.getNowTime());
        commentRepository.save(comment);

        return "评论成功";
    }

    public List<Comment> getCommentListById(Integer commentId) {
        //二级评论
        return commentRepository.findByParentId(commentId);
    }

    public List<Comment> getCommentByUserId(Integer userId) {
        //个人信息中的评论详情
        return commentRepository.findByOwnerUserId(userId);

    }

    @Transactional
    public Article getNoteByComment(Integer commentId) {
        //look for articleMessage by commentid
        Comment comment = commentRepository.findOne(commentId);
        Integer noteId = comment.getParentArticle();
        if (noteId == null) {
            //look for articleMessage by parentComment
            noteId = commentRepository.findOne(comment.getParentId()).getParentArticle();
        }
        Article one = articleRepository.getOne(noteId);
        System.out.println(one);
        return one;

    }

    public Comment getParentCommentByI(Integer commentId) {
        Comment comment = commentRepository.findOne(commentId);
        Integer pId = comment.getParentId();
        if (pId != null) {
            //look for Parent Comment
            comment = commentRepository.findOne(pId);
        }
        return comment;
    }
}

