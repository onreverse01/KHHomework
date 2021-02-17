--1번
select student_name "학생 이름",
           student_address 주소지
from tb_student
order by 1;

--2번
select student_name,
           student_ssn
from tb_student
where absence_yn='Y'
order by 2 desc;

--3번
select student_name 학생이름,
           student_no 학번,
           student_address "거주지 주소"
from tb_student
where extract(year from entrance_date)<2000
and student_address like '%경기도%'
or student_address like '%강원도%'
order by 1;

--4번
select professor_name,
           professor_ssn
from tb_professor P
where department_no=005
order by 2;

--subquery
select professor_name,
           professor_ssn
from tb_professor P
where (
select department_name
from tb_department
where department_no=p.department_no
)='법학과'
order by 2;

--5번
select student_no,
           point
from tb_grade
where class_no = 'C3118100'
and term_no = '200402'
order by point desc;

--6번
select student_no,
student_name,
department_name
from tb_student S
    join tb_department D
     using(department_no)
order by 2;

--7번
select class_name,
           department_name
from tb_class C
    join tb_department
        using(department_no);

--8번
select class_name,
           professor_name
from tb_class_professor CP
    join tb_professor P
        using(professor_no)
    join tb_class C
        using(class_no);

--9번
select class_name,
           professor_name
from tb_class_professor CP
    join tb_professor P
        using(professor_no)
    join tb_class C
        using(class_no)
    join tb_department D
        on d.department_no = c.department_no
where d.category = '인문사회';

--10번
select student_no 학번,
           student_name "학생 이름",
           round(avg(point), 1) "전체 평점"
from tb_student S
    join tb_grade G
        using(student_no)
    join tb_department D
        using(department_no)
where department_name='음악학과'
group by student_no, student_name
order by 1;

--11번
--join
select d.department_name 학과이름,
           student_name 학생이름,
           p.professor_name 지도교수이름
from tb_student S
    join tb_department D
        using(department_no)
    join tb_professor P
        on s.coach_professor_no=p.professor_no
where absence_yn='N'
and student_no='A313047';

--subquery

select (
           select department_name
           from tb_department
           where department_no=s.department_no
           )학과이름,
           student_name 학생이름,
           (
           select professor_name
           from tb_professor
           where professor_no = s.coach_professor_no
           ) 지도교수이름
from tb_student S
where absence_yn='N'
and student_no='A313047';

--12번
select student_name,
            term_no term_name
from tb_grade G
    join tb_student S
        using(student_no)
    join tb_class C
        using(class_no)
where class_name='인간관계론'
and term_no like '2007%';

--13번
select c.class_name,
           d.department_name
from tb_class C
    left join tb_class_professor CP
        on cp.class_no=c.class_no
    join tb_department D
        on c.department_no=d.department_no
where cp.professor_no is null
and d.category = '예체능';

--14번
select s.student_name 학생이름,
           nvl(p.professor_name,'지도교수 미지정') 지도교수
from tb_student S
    left join tb_professor P
        on s.coach_professor_no=p.professor_no
    join tb_department D
        on s.department_no=d.department_no
where d.department_name='서반아어학과';

--15번
select * from tb_grade;
select * from tb_student;
select * from tb_department;

select g.student_no,
           s.student_name,
           d.department_name,
           avg(point)
from tb_grade G
    join tb_student S
        on g.student_no=s.student_no
    join tb_department D
        on s.department_no=d.department_no
where (
            select absence_yn
            from tb_student
            where g.student_no=student_no
)='N'
group by g.student_no, s.student_name, d.department_name
having avg(point)>=4;

--16번
select class_no,
           class_name,
           avg(point)
from tb_grade G
    join tb_class C
        using(class_no)
where (
            select department_name
            from tb_department
            where c.department_no=department_no
)='환경조경학과'
and class_type like '전공%'
group by class_no, class_name;

--17번
select student_name,
           student_address
from tb_student
where department_no = (
            select department_no
            from tb_student
            where student_name='최경희'
);

--18번
select E.*           
from(
    select student_no,
               S.student_name
    from tb_grade G
        join tb_student S
            using(student_no)
    where (select department_name
                from tb_department
                where s.department_no=department_no)='국어국문학과'
    group by student_no,s.student_name
    order by avg(point) desc
) E
where rownum between 1 and 1;

--19번
select d.department_name 계열학과명,
           round(avg(g.point),1) 전공평점
from tb_class C
    right join tb_department D
        on d.department_no=c.department_no
    join tb_grade G
        on g.class_no=c.class_no
where d.category= (
        select category
        from tb_department
        where department_name='환경조경학과'
)
group by d.department_name;

