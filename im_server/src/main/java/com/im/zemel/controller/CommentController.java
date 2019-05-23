package com.im.zemel.controller;

import com.im.zemel.pojo.Article;
import com.im.zemel.pojo.Comment;
import com.im.zemel.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/4/13
 * \* Description:
 * \
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/intercepter/newComment")
    public String newComment(@RequestBody Comment comment, HttpSession session) {
        return commentService.newComment(comment,session);
    }
    @RequestMapping("/intercepter/newChildComment")
    public String newChildComment(@RequestBody Comment comment, HttpSession session) {
        return commentService.newChildComment(comment,session);
    }

    @RequestMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestBody Map map) {
        Integer parentArticle = Integer.parseInt(map.get("articleId").toString());
        return  commentService.getAllComment(parentArticle);
    }

    @RequestMapping("/getCommentListById")
    public List<Comment> getCommentListById(@RequestBody Map map) {
        Integer commentId = Integer.parseInt(map.get("commentId").toString());
        return commentService.getCommentListById(commentId);

    }
    @PostMapping("/intercepter/commentAddPraise")
    public String commentAddPraise(@RequestBody Map map,HttpSession session) {
        Integer commentId = Integer.parseInt(map.get("commentId").toString());
        return commentService.commentAddPraise(commentId,session);
    }

    @PostMapping("getCommentByUserId")
    public List<Comment> getCommentByUserId(@RequestBody Map map) {
        Integer userId = Integer.parseInt(map.get("userId").toString());
        return commentService.getCommentByUserId(userId);
    }
    @PostMapping("getArticleByComment")
    public Article getNoteByComment(@RequestBody Map map) {
        Integer commentId = Integer.parseInt(map.get("commentId").toString());
        return  commentService.getNoteByComment(commentId);
    }
    @PostMapping("getParentCommentById")
    public Comment getParentCommentById(@RequestBody Map map) {
        Integer commentId = Integer.parseInt(map.get("commentId").toString());
        return  commentService.getParentCommentByI(commentId);
    }
}

