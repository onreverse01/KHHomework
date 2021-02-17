package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.Membercontroller;
import member.model.vo.Member;

public class MemberMenu {

	private Scanner sc = new Scanner(System.in);
	private Membercontroller memberController = new Membercontroller();
	
	public void mainMenu() {
		String menu = "========== 회원 관리 프로그램 ==========\n"
				+ "1.회원 전체조회\n"
				+ "2.회원 아이디조회\n" 
				+ "3.회원 이름조회\n" 
				+ "4.회원 가입\n" 
				+ "5.회원 정보변경\n" 
				+ "6.회원 탈퇴\n" 
				+ "0.프로그램 끝내기\n"
				+ "----------------------------------\n"
				+ "선택 : "; 
		
		
		do {
			System.out.print(menu);
			String choice = sc.next();
			List<Member> list = null;
			Member member = null;
			String msg = null;
			String memberId = null;
			String memberName = null;
			int result = 0;
			
			switch(choice) {
			case "1": 
				list = memberController.selectAll();
				displayMemberList(list);
				break;
			case "2": 
				memberId = inputMemberId();
				member = memberController.selectOne(memberId);
				displayMember(member);
				break;
			case "3": 
				memberName = inputMemberName();
				list = memberController.selectByName(memberName);
				displayMemberList(list);
				break;
			case "4": 
				member = inputMember();
				System.out.println("신규회원 확인 : "+ member);
				result = memberController.insertMember(member);
				msg = result > 0 ? "회원 가입 성공!" : "회원 가입 실패!";
				displayMsg(msg);
				break;
			case "5":
				memberId = inputMemberId();
				member = memberController.selectOne(memberId);
				updateMemberMenu(member);
				break;
			case "6": 
				memberId = inputMemberId();
				result = memberController.deleteMember(memberId);
				msg = result > 0 ? "회원 탈퇴 성공" : "회원 탈퇴 실패";
				displayMsg(msg);
				break;
			case "0": 
				System.out.print("정말 끝내시겠습니까?(y/n) : ");
				if(sc.next().charAt(0) == 'y')
					return;
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
		} while(true);
	}
	

	private void displayMsg(String msg) {
		System.out.println(">>> 처리결과 : "+ msg);
	}

	//회원 수정 메뉴
	public void updateMemberMenu(Member member) {
		if(member == null)
			System.out.println(">>>> 조회된 회원이 없습니다.");
		else {
			System.out.println("****************************************************************");
			System.out.println(member);
			System.out.println("****************************************************************");
			String menu = "*** 회원 정보 변경 메뉴 ***\n"
					+ "1.암호변경\n"
					+ "2.이메일변경\n" 
					+ "3.전화번호변경\n" 
					+ "4.주소 변경\n" 
					+ "9.메인메뉴 돌아가기\n" 
					+ "----------------------------------\n"
					+ "선택 : "; 
			do {
				System.out.print(menu);
				String choice = sc.next();
				int result = 0;
				String msg= null;
				
				switch(choice) {
				case "1": 
					System.out.print("변경할 암호 : ");
					member.setPassword(sc.next());
					result = memberController.updateMember(member);
					msg = result > 0 ? "회원 수정 성공" : "회원 수정 실패!";
					displayMember(member);
					displayMsg(msg);
					break;
				case "2":
					System.out.print("이메일 변경 : ");
					member.setEmail(sc.next());
					result = memberController.updateMember(member);
					msg = result > 0 ? "회원 수정 성공" : "회원 수정 실패!";
					displayMember(member);
					displayMsg(msg);
					break;
				case "3": 
					System.out.print("전화번호 변경 : ");
					member.setPhone(sc.next());
					result = memberController.updateMember(member);
					msg = result > 0 ? "회원 수정 성공" : "회원 수정 실패!";
					displayMember(member);
					displayMsg(msg);
					break;
				case "4": 
					System.out.print("주소 변경 : ");
					member.setAddress(sc.next());
					result = memberController.updateMember(member);
					msg = result > 0 ? "회원 수정 성공" : "회원 수정 실패!";
					displayMember(member);
					displayMsg(msg);
					break;
				case "9":
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			} while(true);
		}
		
	}
	
	private String inputMemberName() {
		System.out.print("조회할 이름 입력 : ");
		return sc.next();
	}


	private void displayMember(Member member) {
		if(member == null)
			System.out.println(">>>> 조회된 회원이 없습니다.");
		else {
			System.out.println("****************************************************************");
			System.out.println(member);
			System.out.println("****************************************************************");
		}
		
	}


	private Member inputMember() {
		System.out.println("새로운 회원정보를 입력하세요.");
		Member member = new Member();
		System.out.print("아이디 : ");
		member.setMemberId(sc.next());
		System.out.print("이름 : ");
		member.setMemberName(sc.next());
		System.out.print("비밀번호 : ");
		member.setPassword(sc.next());
		System.out.print("나이 : ");
		member.setAge(sc.nextInt());
		System.out.print("성별(M/F) : ");//m, f
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));
		System.out.print("이메일: ");
		member.setEmail(sc.next());
		System.out.print("전화번호(-빼고 입력) : ");
		member.setPhone(sc.next());
		sc.nextLine();//버퍼에 남은 개행문자 날리기용 (next계열 - nextLine)
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());
		System.out.print("취미(,로 나열할것) : ");
		member.setHobby(sc.nextLine());
		return member;
	}


	private String inputMemberId() {
		System.out.println("조회할 아이디 입력: ");
		return sc.next();
	}

	/**
	 * n행의 회원정보 출력
	 * @param list
	 */
	private void displayMemberList(List<Member> list) {
		if(list != null && !list.isEmpty()) {
			System.out.println("============================================================");
			for(int i = 0; i< list.size(); i++)
				System.out.println((i+1) + ": " + list.get(i));
			System.out.println("============================================================");
		}
		else {
			System.out.println(">>> 조회된 회원 정보가 없습니다.");
		}
	}
}
