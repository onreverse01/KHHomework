--1. 2020년 12월 25일이 무슨 요일인지 조회하시오.
select to_char(to_date('2020/12/25'), 'day') "2020년 12월 25일"
from dual;

--2. 주민번호가 70년대 생이면서 성별이 여자이고, 성이 전씨인 직원들의 
--사원명, 주민번호, 부서명, 직급명을 조회하시오.
select e.emp_name 사원명,
            e.emp_no 주민번호,
            d.dept_title 부서명,
            j.job_name 직급명
from employee E
    join department D
        on e.dept_code=d.dept_id
    join job J
        on e.job_code=j.job_code
where substr(e.emp_no ,1,2) like '7_'
and substr(e.emp_no, 8,1) in (2,4)
and e.emp_name like '전%';

--oracle
select e.emp_name 사원명,
            e.emp_no 주민번호,
            d.dept_title 부서명,
            j.job_name 직급명 
from employee E, department D, job J
where e.dept_code = d.dept_id
and e.job_code=j.job_code
and substr(e.emp_no ,1,2) like '7_'
and substr(e.emp_no, 8,1) in (2,4)
and e.emp_name like '전%';

--3. 가장 나이가 적은 직원의 사번, 사원명, 나이, 부서명, 직급명을 조회하시오.
select e.emp_id 사번,
            e.emp_name 사원명,
            trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365) 나이,
           d.dept_title 부서명,
            j.job_name 직급명
from employee E
    join department D
        on e.dept_code=d.dept_id
    join job J
        on e.job_code=j.job_code
where trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365)
=(select min(trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365)) from employee e);

--oracle
select e.emp_id 사번,
            e.emp_name 사원명,
            trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365) 나이,
           d.dept_title 부서명,
            j.job_name 직급명
from employee E, department D, job J
where e.dept_code=d.dept_id
and e.job_code=j.job_code
and trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365)
=(select min(trunc((sysdate-(to_date((decode(substr(e.emp_no, 8,1), 1, 1900+substr(emp_no, 1,2), 2, 1900+substr(e.emp_no, 1,2), 2000+substr(e.emp_no, 1,2)))||substr(e.emp_no, 3,4))))/365)) from employee e);


--4. 이름에 '형'자가 들어가는 직원들의 사번, 사원명, 부서명을 조회하시오.
select e.emp_id 사번,
           e.emp_name 이름,
           d.dept_title 부서명
from employee E
    join department D
        on e.dept_code=d.dept_id
where e.emp_name like '%형%';

--oracle
select e.emp_id 사번,
           e.emp_name 이름,
           d.dept_title 부서명
from employee E, department D
where e.dept_code=d.dept_id
and e.emp_name like '%형%';

--5. 해외영업팀에 근무하는 사원명, 직급명, 부서코드, 부서명을 조회하시오.
select e.emp_name 사원명,
           j.job_name 직급명,
           d.dept_id 부서코드,
           d.dept_title 부서명
from employee E
    join department D
        on e.dept_code = d.dept_id
    join job j
        on e.job_code=j.job_code
where d.dept_title like'해외영업%';

--oracle

select e.emp_name 사원명,
           j.job_name 직급명,
           d.dept_id 부서코드,
           d.dept_title 부서명
from employee E, department D, job J
where e.dept_code=d.dept_id
and e.job_code=j.job_code
and d.dept_title like '해외영업%';

--6. 보너스포인트를 받는 직원들의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하시오.
select e.emp_name 사원명,
           e.bonus 보너스포인트,
           d.dept_title 부서명,
           l.local_name 근무지역명
from employee E
    join department D
        on e.dept_code=d.dept_id
    join location L
        on d.location_id=l.local_code
where e.bonus is not null;

--oracle
select e.emp_name 사원명,
           e.bonus 보너스포인트,
           d.dept_title 부서명,
           l.local_name 근무지역명
from employee E, department D, location L
where e.dept_code=d.dept_id
and d.location_id=l.local_code
and e.bonus is not null;

--7. 부서코드가 D2인 직원들의 사원명, 직급명, 부서명, 근무지역명을 조회하시오.
select e.emp_name 사원명,
           j.job_name 직급명,
           d.dept_title 부서명,
           l.local_name 근무지역명
