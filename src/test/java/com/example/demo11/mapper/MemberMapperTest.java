package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;

@SpringBootTest
public class MemberMapperTest {
  @Autowired
  MemberMapper mapper;

  @Test
  void testSelectMemberList() {
    SearchDto searchDto = new SearchDto();
    List<MemberDto> list = mapper.selectMemberList(searchDto);
    assertEquals(5, list.size());
  }

  @Test
  void testSelectTotalCnt() {
    SearchDto searchDto = new SearchDto();
    int res = mapper.selectTotalCnt(searchDto);
    assertEquals(13, res);
  }

  // @Test
  // void testSelectMemberList() {
  //   List<MemberDto> list = mapper.selectMemberList();
  //   assertEquals(13, list.size());
  // }
}
