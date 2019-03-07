package code.service;

import code.dao.DiaryDao;
import code.dao.UserDao;
import code.pojo.BlogArticle;
import code.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateUtilEYK;
import util.GlobalResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("diaryService")
public class DiaryService {
    @Autowired
    private DiaryDao diaryDao;

    @Autowired
    private UserDao userDao;

    public GlobalResponse saveBlogDiary(String title, String content, String articleKindId, String blogKindId,int userId) {
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setArticleKindId(Integer.parseInt(articleKindId));
        blogArticle.setBlogKindId(Integer.parseInt(blogKindId));
        blogArticle.setContent(content);
        blogArticle.setTitle(title);
        blogArticle.setUserId(userId);
        diaryDao.saveBlogDiary(blogArticle);
        if(blogArticle.getId() > 0){
            return GlobalResponse.createBySuccess();
        }
        return GlobalResponse.createByError(-1,"save error");
    }

    public GlobalResponse getBlogDiary(Integer userId) {
        List<BlogArticle> blogDiary = diaryDao.getBlogDiary(userId);
        for(BlogArticle b : blogDiary){
            b.setTime(DateUtilEYK.parseDateToStr(b.getCreateTime(),DateUtilEYK.DateFormatEnum.d));
        }
        return GlobalResponse.createBySuccess(blogDiary);
    }

    public GlobalResponse getDiaryKind() {
       return GlobalResponse.createBySuccess(diaryDao.getDiaryKind());
    }

    public GlobalResponse getBlogKind(){
        return GlobalResponse.createBySuccess(diaryDao.getBlogKind());
    }

    public Map<String,Object> getDiaryInfo(int did,int userId) {
        Map<String, Object> diaryInfo = diaryDao.getDiaryInfo(did);
        List<Integer> ids = userDao.getAttentionUser(userId);
        int author = Integer.parseInt(String.valueOf(diaryInfo.get("user_id")));
        int type = 0;
        if(ids.contains(author)){
            type = 1;
        }
        diaryInfo.put("isAttention",type);
        return diaryInfo;
    }

    public GlobalResponse getMyAttention(int userId) {
        List<Integer> attentionUser = userDao.getAttentionUser(userId);
        return GlobalResponse.createBySuccess(userDao.getUserByIds(attentionUser));
    }
}
