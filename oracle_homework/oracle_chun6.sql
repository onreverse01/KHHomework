--04_워크북[DDL]

--1번
create table TB_CATEGORY(
    NAME varchar2(10),
    USE_YN char(1) default 'Y'
);

--2번
create table TB_CLASS_TYPE(
    NO varchar2(5) primary key,
    NAME varchar2(10)
);

--3번
alter table tb_category
add constraint pk_name primary key(NAME);

--4번
alter table tb_class_type
modify NAME varchar2(10) not null;

--5번
alter table tb_class_type
modify NO varchar2(10);

alter table tb_class_type
modify NAME varchar2(20);

alter table tb_category
modify NAME varchar2(20);

--6번
alter table tb_category
rename column NAME to CATEGORY_NAME;

alter table tb_class_type
rename column NAME to CLASS_TYPE_NAME;

alter table tb_class_type
rename column NO to CLASS_TYPE_NO;

--7번
alter table tb_category
drop constraint pk_name;
alter table tb_category
add constraint PK_CATEGORY_NAME primary key(CATEGORY_NAME);

--8번
insert into tb_category values ('공학','Y');
insert into tb_category values ('자연과학','Y');
insert into tb_category values ('의학','Y');
insert into tb_category values ('예체능','Y');
insert into tb_category values ('인문사회','Y');
commit;

--9번
alter table TB_DEPARTMENT
add constraint fk_department_category foreign key (CATEGORY)
                                                            references tb_category(category_name);

--10번
/*
system계정으로 권한주기
grant create view to chun;
*/

create view VM_학생일반정보
as
select student_no 학번,
           student_name 학생이름,
           student_address 주소
from tb_student;

--11번
create view VM_지도면담
as
select S.student_name 학생이름,
           d.department_name 학과이름,
           p.professor_name 지도교수이름
from tb_student S, tb_department D, tb_professor P
where s.department_no=d.department_no
and s.coach_professor_no=p.professor_no
order by 2;

--12번
create view VM_학과별학생수
as
select d.department_name DEPARTMENT_NAME,
            count(S.student_name) STUDENT_COUNT
from tb_student S
    join tb_department D
        on d.department_no=s.department_no
group by d.department_name;

--13번
update VM_학생일반정보
set 학생이름='L'
where 학번='A213046';

--14번
create or replace view VM_학생일반정보
as
select student_no 학번,
           student_name 학생이름,
           student_address 주소
from tb_student
with read only;

--15번
create or replace view VM_3Y_Top3
as
select E.*
from(
select class_no 과목번호,
           class_name 과목이름,
           count(student_no) "누적수강생수(명)"
from tb_grade G
    join tb_class C
        using(class_no)
where extract(year from sysdate)-substr(term_no,1,4) <= 3
group by class_no, class_name
order by count(student_no) desc
)E
where rownum between 1 and 3;
-- 최근 16년 기준 똑같이 뜬다. 3년은 하나도 안나온다.

--05_워크북[DML]

--1번
insert into tb_class_type
values ('01','전공필수');
insert into tb_class_type
values ('02','전공선택');
insert into tb_class_type
values ('03','교양필수');
insert into tb_class_type
values ('04','교양선택');
insert into tb_class_type
values ('05','논문지도');
commit;

--2번
create table TB_학생일반정보
as
select student_no 학번,
           student_name 이름,
           student_address 주소
from tb_student;

--3번
create table TB_국어국문학과
as
select student_no 학번,
           student_name 학생이름,
           to_char(to_date(substr(student_ssn,1,2),'rr'), 'yyyy') 출생년도,
           (
            select professor_name
            from tb_professor
            where s.coach_professor_no=professor_no
           ) 교수이름
from tb_student S
where department_no=(select department_no 
                                  from tb_department
                                  where department_name='국어국문학과');

--4번
update tb_department
set capacity = capacity+round(capacity*0.1);

--5번
update tb_student
set STUDENT_ADDRESS = '서울시 종로구 숭인동 181-21'
where student_no='A413042';

--6번
update tb_student
set student_ssn = substr(student_ssn,1,6);

--7번
update tb_grade
set point = '3.5'
where student_no in (select student_no
                                from tb_student
                                where student_name='김명훈'
                                and department_no=(select department_no
                                                                from tb_department
                                                                where department_name='의학과')
                                )
and class_no=(select class_no
                        from tb_class
                        where class_name='피부생리학');

--8번
delete from tb_grade
where student_no in (select student_no 
                            from tb_student
                            where absence_yn='Y');
                            
