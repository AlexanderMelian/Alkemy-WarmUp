package com.alkemy.blog.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.alkemy.blog.model.Post;
import com.alkemy.blog.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServices{

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post getById(Long id){
        return postRepository.getById(id);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }
    
    public List<Post> findByCategory(String category){
        return postRepository.findByCategory(category);
    }

    public List<Post> findByTitleAndCategory(String title, String category){
        Set<Post> set = new HashSet<>();
        set.addAll(postRepository.findByTitle(title));
        set.addAll(postRepository.findByCategory(category));
        List<Post> end = new ArrayList<>(set);
        return end;
    }

    public boolean delete(Long id){
        try{
            postRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post post) {
        Optional<Post> updatedPost = postRepository.findById(id);
        if(updatedPost.isPresent()){
            if(post.getTitle() != null){
                updatedPost.get().setTitle(post.getTitle());
            }
            if(post.getContent() != null){
                updatedPost.get().setContent(post.getContent());
            }
            if(post.getImage() != null){
                updatedPost.get().setImage(post.getImage());
            }
            if(post.getCategory() != null){
                updatedPost.get().setCategory(post.getCategory());
            }
            return postRepository.save(updatedPost.get());
        }
        return null;
    }

}
