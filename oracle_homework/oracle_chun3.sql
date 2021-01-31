--1번
select student_no 학번,
            student_name 이름,
            to_char(to_date(entrance_date, 'rr-mm-dd'), 'yyyy-mm-dd') 입학년도
from tb_student
where department_no = '002'
order by 2;

--2번
select professor_name,
           professor_ssn
from tb_professor
where professor_name not like '___';

--3번
select professor_name 교수이름,
           trunc((sysdate-(to_date(1900+substr(professor_ssn, 1,2)||substr(professor_ssn,3,4))))/365) 나이
from tb_professor
where substr(professor_ssn, 8,1)='1'
order by 나이 asc;

--4번
select substr(professor_name, 2,2) 이름
from tb_professor
where professor_name not like '____';

--5번
select student_no,
            student_name
from tb_student
where extract(year from entrance_date)-(1900+(substr(student_ssn, 1,2))) >19;

--6번
select to_char(to_date('2020/12/25'), 'day') "2020 크리스마스"
from dual;
--금요일

--7번
select to_char(to_date('99/10/11','YY/MM/DD'),'YYYY/MM/DD') "TO_DATE('99/10/11','YY/MM/DD')",
            to_char(to_date('49/10/11','YY/MM/DD'),'YYYY/MM/DD')"TO_DATE('49/10/11','YY/MM/DD')",
            to_char(to_date('99/10/11','RR/MM/DD'),'YYYY/MM/DD')"TO_DATE('99/10/11','RR/MM/DD')",
            to_char(to_date('49/10/11','RR/MM/DD'),'YYYY/MM/DD')"TO_DATE('49/10/11','RR/MM/DD')"
from dual;
--2099/10/11, 2049/10/11, 1999/10/11, 2049/10/11

--8번
select student_no,
           student_name
from tb_student
where extract(year from entrance_date) < 2000;

--9번
select round(avg(point),1) 평점
from tb_grade
where student_no = 'A517178';

--10번
select department_no 학과번호,
           count(department_no) "학생수(명)"
from tb_student
group by department_no
order by 학과번호;

--11번
select count(*)
from tb_student
where coach_professor_no is null;

--12번
select substr(term_no,1,4) 년도,
            round(avg(point),1) "년도 별 평점"
from tb_grade
where student_no = 'A112113'
group by substr(term_no,1,4)
order by "년도";

--13번
select department_no 학과코드명,
           sum(decode(absence_yn,'Y', 1, 0)) "휴학생 수"
from tb_student
group by department_no
order by 학과코드명;

--14번
select student_name 동일이름,
           count(student_name) "동명인 수"
from tb_student
group by student_name
having count(student_name) >1 
order by 1;

--15번
select substr(term_no,1,4) 년도,
           substr(term_no,5,2) 학기,
            round(avg(point),1) 평점
from tb_grade
where student_no = 'A112113'
group by rollup(substr(term_no,1,4), substr(term_no,5,2))
order by 1;