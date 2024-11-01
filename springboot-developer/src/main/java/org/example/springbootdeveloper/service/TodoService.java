package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.PostTodoRequestDto;
import org.example.springbootdeveloper.dto.response.GetTodoListResponseDto;
import org.example.springbootdeveloper.dto.response.PostTodoResponseDto;
import org.example.springbootdeveloper.dto.response.PutTodoResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Todo;
import org.example.springbootdeveloper.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ResponseDto<List<GetTodoListResponseDto>> getAllTodos() {
        List<GetTodoListResponseDto> data = null;

        try {
            List<Todo> todos = todoRepository.findAll();

            data = todos.stream()
                    .map(GetTodoListResponseDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PostTodoResponseDto> createTodo(PostTodoRequestDto dto) {

        PostTodoResponseDto data = null;

        try {
            Todo todo = Todo.builder()
                    .task(dto.getTask())
                    .status(dto.getStatus())
                    .build();

            todoRepository.save(todo);

            data = new PostTodoResponseDto(todo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<PutTodoResponseDto> updateTodoStatus(Long id, boolean status) {
        PutTodoResponseDto data = null;
        Long todoId = id;

        try {

            Optional<Todo> todoOptional = todoRepository.findById(todoId);

            if (todoOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setStatus(status);

                todoRepository.save(todo);

                data = new PutTodoResponseDto(todo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<Void> deleteTodoById(Long id) {
        Long todoId = id;

        try {
            if (!todoRepository.existsById(todoId)) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            todoRepository.deleteById(todoId);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
