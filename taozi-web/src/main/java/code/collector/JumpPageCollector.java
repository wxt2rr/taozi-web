package code.collector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jumpPage/")
public class JumpPageCollector {
    /**
     * Description:WEB-INF下的页面跳转
     * @param c 类名
     * @param m 方法名
     * @return 页面地址
     * @date 2018/09/13 19:34
     * @author wangxiaotao
     */
    @RequestMapping(value = "{c}/{m}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("c") String c, @PathVariable("m") String m) {
        return new ModelAndView(c + "/" + m);
    }
}
