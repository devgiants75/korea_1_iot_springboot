package org.example.springbootdeveloper.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.MenuRequestDto;
import org.example.springbootdeveloper.dto.response.MenuResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Menu;
import org.example.springbootdeveloper.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public ResponseDto<MenuResponseDto> createMenu(MenuRequestDto dto) {

        MenuResponseDto data = null;

        try {
            Menu menu = Menu.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .price(dto.getPrice())
                    .isAvailable(dto.isAvailable())
                    .category(dto.getCategory())
                    .size(dto.getSize())
                    .build();

            menuRepository.save(menu);

            data = new MenuResponseDto(menu);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<MenuResponseDto>> getAllMenus() {
        List<MenuResponseDto> data = null;

        try {
            List<Menu> menus = menuRepository.findAll();

            data = menus.stream()
                    .map((menu) -> new MenuResponseDto(menu))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<MenuResponseDto> getMenuById(Long id) {
        return null;
    }

    public ResponseDto<MenuResponseDto> updateMenu(Long id, MenuRequestDto dto) {
        return null;
    }

    public ResponseDto<Void> deleteMenu(Long id) {
        return null;
    }
}
