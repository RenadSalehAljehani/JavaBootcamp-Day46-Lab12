package com.example.blogsystem_lab12.Controller;

import com.example.blogsystem_lab12.Api.ApiResponse;
import com.example.blogsystem_lab12.Model.Blog;
import com.example.blogsystem_lab12.Model.MyUser;
import com.example.blogsystem_lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    // 1. CRUD
    // 1.1 Get all blogs
    @GetMapping("/get")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    // 1.2 Add new blog
    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog) {
        blogService.addBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("New Blog Added."));
    }

    // 1.3 Update blog
    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(myUser.getId(), blogId, blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog Updated."));
    }

    // 1.4 Delete blog
    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId) {
        blogService.deleteBlog(myUser.getId(), blogId);
        return ResponseEntity.status(200).body(new ApiResponse("Blog Deleted."));
    }

    // 2. Extra endpoints
    // 2.1 Get all user blogs
    @GetMapping("/userBlogs")
    public ResponseEntity getBlogsByUser(@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body( blogService.getBlogsByUser(myUser));
    }

    // 2.2 Get blog by id
    @GetMapping("getByiId/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

    // 2.3 Get blog by title
    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity getBlogsByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.getBlogsByTitle(title));
    }
}