package code.dao;

import code.pojo.BlogArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DiaryDao {

    void saveBlogDiary(BlogArticle article);

    List<BlogArticle> getBlogDiary(@Param("userId") Integer userId);

    List<Map<String,Object>> getDiaryKind();

    List<Map<String,Object>> getBlogKind();

    Map<String,Object> getDiaryInfo(int did);

    /**
     * 查询用户博客列表
     * @param userId 用户id
     * @param type 1按照时间升序 2按照时间降序
     * @return
     */
    List<BlogArticle> sortUserDiary(@Param("userId")int userId, @Param("type")int type);
}
