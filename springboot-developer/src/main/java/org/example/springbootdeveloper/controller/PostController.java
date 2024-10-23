package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.POST)
public class PostController {

    @Autowired
    private PostService postService;

    // CRUD 기능 명시
    @PostMapping
    public ResponseEntity<ResponseDto<PostResponseDto>> createPost(@RequestBody PostRequestDto dto) {
        ResponseDto<PostResponseDto> result = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseDto<PostResponseDto> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public ResponseDto<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto dto) {
        return postService.updatePost(postId, dto);
    }

    @DeleteMapping("/{postId}")
    public ResponseDto<Void> deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}