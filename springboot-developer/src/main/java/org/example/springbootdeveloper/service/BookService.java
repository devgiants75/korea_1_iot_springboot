package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.request.BookRequestDto;
import org.example.springbootdeveloper.dto.request.BookRequestUpdateDto;
import org.example.springbootdeveloper.dto.response.BookResponseDto;
import org.example.springbootdeveloper.entity.Book;
import org.example.springbootdeveloper.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 1. 게시글 생성(Post)
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(
                null, requestDto.getWriter(), requestDto.getTitle(),
                requestDto.getContent(), requestDto.getCategory()
        );

        Book savedBook = bookRepository.save(book);
        return convertToResponseDto(savedBook);
    }

    // 2. 전체 책 조회
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                // .map((book) -> convertToResponseDto(book))
                .collect(Collectors.toList());
    }

    // 3. 특정 ID 책 조회
    public BookResponseDto getBookById(Long id) {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));
            return convertToResponseDto(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BookResponseDto();
        }
    }

    // 4, 특정 ID 책 수정
    public BookResponseDto updateBook(Long id, BookRequestUpdateDto updateDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다: " + id));

        book.setTitle(updateDto.getTitle());
        book.setContent(updateDto.getContent());
        book.setCategory(updateDto.getCategory());

        Book updatedBook = bookRepository.save(book);
        return convertToResponseDto(updatedBook);
    }

    // 5. 책 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Entity -> Response Dto 변환
    private BookResponseDto convertToResponseDto(Book book) {
        return new BookResponseDto(
                book.getId(), book.getWriter(), book.getTitle()
                , book.getContent(), book.getCategory()
        );
    }
}
