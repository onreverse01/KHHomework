--==========================================
-- CHUN 계정
--==========================================

-- 학과테이블
select * from tb_department;

--학생테이블
select * from tb_student;

--과목테이블
select * from tb_class;

--교수테이블
select * from tb_professor;

--교수과목테이블
select * from tb_class_professor;

--점수등급테이블
select * from tb_grade;

--1번
select department_name "학과 명",
           category 계열
from tb_department;

--2번
select to_char(department_name)||'의 정원은 '||
           to_char(capacity)|| '입니다.' "학과별 정원"
from tb_department;

--3번
select student_name
from tb_student
where absence_yn = 'Y' and department_no = '001'and substr(student_ssn, 8,1) = '2';

--4번
select student_name
from tb_student
where student_no in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119')
order by student_name desc;

--5번
select department_name,
           category
from tb_department
where capacity between 20 and 30;

--6번
select professor_name
from tb_professor
where department_no is null;

--7번
select student_name
from tb_student
where coach_professor_no is null;

--8번
select class_no
from tb_class
where preattending_class_no is not null;

--9번
select distinct category
from tb_department
where OPEN_YN = 'Y'
order by category asc;

--10번
select student_no,
           student_name,
           student_ssn
from tb_student
where substr(entrance_date, 1,2)='02' and
substr(student_address, 1,2)='전주' and
absence_yn = 'N';