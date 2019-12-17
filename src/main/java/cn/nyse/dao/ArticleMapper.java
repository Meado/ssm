package cn.nyse.dao;

import cn.nyse.entity.Article;
import cn.nyse.entity.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends Mapper<Article> {
    @SelectProvider(type = ArticleProvider.class, method = "selectArticleConditicon")
    List<Article> selectArticleConditicon(Map<String, Object> params);

    /*
     * 分页查询收藏文章
     * */
    @SelectProvider(type = ArticleProvider.class, method = "selectFavorriteConditicon")
    List<Article> selectFavorriteConditicon(Map<String, Object> params);

    @Select("select   " +
            "u.*   " +
            "from   " +
            "article ar,`user` u,favorite fa  " +
            "where  " +
            "u.id=fa.u_id  " +
            "and   " +
            "ar.id=fa.a_id  " +
            "and  " +
            "fa.a_id =#{fid}")
    List<User> selectByFaId(long id);


    /*
     * 根据用户ID查询收藏文章
     * */
    @Select("select " +
            "ar.* " +
            "from " +
            "article ar " +
            "left join " +
            "favorite f " +
            "on " +
            "ar.id=f.a_id " +
            "left join  " +
            "`user` u " +
            "on " +
            "u.id = f.u_id " +
            "where " +
            "f.u_id=#{uid}")
    List<Article> selectByUId(long uid);

    /*
     * 添加收藏文章
     * */
    @Insert("INSERT into favorite " +
            "VALUES(#{uid},#{aid})")
    int addArticle(@Param("uid") long uid, @Param("aid") long aid);

    /*
     * 删除收藏文章
     * */
    @Delete("delete  " +
            "from favorite  " +
            "where " +
            "favorite.u_id = #{uid} " +
            "and " +
            "favorite.a_id = #{aid}")
    int deleteArticle(@Param("uid") long uid, @Param("aid") long aid);
}