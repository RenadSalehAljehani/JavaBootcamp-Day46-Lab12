package com.example.blogsystem_lab12.Repository;

import com.example.blogsystem_lab12.Model.Blog;
import com.example.blogsystem_lab12.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);
    List<Blog> findAllByUser(MyUser user);
    List<Blog> findBlogByTitle(String title);
}