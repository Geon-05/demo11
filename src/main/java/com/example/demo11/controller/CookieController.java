package com.example.demo11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
@Slf4j
public class CookieController {
    @GetMapping("/cookie")
    public String getMethodName(HttpServletRequest request, HttpServletResponse response) {
        // // 쿠키생성
        // // 쿠기는 key, value로 생성되어짐
        // Cookie cookie = new Cookie("saveId","abcd");
        // cookie.setPath("/");

        // cookie.setMaxAge(60*60);

        // response.addCookie(cookie);
        return "cookie";
    }
 
    // 체크박스는 체크가되어있지않으면 데이터가 넘어가지 않으니 required = false 필요
    // text박스는 자동 null이 넘어가므로 필요없음
    @GetMapping("/loginAction2")
    public String getMethodName(@RequestParam(name = "chkIdSave", required = false) String chkIdSave
                                , @RequestParam(name = "userId") String userId
                                , HttpServletResponse response) {
        System.out.println("chkSaveId : "+chkIdSave);
        log.info("userId : "+userId);
        // 체크박스가 체크 된 경우
        // nullpoint 오류가나지 않게하는 두가지 방법
        // if("1".equals(chkSaveId)){
            Cookie cookie = new Cookie("saveId", userId);
            cookie.setPath("/");
        if(chkIdSave != null && chkIdSave.equals("1")){
            cookie.setMaxAge(60*60);
        } else {
            // 쿠키제거
            // 만료시간을 0으로 만들어줍니다
            
            cookie.setMaxAge(0);
        }
        // 응답객체에 쿠키를 추가
        response.addCookie(cookie);
        return "cookie";
    }
    
}
