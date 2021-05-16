package code.collector;

import code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import code.service.DiaryService;
import util.GlobalResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("saveBlog")
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

    /**
     * @desc 获取博客列表
     * @author wangxt
     * @date 2019/4/7 0007 14:35
     */
    @RequestMapping("getDiaryList")
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

    @RequestMapping("getDiaryInfo")
    @ResponseBody
    public GlobalResponse getDiaryInfo(HttpServletRequest request,int did){
        ModelAndView mv = new ModelAndView();
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

    @RequestMapping("uploadDiaryImg")
    @ResponseBody
    public void uploadDiaryImg(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        if(file == null || file.getSize() <= 0){
            return;
        }
        diaryService.uploadDiaryImg(file,response);
    }

    /**
     * 排序用户博客
     * @param type 1 按照时间升序 2按照时间降序
     * 。
     */
    @RequestMapping("sortUserDiary")
    @ResponseBody
    public GlobalResponse sortUserDiary(HttpServletRequest request, HttpServletResponse response,
                                        int type){
        int userId = userService.getUserIdBySession(request);
        if(userId <= 0){
            return GlobalResponse.createByError(-2,"not login");
        }
        return diaryService.sortUserDiary(userId, type);
    }
}