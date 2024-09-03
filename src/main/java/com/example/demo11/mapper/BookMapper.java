package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;

@Mapper
public interface BookMapper {

  public List<BookDto> selectBookList(SearchDto searchDto);

  // 검색 처리를 위해 파라메터를 추가
  public int selectTotalCnt(SearchDto searchDto);

  public BookDto selectBook(String book_no);
}