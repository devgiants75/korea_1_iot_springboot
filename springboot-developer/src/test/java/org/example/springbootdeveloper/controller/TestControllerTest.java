package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.entity.Member;
import org.example.springbootdeveloper.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 메인 애플리케이션 클래스에 있는 @SpringBootApplication을 찾고
// : 해당 클래스의 빈을 찾아 테스트용 애플리케이션 컨텍스트를 생성
@SpringBootTest
// MockMvc를 생성하고 자동으로 구성하는 애너테이션
// : 컨트롤러 테스트 시 사용
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        // 테스트 실행 전 메서드
        // : MockMvc 설정
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        // member 테이블에 있는 데이터를 모두 삭제
        memberRepository.deleteAll();
    }

    // 실제 테스트 코드 작성
    @DisplayName("getAllMembers: 조회 성공")
    @Test
    public void getAllmembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "김다혜"));

        // when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
        .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}
