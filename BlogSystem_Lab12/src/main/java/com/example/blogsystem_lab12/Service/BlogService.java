package com.example.blogsystem_lab12.Service;

import com.example.blogsystem_lab12.Api.ApiException;
import com.example.blogsystem_lab12.Model.Blog;
import com.example.blogsystem_lab12.Model.MyUser;
import com.example.blogsystem_lab12.Repository.AuthRepository;
import com.example.blogsystem_lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    // 1. CRUD
    // 1.1 Get all blogs
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // 1.2 Add new blog
    public void addBlog(Integer userId, Blog blog) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("User Not Found.");
        }
        blog.setUser(myUser);
        blogRepository.save(blog);
    }

    // 1.3 Update blog
    public void updateBlog(Integer userId, Integer blogId, Blog updatedBlog) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("User Not Found.");
        }

        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog Not Found.");
        }

        if (blog.getUser() == null || !blog.getUser().getId().equals(userId)) {
            throw new ApiException("You Are Not Allowed To Update This Blog.");
        }
        blog.setTitle(updatedBlog.getTitle());
        blog.setBody(updatedBlog.getBody());
        blogRepository.save(blog);
    }

    // 1.4 Delete blog
    public void deleteBlog(Integer userId, Integer blogId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("User Not Found.");
        }

        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null) {
            throw new ApiException("Blog Not Found.");
        }

        if (blog.getUser() == null || !blog.getUser().getId().equals(userId)) {
            throw new ApiException("You Are Not Allowed To Delete This Blog.");
        }
        blogRepository.delete(blog);
    }

    // 2. Extra endpoints
    // 2.1 Get all user blogs
    public List<Blog> getBlogsByUser(MyUser user) {
        return blogRepository.findAllByUser(user);
    }

    // 2.2 Get blog by id
    public Blog getBlogById(Integer id) {
        Blog blog = blogRepository.findBlogById(id);
        if (blog == null) {
            throw new ApiException("Blog Not Found.");
        }
        return blog;
    }

    // 2.3 Get blog by title
    public List<Blog> getBlogsByTitle(String title) {
        return blogRepository.findBlogByTitle(title);
    }
}