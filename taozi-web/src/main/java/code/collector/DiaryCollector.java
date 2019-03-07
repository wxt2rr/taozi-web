package code.collector;

import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import code.service.DiaryService;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/diary/")
public class DiaryCollector {

    @Autowired
    private UserService userService;

    @Autowired
    private DiaryService diaryService;

    @RequestMapping("toDiaryPage.do")
    public ModelAndView toDiaryPage(HttpServletRequest request){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return new ModelAndView("/html/login");
        }else{
            return new ModelAndView("/html/user_diary");
        }
    }

    @RequestMapping("saveBlog.do")
    @ResponseBody
    public GlobalResponse saveBlog(HttpServletRequest request){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-1,"not login");
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String articleKindId = request.getParameter("articleKindId");
        String blogKindId = request.getParameter("blogKindId");
        return diaryService.saveBlogDiary(title,content,articleKindId,blogKindId,userId);
    }

    @RequestMapping("getDiaryListByUserId.do")
    @ResponseBody
    public GlobalResponse getDiaryListByUserId(HttpServletRequest request){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-1,"user not login");
        }
        return diaryService.getBlogDiary(userId);
    }

    @RequestMapping("getDiaryList.do")
    @ResponseBody
    public GlobalResponse getDiaryList(HttpServletRequest request){
        return diaryService.getBlogDiary(null);
    }

    @RequestMapping("getDiaryKind.do")
    @ResponseBody
    public GlobalResponse getDiaryKind(HttpServletRequest request){
        return diaryService.getDiaryKind();
    }

    @RequestMapping("getBlogKind.do")
    @ResponseBody
    public GlobalResponse getBlogKind(HttpServletRequest request){
        return diaryService.getBlogKind();
    }

    @RequestMapping("getDiaryInfo.do")
    @ResponseBody
    public GlobalResponse getDiaryInfo(HttpServletRequest request,@RequestParam("did") int did){
        ModelAndView mv = new ModelAndView("/html/info");
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            mv.addObject("code",-2);
        }
        return GlobalResponse.createBySuccess(diaryService.getDiaryInfo(did,userId));
    }

    @RequestMapping("toGetDiaryInfo.do")
    public ModelAndView toGetDiaryInfo(HttpServletRequest request,int a_user_id){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return new ModelAndView("/html/login");
        }
        if(userId == a_user_id){
            return new ModelAndView("/html/user_diary");
        }
        return new ModelAndView("/html/info");
    }

    @RequestMapping("toMyAttention.do")
    public ModelAndView toMyAttention(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/html/my_attention");
        return mv;
    }

    @RequestMapping("getMyAttention.do")
    @ResponseBody
    public GlobalResponse getMyAttention(HttpServletRequest request){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-2,"not login");
        }
        return diaryService.getMyAttention(userId);
    }
}