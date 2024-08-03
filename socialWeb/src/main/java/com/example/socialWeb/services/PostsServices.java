package com.example.socialWeb.services;

import com.example.socialWeb.models.Posts;
import com.example.socialWeb.reoasitory.PostsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServices {

    private PostsRepository postsRepository;

    public PostsServices(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }


    public void add(Posts post){
        postsRepository.save(post);
    }


    public List<Posts> findAll(){
        return postsRepository.findAllPosts();

    }


    public Posts getById(long id){
        return postsRepository.findById(id).get();
    }


    public void addUp(long id){
        Posts post=getById(id);
        post.setUp(post.getUp()+1);
        post.setPostId(id);
        postsRepository.save(post);
    }
    public void addDown(long id){
        Posts post=getById(id);
        post.setDown(post.getDown()+1);
        post.setPostId(id);
        postsRepository.save(post);
    }

    public List<Posts> searchGenre(String word){
        return postsRepository.findBySubject(word.toLowerCase());
    }
}
