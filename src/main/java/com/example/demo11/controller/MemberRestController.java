package com.example.demo11.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo11.service.MemberService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * JSON형식의 문자열을 전달 하는 방식
 * 반환하는 객체를 JSON형식의 문자열로 변환 하여 클라이언트에게 전달
 */
@RestController
public class MemberRestController {
  @Autowired
  MemberService service;

  /**
   * PATH를 이용하여 파라메터를 전달하는 방식을 이용할 수 있다.
   */
  @GetMapping("/checkId/{id}")
  public Map<String, Object> getMethodName(@PathVariable(name = "id") String id) {
    Map<String, Object> map = new HashMap<>();

    int res = service.selectCheckId(id);

    map.put("restest", res);
    // map.put("res","1");
    // MemberDto member = new MemberDto();
    // member.setId(id);
    // map.put("member",member);

    // Map형식으로 반환
    return map;
  }

}
