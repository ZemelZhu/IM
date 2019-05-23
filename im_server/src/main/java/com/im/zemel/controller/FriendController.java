package com.im.zemel.controller;

import com.im.zemel.pojo.User;
import com.im.zemel.services.FriendService;
import com.im.zemel.util.SessionUntil;
import com.im.zemel.util.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/AllFried")
    public List<User> getAllUser(HttpSession session) {
        Integer uid = SessionUntil.getSessionUser(session);
        if (uid == null)
            return null;
        return friendService.getAllUser(uid);
    }
    @PostMapping("/AllFriedByPage")
    public List<User> getAllUserByPage(@RequestBody Map map,HttpSession session) {
        Integer uid = SessionUntil.getSessionUser(session);
        if (uid == null)
            return null;
        int page=Integer.parseInt(map.get("page").toString());
        return friendService.getAllUserByPage(uid,page);
    }
    @PostMapping("/deleteFriend")
    public String deleteFriend(@RequestBody Map map,HttpSession session)
    {
        Integer fid= (Integer) map.get("fid");
        Integer uid = SessionUntil.getSessionUser(session);
        if(fid!=null&&uid!=null)
        {
            return friendService.deleteFriend(uid,fid);
        }
        return "false";
    }
}
