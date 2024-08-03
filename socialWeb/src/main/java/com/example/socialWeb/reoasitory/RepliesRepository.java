package com.example.socialWeb.reoasitory;

import com.example.socialWeb.models.Replies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Replies,Long> {
    @Query(nativeQuery = true,value = "SELECT * from replies WHERE post_id=:postId")
    List<Replies>  findAllReplies(@Param("postId") long Id);

}
