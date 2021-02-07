--1. 첫번째 테이블 명 : EX_MEMBER
--* MEMBER_CODE(NUMBER) - 기본키						-- 회원전용코드 
--* MEMBER_ID (varchar2(20) ) - 중복금지					-- 회원 아이디
--* MEMBER_PWD (char(20)) - NULL 값 허용금지					-- 회원 비밀번호
--* MEMBER_NAME(varchar2(30)) 							-- 회원 이름
--* MEMBER_ADDR (varchar2(100)) - NULL값 허용금지					-- 회원 거주지
--* GENDER (char(3)) - '남' 혹은 '여'로만 입력 가능				-- 성별
--* PHONE(char(11)) - NULL 값 허용금지 					-- 회원 연락처


create table EX_MEMBER (
    MEMBER_CODE number,
    MEMBER_ID varchar2(20),
    MEMBER_PWD char(20) not null,
    MEMBER_NAME varchar2(30),
    MEMBER_ADDR varchar2(100) not null,
    GENDER char(3),
    PHONE char(11) not null,
    constraint pk_member_code primary key (MEMBER_CODE),
    constraint uq_member_id unique (MEMBER_ID),
    constraints ch_gender check(GENDER in ('남','여'))
);

--2. EX_MEMBER_NICKNAME 테이블을 생성하자. (제약조건 이름 지정할것)
--(참조키를 다시 기본키로 사용할 것.)
--* MEMBER_CODE(NUMBER) - 외래키(EX_MEMBER의 기본키를 참조), 중복금지		-- 회원전용코드
--* MEMBER_NICKNAME(varchar2(100)) - 필수 						-- 회원 이름


create table EX_MEMBER_NICKNAME(
    MEMBER_CODE number,
    MEMBER_NICKNAME varchar2(100) not null,
    constraint fk_ex_member_id foreign key(MEMBER_CODE) references EX_MEMBER (MEMBER_CODE),
    constraint pk_ex_member_id primary key(MEMBER_CODE)
    
)