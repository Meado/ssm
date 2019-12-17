package cn.nyse.service;

import cn.nyse.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ArticleService extends IService<Article> {
    PageInfo<Article> selectArticleConditicon(Map<String, Object> params);

    /*
     * 根据文章ID 再次
     * */
    Article selectByAId(long aid, long uid);

    /*
     * 根据用户ID查询用户的收藏文章
     * */
    PageInfo<Article> SelectCollectArticle(Map<String, Object> params);


    int addArticle(long uid, long aid);

    /*
     * 删除收藏文章
     * */
    int deleteArticle(long uid, long aid);
}
