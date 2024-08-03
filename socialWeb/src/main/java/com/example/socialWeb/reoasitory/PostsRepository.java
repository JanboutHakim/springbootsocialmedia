package com.example.socialWeb.reoasitory;

import com.example.socialWeb.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM posts WHERE lower(subject) like %:word% ORDER BY posts.up")
    List<Posts> findBySubject(@Param("word") String word);
    @Query(nativeQuery = true,value = "SELECT * FROM posts  ORDER BY posts.up")
    List<Posts> findAllPosts();
}
