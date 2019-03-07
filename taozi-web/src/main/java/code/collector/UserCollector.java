package code.collector;

import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user/")
@Controller
public class UserCollector {

    @Autowired
    private UserService userService;

    @RequestMapping("uploadHead.do")
    @ResponseBody
    public GlobalResponse uploadHead(HttpServletRequest request,String base64){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-2,"not login");
        }

        return userService.uploadHead(userId,base64);
    }

    @RequestMapping("updateInfo.do")
    @ResponseBody
    public GlobalResponse updateInfo(HttpServletRequest request,
                                     String name,@RequestParam(value = "job",required = false,defaultValue = "")String job,
                                     @RequestParam(value = "sex",required = false,defaultValue = "0") int sex,
                                     @RequestParam(value = "aboutme",required = false,defaultValue = "") String aboutme){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-2,"not login");
        }
        return userService.updateInfo(userId,name,job,sex,aboutme);
    }

    @RequestMapping("attentionUser.do")
    @ResponseBody
    public GlobalResponse attentionUser(HttpServletRequest request,int a_user_id, int type){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-2,"not login");
        }
        return userService.attentionUser(userId,a_user_id,type);
    }
}
