package code.service;

import code.dao.UserDao;
import code.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private RedisCache redisCache;

    public GlobalResponse sendVCode(String phone) {
        Map<String, String> templateParam = new HashMap<String,String>();
        String authCode = ValidateCodeUtil.getRandomNumber(4, null);
        templateParam.put("code", authCode);
        String code = smsUtil.sendSms(phone, "SMS_144941081", templateParam);
        if(code == null || !"OK".equals(code)){
            return GlobalResponse.createByError(-1,"send error");
        }
        redisCache.set(phone,authCode);
        return GlobalResponse.createBySuccess();
    }

    public GlobalResponse register(HttpServletRequest request,String name, String password, String surePassword, String profession, String phone, String vCode) {
        Object o = redisCache.get(phone);
        String s = o == null ? null : String.valueOf(o);
        if(s == null || !s.equals(vCode)){
            return GlobalResponse.createByError(-2,"vCode is wrong");
        }
        if(password == null || surePassword == null || !password.equals(surePassword)){
            return GlobalResponse.createByError(-3,"password is inequality");
        }
        User user = new User();
        user.setNickname(name);
        user.setPassword(Md5Util.getStringMD5(password));
        user.setProfession(profession);
        user.setPhone(phone);
        userDao.saveUser(user);
        if(user.getUserId() > 0){
            return GlobalResponse.createBySuccess();
        }else{
            return GlobalResponse.createByError(-4,"save user error");
        }
    }
}
