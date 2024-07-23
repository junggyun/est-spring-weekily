package com.study.estspringweekly.service;

import com.study.estspringweekly.domain.menu.Category;
import com.study.estspringweekly.domain.menu.MenuRequest;
import com.study.estspringweekly.domain.menu.Menu;
import com.study.estspringweekly.domain.menu.MenuDTO;
import com.study.estspringweekly.repository.MenuRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    //메뉴 단건 조회
    public MenuDTO getMenu(Long id) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));

        return new MenuDTO(menu);
    }

    //메뉴 전체 조회
    public List<MenuDTO> getAllMenu() {
        return menuRepository.findAll().stream()
            .map(MenuDTO::new)
            .collect(Collectors.toList());
    }

    //특정 카테고리의 모든 메뉴 조회
    public List<MenuDTO> getAllMenuByCategory(Category category) {
        return menuRepository.findByCategory(category).stream()
            .map(MenuDTO::new)
            .collect(Collectors.toList());
    }

    //인기 메뉴 TOP3 조회
    public List<MenuDTO> getTop3Menu() {
        return menuRepository.findTop3ByOrderBySalesCountDescPriceDesc().stream()
            .map(MenuDTO::new)
            .collect(Collectors.toList());
    }

    //메뉴 등록
    @Transactional
    public MenuDTO createMenu(MenuRequest request) {
        Menu menu = Menu.builder()
            .name(request.getName())
            .category(request.getCategory())
            .price(request.getPrice())
            .info(request.getInfo())
            .build();
        menuRepository.save(menu);

        return new MenuDTO(menu);
    }

    //메뉴 수정
    @Transactional
    public MenuDTO updateMenu(Long id, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));
        menu.updateMenu(menuRequest);

        return new MenuDTO(menu);
    }

    //메뉴 삭제
    @Transactional
    public void deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));
        menuRepository.delete(menu);
    }
}
