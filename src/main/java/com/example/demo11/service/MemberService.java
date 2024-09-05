package com.example.demo11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.PageDto;
import com.example.demo11.dto.SearchDto;
import com.example.demo11.mapper.MemberMapper;

@Service
public class MemberService {
  @Autowired
  MemberMapper mapper;

  @Autowired
  BCryptPasswordEncoder encoder;

  public MemberDto login(MemberDto member) {
    MemberDto loginMember = mapper.login(member);
    // 사용자의 비밀번호 확인
    if (encoder.matches(member.getPw(), loginMember.getPw())) {
      return loginMember;
    } else {
      return null;
    }
    // return mapper.login(member);
  }

  public int insertMember(MemberDto member) {
    // 비밀번호를 암호화 하여 저장 할 수 있도록 암호화 로직을 추가
    String encodePw = encoder.encode(member.getPw());
    member.setPw(encodePw);
    return mapper.insertMember(member);
  }


  public int selectCheckId(String id) {
    return mapper.selectCheckId(id);
  }


  public Map<String, Object> selectMemberList(SearchDto searchDto){
    // 페이지블럭을 생성 - pageDto
    // searchDto : pageNo(요청 페이지번호), amount(페이지당 보여줄 게시물수)
    // 사용자가 값을 전달 하지 않으면 필드의 초기값으로 초기화가 됨
    // totalCnt(총건수)
    Map<String, Object> map = new HashMap<>();

    List<MemberDto> list = mapper.selectMemberList(searchDto);
    map.put("list", list);

    // 검색조건을 추가
    int totalCnt = mapper.selectTotalCnt(searchDto);

    PageDto pageDto = new PageDto(searchDto, totalCnt);
    map.put("pageDto", pageDto);

    return map;
  }
}
