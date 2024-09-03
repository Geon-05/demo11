CREATE TABLE TB_UPLOAD_FILE(
F_NO        NUMBER
, IDX       NUMBER
, ONAME     VARCHAR2(500)
, SNAME     VARCHAR2(500)
, PATH      VARCHAR2(100)
, FILE_TYPE VARCHAR2(100)
, REGDATE   DATE            DEFAULT SYSDATE
, PRIMARY KEY(F_NO, IDX)
);

DROP TABLE TB_UPLOAD_FILE;

-- 시퀀스 생성
CREATE SEQUENCE SEQ_UPLOAD_FILE;
-- 업로드 파일테이블 데이터 입력

INSERT INTO TB_UPLOAD_FILE (F_NO, IDX, ONAME, SNAME, PATH, FILE_TYPE)
VALUES(seq_upload_file.nextval,1,'oname','sname','d:/upload','img');


-- SNAME : 중복을 방지하기 위해서 중복되지 않는 값을 생성해서 추가 - 파일명이 길어짐
-- F_NO, INDEX : 기본키
-- 여러개의 파일을 저장하기 위해서 IDX 컬럼을 이용


-- tb_book에 컬럼추가(f_no)
alter table tb_book add f_no number;