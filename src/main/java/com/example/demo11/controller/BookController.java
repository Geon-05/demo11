package com.example.demo11.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.service.BookService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class BookController {
  @Autowired
  BookService service;

  @GetMapping("/book/list")
  public void list(SearchDto searchDto,Model model) {
    // 파라메터 수집이 잘 되는지 확인!
    log.info(searchDto.toString());
    Map<String, Object> map = service.selectBookList(searchDto);
    model.addAttribute("map", map);

    // List<BookDto> list =service.selectBookList(searchDto);
    // model.addAttribute("list", list);
    // model.addAttribute("pageDto", new PageDto(searchDto, 100));

  }

  @GetMapping("/book/detail")
  public String getMethodName(@RequestParam(name = "book_no", required = false) String no
  , Model model) {
    BookDto book = service.selectBook(no);
    model.addAttribute("book", book);
      return "/book/detail";
  }
  
  
}
