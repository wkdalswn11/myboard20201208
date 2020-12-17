select * from member;

delete from member
where memberId = '삭제';

drop table article;
CREATE TABLE article (
    article_no Number GENERATED AS IDENTITY,
    writer_id VARCHAR2(50) NOT NULL,
    writer_name VARCHAR2(50) NOT NULL,
    title VARCHAR2(255) NOT NULL,
    regdate DATE NOT NULL,
    moddate DATE NOT NULL,
    read_cnt NUMBER,
    PRIMARY KEY (article_no)
);
SELECT * FROM article;

CREATE TABLE article_content (
    article_no NUMBER PRIMARY KEY,
    content VARCHAR2(4000)
);

SELECT * FROM member;

select * from article_content;

delete from article_content
where article_no = 18;

select count(*) from article;

-- page 처리
SELECT * FROM article_content;
INSERT INTO article (writer_id, writer_name,title, regdate, moddate, read_cnt)
VALUES ('오라클', '1234', 'a', sysdate, sysdate, 0);

commit;

SELECT * FROM (
SELECT  article_no, title, ROW_NUMBER() OVER (ORDER BY article_no DESC) rn   
FROM article
--ORDER BY article_no DESC
)
WHERE rn BETWEEN 1 AND 5;


 SELECT rn, article_no, writer_id, writer_name, 
title, regdate, moddate, read_cnt 
FROM (
SELECT article_no, 
writer_id, writer_name, title, regdate, 
moddate, read_cnt,ROW_NUMBER() 
OVER (
ORDER BY article_no DESC)
rn 
FROM article ) WHERE rn 
BETWEEN 6 AND 10;

SELECT *
FROM article
ORDER BY article_no 
OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY;

-- 댓글
-- 11g
CREATE SEQUENCE reply_seq;
CREATE TABLE reply (
    replyid NUMBER,
    memberid VARCHAR2(50) NOT NULL,
    article_no NUMBER NOT NULL,
    body VARCHAR2(1000) NOT NULL,
    regdate DATE NOT NULL,
    PRIMARY KEY(replyid)
);
INSERT INTO reply (replyid, memberid, article_no, body, regdate)
VALUES (reply_seq.nextval, ' ', 0, ' ', SYSDATE);

-- 12 c
DROP TABLE reply;
CREATE TABLE reply (
    replyid NUMBER GENERATED AS IDENTITY,
    memberid VARCHAR2(50) NOT NULL,
    article_no NUMBER NOT NULL,
    body VARCHAR2(1000) NOT NULL,
    regdate DATE NOT NULL,
    PRIMARY KEY(replyid)
);
INSERT INTO reply (memberid, article_no, body, regdate)
VALUES (' ', 0, ' ', SYSDATE);

DESC reply;








