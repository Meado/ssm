package cn.nyse.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
public class ArticleProvider {
    public String selectArticleConditicon(Map<String, Object> condition){
        StringBuilder sb = new StringBuilder();
        sb.append("select ar.* from   " +
                "article ar,`user` u  " +
                "where  " +
                "ar.user_id = u.id ");
        if(condition.containsKey("uid")&&!StringUtils.isEmpty(condition.get("uid"))){
            sb.append(" and u.id=#{uid} ");
        }
        if(condition.containsKey("title")&&!StringUtils.isEmpty(condition.get("title"))){
            sb.append(" and ar.title  like CONCAT('%',#{title},'%')");
        }
        return sb.toString();
    }

    public String selectFavorriteConditicon(Map<String, Object> condition){
        StringBuilder sb = new StringBuilder();
        sb.append("select " +
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
                "u.id = f.u_id ");
        if(condition.containsKey("uid")&&!StringUtils.isEmpty(condition.get("uid"))){
            sb.append(" where  f.u_id=#{uid} ");
        }
        if(condition.containsKey("title")&&!StringUtils.isEmpty(condition.get("title"))){
            sb.append(" and ar.title  like CONCAT('%',#{title},'%')");
        }
        return sb.toString();
    }
}
