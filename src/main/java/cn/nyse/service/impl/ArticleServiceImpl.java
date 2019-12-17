package cn.nyse.service.impl;

import cn.nyse.dao.ArticleMapper;
import cn.nyse.dao.UserMapper;
import cn.nyse.entity.Article;
import cn.nyse.entity.User;
import cn.nyse.service.ArticleService;
import cn.nyse.service.IService;
import cn.nyse.util.Deduplication;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl extends ServiceImp<Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Article> selectArticleConditicon(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));


        List<Article> articles = articleMapper.selectArticleConditicon(params);
        if (params.containsKey("id") && !StringUtils.isEmpty(params.get("id"))) {
            Integer uid = (Integer) params.get("id");
            for (Article article : articles) {
                //设置查到的用户是否为登录账号用户的关注人
                List<User   > users1 = articleMapper.selectByFaId(article.getId());
                List<User> users = userMapper.selectFocus(uid);
                /*
                 * 去重后得到的user集合
                 * */
                List<User> same = Deduplication.getSame(users, users1);
                article.setCommon(same);

            }
        }

        /*if (params.containsKey("id")&&!StringUtils.isEmpty(params.get("id"))){
            Integer uid = (Integer) params.get("id");
            for (Article article : articles) {
                //设置这篇文章是否是已经收藏的文章
                List<Article> collect = mapper.selectByUId(uid);
                for (Article c : collect) {
                    if (article.getId()==c.getId()){
                        article.setFlag(1);
                    }
                }

            }
        }*/
        return new PageInfo<Article>(articles);
    }

    /*
     * 根据文章ID查询文章信息，在根据当前的用户ID和文章id查询两个用户的信息，然后去重，存入Common中
     * */
    @Override
    public Article selectByAId(long aid, long uid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        List<User> users1 = articleMapper.selectByFaId(aid);
        List<User> users = userMapper.selectFocus(uid);
        /*
         * 去重后得到的user集合
         * */
        List<User> same = Deduplication.getSame(users, users1);
        article.setCommon(same);

        //设置这篇文章是否是已经收藏的文章
        List<Article> collect = articleMapper.selectByUId(uid);
        for (Article c : collect) {
            if (article.getId() == c.getId()) {
                article.setFlag(1);
            }
        }
        return article;
    }


    /*
     * 根据用户ID查询用户的收藏文章
     * */
    @Override
    public PageInfo<Article> SelectCollectArticle(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 3);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));


        List<Article> articles = articleMapper.selectFavorriteConditicon(params);
        if (params.containsKey("uid") && !StringUtils.isEmpty(params.get("uid"))) {
            Integer uid = (Integer) params.get("uid");
            for (Article article : articles) {
                //设置查到的用户是否为登录账号用户的关注人
                List<User> users1 = articleMapper.selectByFaId(article.getId());
                List<User> users = userMapper.selectFocus(uid);
                /*
                 * 去重后得到的user集合
                 * */
                List<User> same = Deduplication.getSame(users, users1);
                article.setCommon(same);

            }
        }

        return new PageInfo<Article>(articles);
    }

    /*
     * 添加收藏文章
     * */
    @Override
    public int addArticle(long uid, long aid) {
        return articleMapper.addArticle(uid, aid);
    }

    /*
     * 删除收藏文章
     * */
    @Override
    public int deleteArticle(long uid, long aid) {
        return articleMapper.deleteArticle(uid, aid);
    }
}
