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