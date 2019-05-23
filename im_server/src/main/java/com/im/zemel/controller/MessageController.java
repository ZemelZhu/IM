package com.im.zemel.controller;

import com.im.zemel.bean.MessageList;
import com.im.zemel.services.MessageService;
import com.im.zemel.util.SessionUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/AllRelation")
    public List<MessageList> allRelation(HttpSession session)
    {
        Integer uid = SessionUntil.getSessionUser(session);
        if (uid != null && "".equals(uid) == false) {
            return messageService.getAllMessageById(uid);
        }
        return null;
    }
    @PostMapping("record")
    public List<MessageList> record(@RequestBody Map map,HttpSession session)
    {
        Integer fid= (Integer) map.get("fid");
        Integer uid = SessionUntil.getSessionUser(session);
        if(fid!=null&&uid!=null)
        {
            return messageService.getMessageByFid(uid,fid);
        }
        return null;
    }
}
