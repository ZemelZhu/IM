package com.im.zemel.controller;

import com.im.zemel.services.RelationService;
import com.im.zemel.util.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/Relation")
public class RelationController {
    @Autowired
    private RelationService relationService;

//    @GetMapping("/AllRelation")
//    public List<Message> AllRelation(HttpSession session) {
//        Integer uid = SessonUntil.getSessionUser(session);
//        if (uid != null && "".equals(uid) == false) {
//            return relationService.getAllRelation(uid);
//        }
//        return null;
//    }

    @PostMapping("/addNewFriend")
    public String addNewFriend(@RequestBody Map map, HttpSession session) {
        Integer sessionUser = SessionUntil.getSessionUser(session);
        int id = (int) map.get("uid");
        if (sessionUser != null && id != sessionUser) {
            return relationService.addNewFriend(sessionUser, id, true);
        }

        return "false";
    }
}
