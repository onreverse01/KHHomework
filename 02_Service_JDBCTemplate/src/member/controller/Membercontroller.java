package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

public class Membercontroller {
	
	private MemberService memberService = new MemberService();

	public List<Member> selectAll() {
		return memberService.selectAll();
	}
	
	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}

	public Member selectOne(String memberId) {
		return memberService.selectOne(memberId);
	}

	public List<Member> selectByName(String memberName) {
		return memberService.selectByName(memberName);
	}

	public int deleteMember(String memberId) {
		return memberService.deleteMember(memberId);
	}

	public int updateMember(Member member) {
		return memberService.updateMember(member);
	}
	
}
