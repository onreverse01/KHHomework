package member.model.vo;

public class Vvip extends Member{
	public Vvip() {}
	public Vvip(String name, String grade, int point) {
		super(name, grade, point);
	}
	
	public double getEjapoint() {
		return getPoint()*0.15;
	}
}
