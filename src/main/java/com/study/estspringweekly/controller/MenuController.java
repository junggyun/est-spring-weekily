package com.study.estspringweekly.controller;

import com.study.estspringweekly.domain.menu.Category;
import com.study.estspringweekly.domain.menu.MenuDTO;
import com.study.estspringweekly.domain.menu.MenuRequest;
import com.study.estspringweekly.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    //메뉴 전체 조회
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenu() {
        return ResponseEntity.ok(menuService.getAllMenu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenu(
        @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(menuService.getMenu(id));
    }

    //메뉴 등록
    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(
        @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.ok(menuService.createMenu(menuRequest));
    }

    //메뉴 수정
    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(
        @PathVariable("id") Long id,
        @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.ok(menuService.updateMenu(id, menuRequest));
    }

    //메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenu(
        @PathVariable("id") Long id
    ) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok("DELETE SUCCESS!");
    }

    //특정 카테고리의 모든 메뉴 조회
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenuByCategory(
        @RequestParam("category") Category category
    ) {
        return ResponseEntity.ok(menuService.getAllMenuByCategory(category));
    }
}
