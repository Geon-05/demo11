package com.example.demo11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo11.dto.MemberDto;
import com.example.demo11.dto.SearchDto;

@Mapper
public interface MemberMapper {
  public List<MemberDto> selectMemberList(SearchDto searchDto);

  public int selectTotalCnt(SearchDto searchDto);

  public MemberDto login(MemberDto member);

  public int insertMember(MemberDto member);

  public int selectCheckId(String id);
}
