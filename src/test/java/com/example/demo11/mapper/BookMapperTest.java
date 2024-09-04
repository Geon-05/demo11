package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;

@SpringBootTest
public class BookMapperTest {
  @Autowired
  BookMapper mapper;

  @Test
  void testSelectBookList() {
    SearchDto dto = new SearchDto();
    // 페이징과 검색처리를 위해 SearchDto를 매개변수로 넣어준다.
    dto.setSearchField("title");
    dto.setSearchWord("오늘도 맑음");
    List<BookDto> list = mapper.selectBookList(dto);
    assertEquals(1, list.size());
  }
  


  @Test
  void testSelectBook() {
    BookDto book = mapper.selectBook("B00002");
    assertNotNull(book);
  }



  @Test
  void testInsertBook() {
    BookDto book = new BookDto();
    book.setTitle("매퍼테스트중");
    book.setAuthor("작가님테스트중");
    book.setPrice(123456);
    book.setImg_f_no(123);
    
    int insertBook = mapper.insertBook(book);

    assertEquals(1, insertBook);
  }
}
