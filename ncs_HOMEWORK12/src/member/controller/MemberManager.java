package member.controller;

import member.model.vo.Gold;
import member.model.vo.Silver;
import member.model.vo.Vip;
import member.model.vo.VVip;

public class MemberManager {
	private Silver[] sArr = new Silver[10];
	private Gold[] gArr = new Gold[10];
	private Vip[] vArr = new Vip[10];
	private VVip[] vvArr = new VVip[10];

	private int gidx = 0;
	private int sidx = 0;
	private int vidx = 0;
	private int vvidx = 0;

	public void silverInsert(Silver s) {
		sArr[sidx++] = s;

	}

	public void goldInsert(Gold g) {
		gArr[gidx++] = g;
	}
	
	public void vipInsert(Vip v) {
		vArr[vidx++] = v;
	}
	
	public void vvipInsert(VVip vv) {
		vvArr[vvidx++] = vv;
	}

	public void printData() {
		for (int i = 0; i < sidx; i++) {
			Silver s = sArr[i];
			System.out.printf("%s %s %d %.2f%n",s.getName(),s.getGrade(),s.getPoint(),s.bonusP());
		}
		for (int i = 0; i < gidx; i++) {
			Gold g = gArr[i];
			System.out.printf("%s %s %d %.2f%n",g.getName(),g.getGrade(),g.getPoint(),g.bonusP());
		}
		for (int i = 0; i < vidx; i++) {
			Vip v = vArr[i];
			System.out.printf("%s %s %d %.2f%n",v.getName(),v.getGrade(),v.getPoint(),v.bonusP());
		}
		for (int i = 0; i < vvidx; i++) {
			VVip vv = vvArr[i];
			System.out.printf("%s %s %d %.2f%n",vv.getName(),vv.getGrade(),vv.getPoint(),vv.bonusP());
		}
	}
}
