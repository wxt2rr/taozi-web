package code.service;

import code.dao.DiaryDao;
import code.dao.UserDao;
import code.pojo.BlogArticle;
import code.pojo.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.DateUtilEYK;
import util.GlobalResponse;
import util.Md5Util;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("diaryService")
public class DiaryService {
    @Autowired
    private DiaryDao diaryDao;

    @Autowired
    private UserDao userDao;

    public GlobalResponse saveBlogDiary(String title, String content, String articleKindId, String blogKindId,int userId) {
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setArticleKindId(0);
        blogArticle.setBlogKindId(0);
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

    public void uploadDiaryImg(MultipartFile file, HttpServletResponse response) {
        String fileMD5 = UUID.randomUUID().toString();
        String path = "http://127.0.0.1:8088/images/diary/" + fileMD5 + ".png";
        try {
            byte[] bytes = file.getBytes();
            String imgFilePath = "D:\\taozi-web-git\\taozi-web\\src\\main\\webapp\\images\\diary\\" + fileMD5 + ".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray array = new JSONArray();
        array.add(path);
        JSONObject res = new JSONObject();
        res.put("success",true);
        res.put("msg","成功");
        res.put("code",1);
        res.put("data",array);
        try {
            response.getWriter().write(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 排序用户博客
     * @param  userId 用户id
     * @param type 1按照时间升序  2按照时间降序
     */
    public GlobalResponse sortUserDiary(int userId, int type) {
        List<BlogArticle> blogDiary = diaryDao.sortUserDiary(userId, type);
        for(BlogArticle b : blogDiary){
            b.setTime(DateUtilEYK.parseDateToStr(b.getCreateTime(),DateUtilEYK.DateFormatEnum.d));
        }
        return GlobalResponse.createBySuccess(blogDiary);
    }
}
