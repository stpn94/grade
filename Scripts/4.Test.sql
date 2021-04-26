select user(),database();

select * from ban;
select * from subject s ;
select * from student s ;
select * from score s ;


-- view 생성
create view vw_student_table as
select s.stdNo as stdNo, stdName,banNo, banCode,
									  max(if(sco.subject = 4, sub.subjNo , null))as 'subj4',max(if(sco.subject = 4, sub.subjName, null))as 'subj국어',max(if (sco.subject = 4, sco.jumsu, 0))as '국어',
                                      max(if(sco.subject = 5, sub.subjNo , null))as 'subj5',max(if(sco.subject = 5, sub.subjName, null))as 'subj영어',max(if (sco.subject = 5, sco.jumsu, 0))as '영어',
                                      max(if(sco.subject = 6, sub.subjNo , null))as 'subj6',max(if(sco.subject = 6, sub.subjName, null))as 'subj수학',max(if (sco.subject = 6, sco.jumsu, 0))as '수학',
                                      max(if(sco.subject = 7, sub.subjNo , null))as 'subj7',max(if(sco.subject = 7, sub.subjName, null))as 'subj사회',max(if (sco.subject = 7, sco.jumsu, 0))as '사회',
                                      max(if(sco.subject = 8, sub.subjNo , null))as 'subj8',max(if(sco.subject = 8, sub.subjName, null))as 'subj과학',max(if (sco.subject = 8, sco.jumsu, 0))as '과학',
                                      sum(jumsu)as'합계',round(avg(jumsu),1)as'평균'
	FROM student s
		  JOIN ban b ON s.ban = b.banNo
		  JOIN score sco ON s.stdNo = sco.stdNo
		  JOIN subject sub ON sco.subject = sub.subjNo
	group by s.stdNo;

select * from vw_student_table;
drop view vw_student_table;


drop trigger insert_student;
-- 학생이 추가 되면
delimiter $$
create trigger insert_student
	after insert on student
	for each row
begin 
		insert into score 
			values
			(new.stdNo, 4, 0),
			(new.stdNo, 5, 0),
			(new.stdNo, 6, 0),
			(new.stdNo, 7, 0),
			(new.stdNo, 8, 0);
end $$
delimiter ;
-- 학생이 삭제되면
drop trigger test_student;


delimiter $$
create trigger delete_student
before delete on student
for each row
begin
delete from score
where stdno = old.stdno;
end $$
delimiter ;

CREATE TABLE newoldtable
	(newstd varchar(20) not null,
	oldstd varchar(20) not null);

drop table newoldtable;

select stdNo,stdName,ban from student;
select banNo, banCode from ban ;
select stdNo,subject,jumsu from score;

where p.stdNo = 2031;
select * from subject ;

select *
from score
where stdno=2031;

-- 전체 학생 정보와 과목별 점수를 Sql문
-- 학번 이름 분반 국어 영어 수학 사회 과학 테이블

select * from point;

-- 전체 학생 정보

select s.stdNo, s.stdName, b.banCode, max(if (sco.subject = 4, sco.jumsu, 0))as '국어',
                                      max(if (sco.subject = 5, sco.jumsu, 0))as '영어',
                                      max(if (sco.subject = 6, sco.jumsu, 0))as '수학',
                                      max(if (sco.subject = 7, sco.jumsu, 0))as '사회',
                                      max(if (sco.subject = 8, sco.jumsu, 0))as '과학'
	FROM student s
		  JOIN ban b ON s.ban = b.banNo
		  JOIN score sco ON s.stdNo = sco.stdNo
		  JOIN subject sub ON sco.subject = sub.subjNo
	group by s.stdNo;

-- -----------------------------------------
select stdNo, stdName, ban from student;

select stdNo,pic,gender,birthday from newoldtable;
-- 학생 추가

INSERT INTO student VALUES (0001,'아이다',1);

-- 학생 수정

update student set stdName = '아니니' , ban= 2 where stdNo=2099;
-- 학생 삭제

delete from student where stdNo=2031;

select * from std_detail;


insert into std_detail
values
(2031,null ,1,19940817);

delete
from student
where stdNo=;
-- 학생 번호로 학생 정보 검색
select stdNo,pic,gender,birthday from std_detail where stdNo=2001;
-- 학생 번호로 학생 정보 삭제
delete from std_detail where stdNo =2002;
-- 학생 정보 입력
insert into std_detail values (?,?,?,?);
-- 학생 번호로 학생 정보 수정
update std_detail set pic=null,gender =2,birthday =19940817 where stdNo=2002;

INSERT INTO erp.emp_detail (empno, pic, gender, hiredate, password) VALUES(?, ?, ?, ?, password(?))

-- 성적입력이 안된 학생 목록
select stdNo,stdName,banCode FROM vw_student_table where 국어+영어+수학+사회+과학=0;

select stdNo, stdName, banCode, subj4, subj국어, 국어, subj5, subj영어, 영어, subj6, subj수학, 수학, subj7, subj사회, 사회, subj8, subj과학, 과학, 평균 from vw_student_table where 국어+영어+수학+사회+과학=0;
	     
-- -------------------------------------------------------

-- 성적 입력
update score set jumsu = 90 where stdno = 2033 and subject = 4;
update score set jumsu = 70 where stdno = 2031 and subject = 5;
update score set jumsu = 80 where stdno = 2031 and subject = 6;
update score set jumsu = 97 where stdno = 2031 and subject = 7;
update score set jumsu = 55 where stdno = 2031 and subject = 8;
select * from score;

