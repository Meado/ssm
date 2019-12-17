package cn.nyse.util;

import cn.nyse.entity.Article;
import cn.nyse.entity.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description 根据id去除重复
 * @date 2019/12/7 21
 */
public class Deduplication {
    public static List<User> getSame(List<User> collmax, List<User> collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        List<User> csReturn = new LinkedList<>();
        List<User> max = collmax;
        List<User> min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (User user : max) {
            map.put(user.getId(), 1);
        }
        for (User user : min) {
            if (map.get(user.getId()) != null) {
                csReturn.add(user);
            }
        }
        return csReturn;
    }

    public static List<Article> getCollect(List<Article> collmax, List<Article> collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        List<Article> csReturn = new LinkedList<>();
        List<Article> max = collmax;
        List<Article> min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Article user : max) {
            map.put(user.getId(), 1);
        }
        for (Article user : min) {
            if (map.get(user.getId()) != null) {
                csReturn.add(user);
            }
        }
        return csReturn;
    }
}
