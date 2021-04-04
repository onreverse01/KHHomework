package com.oop.body.controller;

import com.oop.body.model.Arm;
import com.oop.body.model.Body;
import com.oop.body.model.Head;
import com.oop.body.model.Leg;

public class ActionBody {
	public static void main(String[] args) {
		Body bodyArr[] = new Body[5];
		bodyArr[0] = new Head("파란색");
		bodyArr[1] = new Arm();
		bodyArr[2] = new Arm();
		bodyArr[3] = new Leg();
		bodyArr[4] = new Leg();

		ActionBody ac = new ActionBody();
		for(int i=0; i<bodyArr.length; i++){
			ac.action(bodyArr[i]);
		}
			
	}

	public void action(Body body) {
		if (body instanceof Head) {
			((Head) body).headBang();
		}
		else if (body instanceof Arm) {
			((Arm) body).armSwing();
		}
		else if (body instanceof Leg) {
			((Leg) body).run();
		}
	}
}
