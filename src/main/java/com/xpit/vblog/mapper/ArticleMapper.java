package com.xpit.vblog.mapper;

import com.xpit.vblog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int addNewArticle(Article article);

    int updateArticle(Article article);

    List<Article> getArticleByState(
            @Param("state") Integer state,
            @Param("start") Integer start,
            @Param("count") Integer count,
            @Param("uid") Long uid,
            @Param("keywords") String keywords);

    int getArticleCountByState(
            @Param("state") Integer state,
            @Param("uid") Long uid,
            @Param("keywords") String keywords);

    int updateArticleState(@Param("aids") Long[] aids, @Param("state") Integer state);

    int updateArticleStateById(@Param("articleId") Integer articleId,
                               @Param("state") Integer state);

    int deleteArticleById(@Param("aids") Long[] aids);

    Article getArticleById(Long aid);

    void pvIncrement(Long aid);

    void pvStatisticsPerDay();

    List<String> getCategories(Long uid);

    List<Integer> getDataStatistice(Long uid);
}
