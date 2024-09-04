package com.example.demo11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.mapper.BookMapper;

@Service
public class BookService {
  @Autowired
  BookMapper mapper;

  public Map<String, Object> selectBookList(SearchDto searchDto) {
    // Map을사용하는 이유는 여러가지 타입을 저장할 수있기때문
    // 여기서는 리스트, 페이지Dto를 전달 함!
    Map<String, Object> map = new HashMap<>();

    // 리스트 조회 & map에 담기
    List<BookDto> list = mapper.selectBookList(searchDto);
    map.put("list", list);

    int totalCnt = mapper.selectTotalCnt(searchDto);

    // 페이지 DTo & map에 담기
    PageDto pageDto = new PageDto(searchDto, totalCnt);
    map.put("pageDto", pageDto);

    return map;

  }

  public BookDto selectBook(String book_no) {
    return mapper.selectBook(book_no);
  }

  public int insertBook(BookDto book) {
    return mapper.insertBook(book);
  }
}
