package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.PostTodoRequestDto;
import org.example.springbootdeveloper.dto.response.GetTodoListResponseDto;
import org.example.springbootdeveloper.dto.response.PostTodoResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Todo;
import org.example.springbootdeveloper.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.TODO)
public class TodoController {

    private final TodoService todoService;

    // === MenuController mapping pattern 설정 ===
    public static final String TODO_POST = "/";

//    public static final String TODO_GET_MENU_ID = "/{id}";
    public static final String TODO_GET_LIST = "/list";
//    public static final String TODO_GET_MENU_CATEGORY = "/search/category";

    public static final String TODO_PUT = "/{id}";

    public static final String TODO_DELETE = "/{id}";

    @GetMapping
    public ResponseEntity<ResponseDto<List<GetTodoListResponseDto>>> getAllTodos() {
        try {
            ResponseDto<List<GetTodoListResponseDto>> todos = todoService.getAllTodos();
            return ResponseEntity.status(HttpStatus.OK).body(todos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

//    @PostMapping
//    public ResponseEntity<ResponseDto<PostTodoResponseDto>> createTodo(@RequestBody PostTodoRequestDto dto) {
//        Todo createdTodo = todoService.createTodo(dto);
//        return ResponseEntity.ok(ResponseDto.setSuccess("Todo created successfully", createdTodo));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseDto<Todo>> updateTodoStatus(@PathVariable Long id, @RequestBody Todo todo) {
//        Todo updatedTodo = todoService.updateTodoStatus(id, todo.isStatus());
//        if (updatedTodo != null) {
//            return ResponseEntity.ok(ResponseDto.setSuccess("Todo status updated successfully", updatedTodo));
//        } else {
//            return ResponseEntity.status(404).body(ResponseDto.setFailed("Todo not found"));
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseDto<Void>> deleteTodoById(@PathVariable Long id) {
//        boolean isDeleted = todoService.deleteTodoById(id);
//        if (isDeleted) {
//            return ResponseEntity.ok(ResponseDto.setSuccess("Todo deleted successfully", null));
//        } else {
//            return ResponseEntity.status(404).body(ResponseDto.setFailed("Todo not found"));
//        }
//    }
}
