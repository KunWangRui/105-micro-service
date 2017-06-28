package cn.demo.service1.controller;

import cn.demo.AbstractController;
import cn.demo.service1.beans.Blog;
import cn.demo.service1.client.Service0Client;
import cn.demo.service1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kun on 17-6-27.
 */
@RestController
public class Service1Controller extends AbstractController {
    @Autowired
    private BlogService blogService;

    private  static final int SUCCESS_STATUS = 200;

    private static  final  int FAIL_STATUS = 500;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public int addBlog(@RequestBody Blog blog){

        int rows =  blogService.addBlog(blog);

        return rows > 0 ?SUCCESS_STATUS:FAIL_STATUS;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public int updateBlog(@RequestBody Blog blog) {

        int rows = blogService.updateBlog(blog);
        return rows > 0 ? SUCCESS_STATUS : FAIL_STATUS;
    }

    @RequestMapping(value = "blog", method = RequestMethod.GET)
    public Blog searchBlog(@RequestParam("id") int id) {
        System.out.println(id);
        Blog blog = blogService.searchBlog(id);
        return blog;
    }

    @RequestMapping("blogs")
    public List<Blog>  getBlogList(){

        return blogService.loadBlogList();
    }
}
