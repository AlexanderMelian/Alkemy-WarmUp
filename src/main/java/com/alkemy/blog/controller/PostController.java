package com.alkemy.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alkemy.blog.helps.Regex;
import com.alkemy.blog.model.Post;
import com.alkemy.blog.services.PostServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/post")
@RestController
public class PostController {
    @Autowired
    private PostServices postServices;

    @GetMapping("/all")
    public List<Post> findAll() {
        return postServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable("id") Long id) {
        return postServices.findById(id);
    }

    @GetMapping()
    public List<Post> findByCategoryAndTitle(@RequestParam Map<String, String> query) {
        // This is temporal, is not scalable
        if (query.size() == 0) {
            return null;
        } else {
            String title = query.get("title");
            String category = query.get("category");
            return postServices.findByTitleAndCategory(title, category);
        }
    }

    @PostMapping("save")
    public Post save(@RequestParam("file") MultipartFile image, @ModelAttribute Post post) {
        if (!image.isEmpty()) {
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                post.setImage(image.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if(Regex.isImageValidUrl(post.getImage())){
                return postServices.save(post);

            }else{
                post.setImage(null);
            }
        }
        return postServices.save(post);
    }

    @PatchMapping("/patch/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postServices.updatePost(id, post);
    }

}
