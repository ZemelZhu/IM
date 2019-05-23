package com.im.zemel.services;

import com.im.zemel.pojo.Article;
import com.im.zemel.pojo.Note;
import com.im.zemel.pojo.UserInfo;
import com.im.zemel.repository.ArticleRepository;
import com.im.zemel.repository.CommentUserRepository;
import com.im.zemel.repository.UserInfoRepository;
import com.im.zemel.util.PatternArticleUntil;
import com.im.zemel.util.SessionUntil;
import com.im.zemel.util.TimeUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/2/23
 * \* Description:
 * \
 */
@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CommentUserRepository commentUserRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Value("${userAvatar}")
    private String baseImg;

    @Transactional
    public Integer newArticle(Article article, HttpSession session) {
        Integer userId = SessionUntil.getSessionUser(session);
        String time = TimeUntil.getNowTime();

        Note note = new Note();

        note.setAuthorId(userId);
        article.setAuthor(userId);
        note.setTime(time);
        article.setTime(time);
        note.setTitle(article.getTitle());
        note.setAbstractContent(PatternArticleUntil.getAbstractContent(article.getContent()));
        String img = PatternArticleUntil.findImg(article.getContent());
        if (img == null)
            note.setImgUrl(baseImg);
        else
            note.setImgUrl(img);
        articleRepository.save(article);
        note.setArticleId(article.getId());
        noteService.newNote(note);
        article.setNoteId(note.getId());
        UserInfo userInfo = userInfoRepository.findOne(userId);
        userInfo.setNoteCount(userInfo.getNoteCount()+1);
        return article.getId();
    }

    public Article getArticleById(Integer id) {
        Article article = articleRepository.findOne(id);
        article.setViewCount(article.getViewCount() + 1);

        articleRepository.save(article);
        return article;
    }

    @Transactional
    public String deleteArticleById(Article article) {
        article = articleRepository.findOne(article.getId());
        if (article != null) {
            UserInfo userInfo = userInfoRepository.findOne(article.getAuthor());
            if(userInfo!=null)
            {
                userInfo.setNoteCount(userInfo.getNoteCount()-1);
            }

            noteService.deleteNoteById(article.getNoteId());
            articleRepository.delete(article);

            return "success";
        }
//        commentUserRepository.deleteBy

        return "false";
    }
}


