--@실습문제 : tb_number테이블에 난수 100개(0 ~999)를 저장하는 익명블럭을 생성하세요.
--실행시마다 생성된 모든 난수의 합을 콘솔에 출력할 것.
create table tb_number(
    id number, --pk sequence객체로 부터 채번
    num number, --난수
    reg_date date default sysdate,
    constraints pk_tb_number_id primary key(id)
);

create sequence seq_tb_num_id
increment by 1;

declare
    rnd number;
    n number :=1;
    cnt number :=0;
begin
    loop
        rnd := trunc(dbms_random.value(0, 999));
        dbms_output.put_line(n || ' : ' || rnd);
        insert into tb_number
        values(seq_tb_num_id.nextval, rnd,sysdate);
        n := n+1;
        cnt :=cnt+rnd;
        exit when n>100;
    end loop;
    
    dbms_output.put_line(cnt);
end;
/
select * from tb_number;

--drop table tb_number;
--drop sequence seq_tb_num_id;