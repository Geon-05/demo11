package com.example.demo11.dto;

import lombok.Data;


/**
 * 페이지처리를 위한 정보
 * 검색어, 검색필드 정보
 */
@Data
public class SearchDto {
  // 요청 페이지 번호
  private int pageNo = 1;
  // 한 페이지에 보여질 게시물의 수
  private int amount = 5; //한 페이지에 게시물을 5건씩 조회 해서 화면에 출력

  // 검색 필드
  // 빈문자열을 넣어놓으면 null 에러를 방지할 수있다.
  private String searchField = "";
  // 검색어
  private String searchWord = "";
}
