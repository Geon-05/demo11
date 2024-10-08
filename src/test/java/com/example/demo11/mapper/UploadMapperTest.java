package com.example.demo11.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo11.dto.UploadDto;

@SpringBootTest
public class UploadMapperTest {
  @Autowired
  UploadMapper mapper;

  @Test
  void testInsertUpload() {
    // 파일의 정보를 Dto에 저장 후 테이블에 입력
    UploadDto data = new UploadDto();
    data.setIdx(1);
    data.setOname("oname");
    data.setSname("sname");
    data.setPath("d:/upload/");
    data.setFile_type("jpg");

    int res = mapper.insertUpload(data);
    assertEquals(1, res);
  }

  @Test
  void uuidTest() {
    // UUID(Universally Unique Identifier)는 전 세계적으로 고유한 식별자
    // 생성하기 위해 설계된 128비트 숫자입니다
    // 8-4-4-4-12 32개의 16진수 숫자로 구성된 값을 반환
    UUID uuid = UUID.randomUUID();
    System.out.println("==============");
    System.out.println(uuid.toString());
    System.out.println(uuid);

    String str = "a.jpg";
    str.indexOf(".");
  }

  public static void main(String[] args) {

    File uploadFile = new File("d:/upload/book");
    // 파일객체.exists() : 해당 경로, 해당경로의 파일이 존재하는지 확인하는 매서드
    if(!uploadFile.exists()){
      // 폴더가 존재하지 않는 경우 폴더를 생성 합니다.
      // 파일객체.mkdir : 폴더생성(1개)
      // 파일객체.mkdirs : 여러개의 폴더생성
      boolean res = uploadFile.mkdirs();
      System.out.println(res);
    }

    System.out.println("===================================");

    // 파일이 저장될때 파일의 이름이 같은 경우, 덮어쓰기가 되어지므로
    // 기존파일이 사라질 수 있다.
    // 파일을 저장시 이름에 unique한 값을 붙여주어 중복되는 현상을 방지한다.
    String oName = "a.jpg";
    // indexOf : 문자열에서 매개변수의 위치를 반환
    // 만약 찾는 문자가 없으면 -1을 반환
    int dotIndex = oName.indexOf(".");
    System.out.println(dotIndex);
    // substring(시작번호, 끝번호) : 시작번호는 포함, 끝번호는 포함되지 않아요!
    System.out.println(oName.substring(0, dotIndex));
    System.out.println(oName.substring(dotIndex));

    String fileName = oName.substring(0, dotIndex);
    String fileExtension = oName.substring(dotIndex);
    UUID uuid = UUID.randomUUID();

    String sName = fileName + "_" + uuid.toString() + fileExtension;

    System.out.println("원본 파일명 : " + oName);
    System.out.println("저장할 파일명 : " + sName);
  }

  @Test
  void testSelectUpload() {
    List<UploadDto> list = mapper.selectUpload(1);
    assertEquals(1, list.size());
  }

  @Test
  void testSelectUploadList() {
    List<UploadDto> list = mapper.selectUploadList();
    assertEquals(21, list.size());
  }
}
