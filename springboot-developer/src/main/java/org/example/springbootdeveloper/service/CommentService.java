package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.request.CommentRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public ResponseDto<CommentResponseDto> createComment(CommentRequestDto dto) {
        return null;
    }

    public ResponseDto<CommentResponseDto> getCommentsByPost(Long postId) {
    }

    public ResponseDto<CommentResponseDto> updateComment(Long commentId, String newContent) {
    }

    public ResponseDto<CommentResponseDto> deleteComment(Long commentId) {
    }
}
