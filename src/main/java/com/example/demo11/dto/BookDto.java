package com.example.demo11.dto;

import lombok.Data;

@Data
public class BookDto {
  private String book_no;
  private String title;
  private String author;
  private int price;
  private String pub_no;
  private String rentyn;
  private String delyn;
  private String regdate;
}
