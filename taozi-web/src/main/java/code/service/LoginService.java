package code.service;

import code.dao.UserDao;
import code.pojo.User;
import manage.ConstantManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.GlobalResponse;
import util.Md5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    @Autowired
    private UserDao loginDao;

    public GlobalResponse login(String account, String password,int isRember, HttpServletRequest request,HttpServletResponse response) {
        password = Md5Util.getStringMD5(password);
        User user = loginDao.checkUser(account, password);
        if(user != null){
            request.getSession().setAttribute(ConstantManage.USER_SESSION_KEY,user.getUserId());
            if(isRember > 0){
                Cookie userCookie = new Cookie("loginInfo",String.format("%s_%s_%s",user.getUserId(),password,account));
                userCookie.setMaxAge(30 * 24 * 60 * 60);//存活期为一个月 30*24*60*60
                userCookie.setPath("/");
                response.addCookie(userCookie);
            }
            return GlobalResponse.createBySuccess();
        }else{
            return GlobalResponse.createByError(-1,"no had user");
        }
    }
}
