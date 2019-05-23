package com.im.zemel.util;

import com.im.zemel.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUntil {
    public static Map<Integer, HttpSession> sessionMap = new ConcurrentHashMap<Integer, HttpSession>();
    private static final String userID = "userId";
    private static final Logger logger = LoggerFactory.getLogger(SessionUntil.class);

    public static Integer getSessionUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userID);
        return userId;
    }

    public static void saveSessionUser(HttpSession session, User user) {
        if (session != null) {
            session.removeAttribute(userID);
            session.setAttribute(userID, user.getUid());
            if (sessionMap.containsKey(user.getUid()) && session != sessionMap.get(user.getUid())) {
                offline(sessionMap.get(user.getUid()));
            }
            sessionMap.put(user.getUid(), session);
        }
    }

    public static void offline(HttpSession session) {
        if (session != null) {
            session.removeAttribute(userID);
            session.invalidate();
            logger.error("sessionID过期:" + session.getId());
        }
    }

    public static void offline(int id) {
        if (sessionMap.containsKey(id)) {
            offline(sessionMap.get(id));
            sessionMap.remove(id);
        }
    }

}
