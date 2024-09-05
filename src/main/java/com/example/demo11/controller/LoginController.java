package com.example.demo11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {
  @Autowired
  MemberService service;

  @GetMapping("/newLogin")
  public String getMethodName() {
    return "/login/newLogin";
  }

  // 로그인 페이지
  // 요청 데이터 수집
  @PostMapping("/newLoginAction")
  public String loginAct(Model model
  , @RequestParam(name = "chkIdSave", required = false) String chkIdSave
  , HttpServletRequest request
  , HttpServletResponse response
  , MemberDto member
  , HttpSession session) {

    // 수집된 데이터를 출력 : 수집될 데이터를 확인하기 위해서 출력
    log.info("id : " + member.getId());
    log.info("pw : " + member.getPw());

    Cookie cookie = new Cookie("saveId", member.getId());
    cookie.setMaxAge(0);

    // 체크박스가 선택 되어 있다면 아이디를 쿠키에 저장
    if ("1".equals(chkIdSave)) {
      cookie.setMaxAge(60 * 60 * 24 * 7);

    }

    response.addCookie(cookie);

    MemberDto loginMember = service.login(member);
    if (loginMember != null) {
      // id/pw에 해당하는 사용자가 있다면 로그인처리
      // 서버에 요청(redirect, forward - 요청방식이 맞지 않으면 오류)
      // 로그인 유지를 위해 세션에 값을 저장
      session.setAttribute("loginId", loginMember.getId());
      session.setAttribute("loginName", loginMember.getName());

      session.getAttribute("loginId");

      return "redirect:/";
    } else {
      // 없으면 로그인 실패

      model.addAttribute("msg", "아이디 비밀번호를 확인해주세요.");
      return "/login/newLogin";
    }
  }

  @GetMapping("/newLogout")
  public String getMethodName(HttpSession session) {
    session.invalidate();
    return "redirect:/login/newLogin";
  }

}