from employee E
    join job J
        on e.job_code = j.job_code
    join department D
        on e.dept_code=d.dept_id
    join location L
        on d.location_id=l.local_code
where e.dept_code='D2';

--oracle
select e.emp_name 사원명,
           j.job_name 직급명,
           d.dept_title 부서명,
            l.local_name 근무지역명
from employee E, job j, department d, location l
where e.job_code = j.job_code
and e.dept_code=d.dept_id
and d.location_id=l.local_code
and e.dept_code='D2';

--8. 급여등급테이블의 등급별 최대급여(MAX_SAL)보다 많이 받는 직원들의 사원명, 직급명, 급여, 연봉을 조회하시오.
--(사원테이블과 급여등급테이블을 SAL_LEVEL컬럼기준으로 동등 조인할 것)
select e.emp_name 사원명,
            j.job_name 직급명,
            to_char(e.salary, 'fml9,999,999,999')||'원' 급여,
            to_char((e.salary+(e.salary*nvl(e.bonus,0)))*12, 'fml9,999,999,999')||'원' 연봉
from employee E
    join job J
        on e.job_code = j.job_code
    join sal_grade S
        on e.sal_level = s.sal_level
where s.max_sal <= e.salary;

--oracle
select e.emp_name 사원명,
           j.job_name 직급명,
           to_char(e.salary,'fml9,999,999,999')||'원' 급여,
           to_char((e.salary+(e.salary*nvl(e.bonus,0)))*12, 'fml9,999,999,999')||'원' 연봉
from employee E, job J, sal_grade S
where e.job_code = j.job_code
and e.sal_level = s.sal_level
and s.max_sal <= e.salary;

--9. 한국(KO)과 일본(JP)에 근무하는 직원들의 
--사원명, 부서명, 지역명, 국가명을 조회하시오.
select e.emp_name 사원명,
           d.dept_title 부서명,
           l.local_name 지역명,
           n.national_name 국가명
from employee E
    join job J
        on e.job_code = j.job_code
    join department D
        on e.dept_code=d.dept_id
    join location L
        on d.location_id=l.local_code
    join nation N
        on l.national_code=n.national_code
where l.national_code in ('KO','JP');

--oracle
select e.emp_name 사원명,
           d.dept_title 부서명,
           l.local_name 지역명,
           n.national_name 국가명
from employee E, job J, department D, location L, nation N
where e.job_code = j.job_code
and e.dept_code = d.dept_id
and d.location_id=l.local_code
and l.national_code=n.national_code
and l.national_code in('KO','JP');

--10. 같은 부서에 근무하는 직원들의 사원명, 부서코드, 동료이름을 조회하시오.
--self join 사용
select e1.emp_name 사원명,
           d.dept_title 부서코드,
           e2.emp_name 동료이름
from employee E1
    join department D
        on e1.dept_code = d.dept_id
    join employee E2
        on d.dept_id=e2.dept_code
where e1.emp_name <> e2.emp_name
order by 1,2;

--oracle
select e1.emp_name 사원명,
           d.dept_title 부서코드,
           e2.emp_name 동료이름
from employee E1, department D, employee E2
where e1.dept_code = d.dept_id
and d.dept_id=e2.dept_code
and e1.emp_name <> e2.emp_name
order by 1,2;

--11. 보너스포인트가 없는 직원들 중에서 직급이 차장과 사원인 직원들의 사원명, 직급명, 급여를 조회하시오.
select E.emp_name 사원명,
           j.job_name 직급명,
           to_char(e.salary, 'fml9,999,999,999')||'원' 급여
from employee E
    join job j
        on e.job_code = j.job_code
where bonus is null
and j.job_name in ('차장','사원');

--oracle
select e.emp_name 사원명,
           j.job_name 직급명,
           to_char(e.salary, 'fml9,999,999,999')||'원' 급여
from employee E, job J
where e.job_code = j.job_code
and e.bonus is null
and j.job_name in ('차장','사원');

--12. 재직중인 직원과 퇴사한 직원의 수를 조회하시오.
select count(decode(quit_yn,'N',1)) "재직한 직원의 수",
           count(decode(quit_yn,'Y',1)) "퇴사한 직원의 수"
from employee;
