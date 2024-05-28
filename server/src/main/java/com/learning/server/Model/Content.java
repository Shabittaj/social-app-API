package com.learning.server.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany
    private List<User> likePost=new ArrayList<>();

    @OneToMany
    private List<Comment> commentPost= new ArrayList<>();

    private LocalDateTime createdAt;


    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", user=" + user +
                ", likePost=" + likePost +
                ", commentPost=" + commentPost +
                ", createdAt=" + createdAt +
                '}';
    }


}
