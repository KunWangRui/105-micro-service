package cn.demo.service1.service;

import cn.demo.service1.beans.Blog;

import java.util.List;

/**
 * Created by zhenyu on 2017/6/25.
 */
public interface BlogService {

    int addBlog(Blog blog);
    int updateBlog(Blog blog);
    Blog searchBlog(int id);
    List<Blog> loadBlogList();
}
