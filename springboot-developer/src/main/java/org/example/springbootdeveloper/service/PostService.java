package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
        try {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .build();
            postRepository.save(post);
            return ResponseDto.setSuccess("게시글이 정상적으로 등록되었습니다.", convertToPostResponseDto(post));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 등록 중 오류가 발생했습니다: " + e.getMessage());
        }

    }

    public ResponseDto<List<PostResponseDto>> getAllPosts() {
        return null;
    }

    public ResponseDto<PostResponseDto> getPostById(Long postId) {
        return null;
    }


    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
        return null;
    }

    public ResponseDto<Void> deletePost(Long postId) {
        return null;
    }

    private PostResponseDto convertToPostResponseDto(Post post) {
        List<CommentResponseDto> commentDtos = post.getComments().stream()
                .map(comment -> new CommentResponseDto(comment.getId(), post.getId(), comment.getContent(), comment.getCommenter()))
                .collect(Collectors.toList());

        return new PostResponseDto(
                post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), commentDtos
        );
    }
}
