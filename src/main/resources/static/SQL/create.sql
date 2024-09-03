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

-- ������ ����
CREATE SEQUENCE SEQ_UPLOAD_FILE;
-- ���ε� �������̺� ������ �Է�

INSERT INTO TB_UPLOAD_FILE (F_NO, IDX, ONAME, SNAME, PATH, FILE_TYPE)
VALUES(seq_upload_file.nextval,1,'oname','sname','d:/upload','img');


-- SNAME : �ߺ��� �����ϱ� ���ؼ� �ߺ����� �ʴ� ���� �����ؼ� �߰� - ���ϸ��� �����
-- F_NO, INDEX : �⺻Ű
-- �������� ������ �����ϱ� ���ؼ� IDX �÷��� �̿�


-- tb_book�� �÷��߰�(f_no)
alter table tb_book add f_no number;