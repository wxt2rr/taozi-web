package code.collector;

import code.service.TestService;
import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test/")
public class TestCollector {

    @Autowired
    private TestService testService;

    @RequestMapping("test.do")
    @ResponseBody
    public GlobalResponse test(HttpServletRequest request,String name){
        return testService.test(name);
    }
}
