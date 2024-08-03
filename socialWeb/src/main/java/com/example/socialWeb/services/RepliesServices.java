package com.example.socialWeb.services;

import com.example.socialWeb.models.Replies;
import com.example.socialWeb.reoasitory.RepliesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepliesServices {

    private final RepliesRepository repliesRepository;

    public RepliesServices(RepliesRepository repliesRepository) {
        this.repliesRepository = repliesRepository;
    }


    public void add(Replies replies){
        repliesRepository.save(replies);

    }

    public List<Replies> findAll(long id){
        return repliesRepository.findAllReplies(id);
    }
}
