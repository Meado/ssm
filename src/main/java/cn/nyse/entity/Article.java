package cn.nyse.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "article")
public class Article {
    @Id
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 浏览次数
     */
    @Column(name = "browse_count")
    private Integer browseCount;

    /**
     * 发布时间
     */
    @Column(name = "publish_date")
    private Date publishDate;

    /**
     * 发布人名称
     */
    @Column(name = "publish_real_name")
    private String publishRealName;

    /**
     * 发布人id
     */
    @Column(name = "user_id")
    private Integer userId;

    @Transient
    List<User> common;

    /*
     * 设置取消收藏的标志
     * */
    @Transient
    Integer flag=0;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<User> getCommon() {
        return common;
    }

    public void setCommon(List<User> common) {
        this.common = common;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取浏览次数
     *
     * @return browse_count - 浏览次数
     */
    public Integer getBrowseCount() {
        return browseCount;
    }

    /**
     * 设置浏览次数
     *
     * @param browseCount 浏览次数
     */
    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    /**
     * 获取发布时间
     *
     * @return publish_date - 发布时间
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置发布时间
     *
     * @param publishDate 发布时间
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 获取发布人名称
     *
     * @return publish_real_name - 发布人名称
     */
    public String getPublishRealName() {
        return publishRealName;
    }

    /**
     * 设置发布人名称
     *
     * @param publishRealName 发布人名称
     */
    public void setPublishRealName(String publishRealName) {
        this.publishRealName = publishRealName == null ? null : publishRealName.trim();
    }

    /**
     * 获取发布人id
     *
     * @return user_id - 发布人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置发布人id
     *
     * @param userId 发布人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}