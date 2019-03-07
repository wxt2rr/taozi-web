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
}
