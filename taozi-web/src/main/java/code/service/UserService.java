package code.service;

import code.dao.UserDao;
import code.pojo.User;
import com.alibaba.fastjson.JSONObject;
import manage.ConstantManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import util.GlobalResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisCache redisCache;

    public int getUserIdBySession(HttpServletRequest request){
        Object userId = request.getSession().getAttribute(ConstantManage.USER_SESSION_KEY);
        if(null == userId){
            return 0;
        }
        return Integer.parseInt(userId.toString());
    }

    public User getUserBySession(HttpServletRequest request){
        int userId = getUserIdBySession(request);
        if(userId <= 0){
            return null;
        }
        return getSetUser(userId);
    }

    private User getSetUser(int userId){
        Object o = redisCache.get(String.valueOf(userId));
        String s = o == null ? null : String.valueOf(o);
        if(s != null && !"".equals(s)){
            return JSONObject.parseObject(s,User.class);
        }
        User user = userDao.getUserById(userId);
        redisCache.set(String.valueOf(userId),JSONObject.toJSONString(user));
        return user;
    }

    public GlobalResponse uploadHead(int userId, String base64) {
        if(base64 == null || "".equals(base64)){
            return GlobalResponse.createByError(-3,"param is error");
        }
        base64 = base64.substring(22,base64.length());
        BASE64Decoder decoder = new BASE64Decoder();
        try{
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "/root/software/tomcat/apache-tomcat-9.0.17/webapps/ROOT/user/head/" + userId + ".png";//新生成的图片
            //String imgFilePath = "D:\\taozi-web-git\\taozi-web\\src\\main\\webapp\\user\\head\\" + userId + ".png";//新生成的图片
            String path = "/user/head/" + userId + ".png";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            userDao.uploadHead(userId,path,new Date());
        }catch (Exception e){
            return GlobalResponse.createByError(-4,e.toString());
        }
        redisCache.del(String.valueOf(userId));
        return GlobalResponse.createBySuccess();
    }

    public GlobalResponse updateInfo(int userId, String name, String job, int sex, String aboutme) {
        int res = userDao.updateInfo(userId,name,job,sex,aboutme);
        if(res <= 0){
            return GlobalResponse.createByError(-2,"error");
        }else{
            redisCache.del(String.valueOf(userId));
            return GlobalResponse.createBySuccess();
        }
    }

    public GlobalResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
        int userId = getUserIdBySession(request);
        redisCache.del(String.valueOf(userId));
        request.getSession().removeAttribute(ConstantManage.USER_SESSION_KEY);
        Cookie userCookie = new Cookie("loginInfo","");
        userCookie.setMaxAge(0);//存活期为一个月 30*24*60*60
        userCookie.setPath("/");
        response.addCookie(userCookie);
        return GlobalResponse.createBySuccess();
    }

    public GlobalResponse attentionUser(int userId, int aUserId,int type) {
        if(type == 0){//新增
            return GlobalResponse.createBySuccess(userDao.attentionUser(userId,aUserId));
        }else{//删除
            return GlobalResponse.createBySuccess(userDao.quitAttentionUser(userId,aUserId));
        }
    }
}
