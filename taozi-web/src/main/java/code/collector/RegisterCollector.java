package code.collector;

import code.dao.UserDao;
import code.pojo.User;
import code.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/register/")
public class RegisterCollector {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterService registerService;

    @RequestMapping("register.do")
    @ResponseBody
    public GlobalResponse register(HttpServletRequest request,
            String name,String password,String surePassword,String profession,String phone,String vCode){
        return registerService.register(request,name,password,surePassword,profession,phone,vCode);
    }

    @RequestMapping("sendVCode.do")
    @ResponseBody
    public GlobalResponse sendVCode(HttpServletRequest request, HttpServletResponse response,String phone){
        User user = userDao.getUserByPhone(phone);
        if(user != null){
            return GlobalResponse.createByError(-1,"phone had bind");
        }
        return registerService.sendVCode(phone);
    }
}
