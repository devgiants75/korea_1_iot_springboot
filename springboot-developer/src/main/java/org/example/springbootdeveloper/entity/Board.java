package org.example.springbootdeveloper.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

enum Category {
    NOTICE, // 공지사항
    FREE, // 자유게시판
    QNA, // 문의
    EVENT // 이벤트
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=50)
    private String writer;

    @Column(nullable = false, length=100)
    private String title;

    @Column(nullable = false, length=500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
}
