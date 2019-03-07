package code.collector;

import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/write/")
public class WriteCollector {
    @Autowired
    private UserService userService;

    @RequestMapping("toWritePage.do")
    public ModelAndView toWritePage(HttpServletRequest request){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return new ModelAndView("/html/login");
        }else{
            return new ModelAndView("/html/read");
        }
    }
}