delete from point where stdNo = 2035 and subject =4;
-- -------------------------------------------------------

-- 모든 과목 검색
select subjNo, subjName from subject ;

-- 과목 추가
insert into subject values (9,'역사');

-- 과목 삭제
delete from subject where subjName = '역사';
-- -------------------------------------------------------

-- 모든 반 검색
select banNo,banCode from ban ;
-- 분반 추가

insert into ban
values (3,'A03');

-- 분반 삭제

DELETE from ban where banCode = 'A03';
-- -------------------------------------------------------

-- 분반별 성적을 선택한 과목으로 내림차순 정렬
/*select s.stdNo, s.stdName, b.banCode, max(if (p.subject = 4,p.jumsu,0))as '국어',
                                      max(if (p.subject = 5,p.jumsu,0))as '영어',
                                      max(if (p.subject = 6,p.jumsu,0))as '수학',
                                      max(if (p.subject = 7,p.jumsu,0))as '사회',
                                      max(if (p.subject = 8,p.jumsu,0))as '과학'
	FROM student s 
		  JOIN ban b ON s.ban = b.banNo
		  JOIN point p ON s.stdNo = p.stdNo
		  JOIN Subject sub ON p.subject = sub.subjNo 
	where b.banCode = 'A01' 
	group by s.stdNo
	order by 4 desc limit 32;*/

-- 학생별 성적 검색
select s.stdNo, s.stdName, b.banCode, 국어, 영어, 수학, 사회, 과학,((국어+영어+수학+사회+과학)/5)as 평균
from student s
		join vw_student_table vw on s.stdNo =vw.stdno
		join ban b on s.ban = b.banNo
group by s.stdNo;

-- 분반별 성적을 선택한 과목으로 내림차순 정렬

select s.stdNo, s.stdName, b.banCode, 국어, 영어, 수학, 사회, 과학,((국어+영어+수학+사회+과학)/5)as 평균
from student s
		join vw_student_table vw on s.stdNo =vw.stdno
		join ban b on s.ban = b.banNo
where b.banCode = 'A01' 
group by s.stdNo
order by 국어 desc limit 5;  -- desc(오름차순)  기본(내림차순)

-- 이름으로 학생의 정보와 과목별 점수를 검색 Sql

select s.stdNo, s.stdName, b.banCode, 국어, 영어, 수학, 사회, 과학,((국어+영어+수학+사회+과학)/5)as 평균
from student s
		join vw_student_table vw on s.stdNo =vw.stdno
		join ban b on s.ban = b.banNo
where s.stdName = '박재선';

-- 
CREATE VIEW vw_employee_dno3 (ENO, ENAME, TITLE, DNO) AS
SELECT EMPNO, EMPNAME, TITLE, DNO
FROM EMPLOYEE
WHERE DNO=3;
-- 


select s.stdNo, s.stdName, b.banCode, 국어, 영어, 수학, 사회, 과학,((국어+영어+수학+사회+과학)/5)as 평균
from student s
		join vw_student_table vw on s.stdNo =vw.stdno
		join ban b on s.ban = b.banNo;
-- 학생리스트 출력

select stdNo, stdName, banCode,
	   국어, 영어, 수학, 사회, 과학, 평균
from vw_student_table
where banCode = ifnull(null,'A01'&'A02')
order by ifnull(국어,평균) desc limit 10;
select stdNo, stdName, banCode, 국어, 영어, 수학, 사회, 과학, 평균
from vw_student_table where banCode = ifnull('A01' ,'A01' & 'A02')
order by ifnull(4 , 평균) desc limit 5;

select stdNo, stdName, banCode, 국어, 영어, 수학, 사회, 과학, 평균
from vw_student_table where banCode = ifnull(null ,'A01' & 'A02')
order by if(평균=평균,평균,국어) desc limit 5;

select stdNo, stdName, banCode, 국어, 영어, 수학, 사회, 과학, 평균
from vw_student_table
where banCode = if('전체'='전체','A02 & A01','A01')
order by if('평균'='평균',평균, 국어) desc limit 5;

if(,)
-- 학생이름별 학생리스트 출력
select stdNo, stdName, banCode,
	   subj4, 국어, subj5, 영어, subj6, 수학, subj7, 사회, subj8, 과학,
	   평균
from vw_student_table;
where stdName = '황보동명';


-- 
select jumsu as '국어' from point where subject = 1;
select jumsu as '영어' from point where subject = 2;
select jumsu as '수학' from point where subject = 3;
select jumsu as '사회' from point where subject = 4;
select jumsu as '과학' from point where subject = 5;

select * from score ;
select all from score ;



-- Test
select s.stdNo, s.stdName, b.banCode, if (p.subject = 4,p.jumsu,0)as '국어',
							          if (p.subject = 5,p.jumsu,0)as '영어',
							          if (p.subject = 6,p.jumsu,0)as '수학',
							          if (p.subject = 7,p.jumsu,0)as '사회',
							          if (p.subject = 8,p.jumsu,0)as '과학'
	FROM student s
		  JOIN ban b ON s.ban = b.banNo
		  JOIN point p ON s.stdNo = p.stdNo
		  JOIN Subject sub ON p.subject = sub.subjNo;
 	group by s.stdNo;
 -- 
