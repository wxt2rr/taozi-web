package code.collector;

import code.dao.UserDao;
import code.pojo.User;
import code.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("register")
    @ResponseBody
    public GlobalResponse register(HttpServletRequest request,
                                   String name, String password, @RequestParam("re_password") String surePassword, String phone,
                                   @RequestParam("v_code") String vCode){
        return registerService.register(request,name,password,surePassword,phone,vCode);
    }

    @RequestMapping("sendVCode")
    @ResponseBody
    public GlobalResponse sendVCode(HttpServletRequest request, HttpServletResponse response,String phone,
                                    @RequestParam(value = "type",defaultValue = "0",required = false) int type){
        if(type <= 0){
            User user = userDao.getUserByPhone(phone);
            if(user != null){
                return GlobalResponse.createByError(-103,"phone had bind");
            }
        }
        return registerService.sendVCode(phone);
    }

    @RequestMapping("forgot")
    @ResponseBody
    public GlobalResponse forgot(String phone,String password,
                                 @RequestParam("v_code") String vCode){
        if(StringUtils.isAnyEmpty(phone,password,vCode)){
            return GlobalResponse.createByError(-1,"error param");
        }

        return registerService.forgot(phone,password,vCode);
    }
}
