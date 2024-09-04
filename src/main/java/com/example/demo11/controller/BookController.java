package com.example.demo11.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo11.dto.BookDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.dto.UploadDto;
import com.example.demo11.service.BookService;
import com.example.demo11.service.UploadService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@Slf4j
public class BookController {
  @Autowired
  BookService service;

  @Autowired
  UploadService uploadService;

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
  public String getMethodName(BookDto book
  , Model model) {
    book = service.selectBook(book.getBook_no());
    model.addAttribute("book", book);
    // 도서에 포함된 파일목록을 조회하고 저장
    List<UploadDto> imgFileList = uploadService.selectUploadList(book.getImg_f_no());
    model.addAttribute("imgFileList", imgFileList);
      return "/book/detail";
  }
  
  // 등록페이지로 이동
  @GetMapping("/book/write")
  public String getMethodName() {
      return "/book/write";
  }


  /**
   * 파일을 선택하지 않은경우, 파일객체는 null
   * required = false 가 설정 되지 않으면 오류 발생
   */
  // DB 등록
  @PostMapping("/book/writeAction")
  public String postMethodName(
    BookDto book
    , @RequestPart(name = "file", required = false) List<MultipartFile> files
    , Model model) {
      System.out.println("=================== 전달된 파라메터 정보");
      log.info(book.toString());
      // 파일을 선택 하지 않으면 null이 반환되어져서
      // 로그로 출력시 오류가 발생
      System.out.println("==============="+files);
      System.out.println(files.get(0).getSize());


      if (files.get(0).getSize() != 0 && files.size() > 0) {
        System.out.println(files.size()+"========================");
        // 파일을 저장하고 시퀀스값을 반환 받아온다!
        // path : 서비스별로 첨부파일을 관리하기 위해 폴더를 구분!
        System.out.println("문제발생지점=======================================================");
        int f_no = uploadService.insertUpload(files, "book");
        book.setImg_f_no(f_no);
      }

      int res = service.insertBook(book);

      if (res>0) {
        return "redirect:/book/list";
      } else {
        model.addAttribute("msg", "도서등록 중 문제가 발생하였습니다.\n관리자에게 문의하세요.");
        return "/common/msg";
      }

      // return "redirect:/book/list";
  }
  
  
}
