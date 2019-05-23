package com.im.zemel.controller;

import com.im.zemel.pojo.Note;
import com.im.zemel.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Vimmone
 * \* Date: 2018/2/22
 * \* Description:文章功能控制器
 * \
 */
@RestController
@RequestMapping("/Note")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @RequestMapping("/getNoteByPage")
    public List<Note> getNoteByPage(@RequestBody Map map) {
        int page=Integer.parseInt(map.get("page").toString());
        List<Note> allNote = noteService.getNoteByPage(page);
        return allNote;
    }
    @RequestMapping("/getNoteByPageInPhone")
    public List<Note> getNoteByPageInPhone() {
        List<Note> allNote = noteService.getNoteByPageInPhone();
        return allNote;
    }

    @PostMapping("getNoteByUserId")
    public List<Note> getNoteByUserId(@RequestBody Map map){
        int userId=Integer.parseInt(map.get("userId").toString());
        return noteService.getNoteByUserId(userId);
    }
    @PostMapping("getNoteByUserIdAndPage")
    public List<Note> getNoteByUserIdAndPage(@RequestBody Map map){
        int userId=Integer.parseInt(map.get("userId").toString());
        int page=Integer.parseInt(map.get("page").toString());
        return noteService.getNoteByUserIdAndPage(userId,page);
    }

}

