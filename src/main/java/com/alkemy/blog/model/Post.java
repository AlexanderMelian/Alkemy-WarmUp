package com.alkemy.blog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private Long pk_postid;
    private String title;
    private String content;
    private String image;
    private String category;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date creation_date;
    private Long user_id;
}
