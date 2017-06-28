package cn.demo.service1.service;

import cn.demo.service1.beans.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhenyu on 2017/6/25.
 */
@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Blog> loadBlogList() {

        String sql = "select * from blog";

        List<Blog> blogList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Blog>(Blog.class));
        return blogList;
    }

    @Override
    public int addBlog(Blog blog) {
        try {
            String insertSql = "insert into blog(title,content,author) values(?,?,?)";
            return jdbcTemplate.update(insertSql, blog.getTitle(), blog.getContent(), blog.getAuthor());
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }

    @Override
    public int updateBlog(Blog blog) {
        try {
            String updateSql = "update blog set title=?,content=?,author=? where id = ?";
            return jdbcTemplate.update(updateSql, blog.getTitle(), blog.getContent(), blog.getAuthor(), blog.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Blog searchBlog(int id) {
        return jdbcTemplate.queryForObject("select * from blog where id=?", new Object[]{id}, new BeanPropertyRowMapper<Blog>(Blog.class));
    }
}
