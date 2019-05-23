package com.im.zemel.services;

import com.im.zemel.bean.Email;
import com.im.zemel.component.GlobalComponent;
import com.im.zemel.component.UserComponent;
import com.im.zemel.pojo.Message;
import com.im.zemel.pojo.User;
import com.im.zemel.pojo.UserInfo;
import com.im.zemel.repository.MessageRepository;
import com.im.zemel.repository.UserInfoRepository;
import com.im.zemel.repository.UserRepository;
import com.im.zemel.core.Iplayer;
import com.im.zemel.type.MeeageStatusType;
import com.im.zemel.type.RedisMessageType;
import com.im.zemel.util.RandomUntil;
import com.im.zemel.util.RedisServiceUtil;
import com.im.zemel.util.SessionUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Service
public class LoginService {
    static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UntilService untilService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private RelationService relationService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RedisServiceUtil redisServiceUtil;
    @Value("${userAvatar}")
    private String userAvatar;
    @Value("${administrator}")
    private Integer administrator;

    public String checkNumber(int userId) {
        User user = userRepository.findOne(userId);

        if (user == null) {
            return "error";
        }
        return "success";
    }

    public String userLogin(User user, HttpSession session) {
        User one = userRepository.findOne(user.getUid());

        if (one != null && one.getPassword().equals(user.getPassword())) {
            UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
            Iplayer player = component.getUser(user.getUid());
            if (player != null) {
                player.offline();
                logger.error("玩家下线  playerID:" + player.getId());
            }
            if (session != null) {
                redisServiceUtil.setKey(RedisMessageType.LOGIN + user.getUid(), session.getId());
                SessionUntil.saveSessionUser(session, user);
            }
            return "success";
        } else
            return "error";
    }

    public User getLogin(HttpSession session) {
        Integer uid = SessionUntil.getSessionUser(session);
//        String s = redisServiceUtil.getValue(session.getId());
//        if (s == null)
//            return null;
//        Integer uid = Integer.valueOf(s);

        if (uid != null && "".equals(uid) == false) {

            User user = userRepository.findOne(Integer.valueOf(uid));
            return user;
        }
        return null;
    }

    public String generateUserId() {
        while (true) {
            int abs = RandomUntil.getRandom();
            if (userRepository.findOne(abs) == null) {
                return String.valueOf(abs);
            }
        }
    }

    public String getVerificationCode(String emailTo, HttpSession session) {
        Random random = new Random();
        Email email = new Email("Chat注册验证信息");
        int nextInt = 100000 + random.nextInt(900000);
        System.out.println(nextInt);
        if (session.getAttribute(emailTo) != null) {
            return "服务器已经发送验证码,请输入获取的验证码即可" +
                    "(如果不想使用该邮箱请等待session清除或者启动其他session)";
        }
        session.setAttribute(emailTo, nextInt + "");

        email.setTo(emailTo);

        email.setText("来自Chat的注册验证码:" + nextInt);
//        untilService.sendHtmlEmail(email);
        return "验证码发送成功，请输入验证码";
    }

    public String checkEmail(String email) {
        List<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.size() == 0) {
            return "error";
        }
        return "success";
    }

    @Transactional
    public String register(int userId, String password, String email, String verificationCode, HttpSession session) {

        String vCode = (String) session.getAttribute(email);
        if (vCode == null) {
            return "验证过时或者没有验证,请重新获取验证码";
        }
        if (vCode.equals(verificationCode) == false) {
            return "验证码错误";
        }
        User user = new User();
        user.setUid(userId);
        user.setPassword(password);
        user.setUid(userId);
        user.setName("新注册用户" + userId);
        user.setState("大家好...");
        user.setAvatar(userAvatar);
        user.setEmail(email);
        userRepository.save(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(userId);
        userInfo.setFriendCount(0);
        userInfo.setNoteCount(0);
        userInfoRepository.save(userInfo);
        //添加管理员好友
        String status = relationService.addNewFriend(userId, administrator, false);
        if ("success".equals(status)) {
            Message message = new Message(administrator, userId, "你好，我是管理员，有问题可以反馈给我", 0);
            message.setStatus(MeeageStatusType.UNCHANGEABLE.getValue());
            messageRepository.save(message);
        }
        return "注册成功";
    }

    public String offLine(HttpSession session) {

        Integer userId = SessionUntil.getSessionUser(session);
        if (userId == null) {
            return "未登陆或已过期";
        } else {
            UserComponent component = (UserComponent) GlobalComponent.getInstance().getComponent(UserComponent.NAME);
            Iplayer player = component.getUser(userId);
            if (player != null) {
                player.offline();
                logger.error("玩家下线  playerID:" + player.getId());
            }
            redisServiceUtil.deleteKey(RedisMessageType.LOGIN + userId);
            SessionUntil.offline(userId);
            return "注销成功";
        }
    }
}
