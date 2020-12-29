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

		for (Body b : bodyArr)
			if (b instanceof Head) {
				((Head) b).headBang();
			}
			else if (b instanceof Arm) {
				((Arm) b).armSwing();
			}
			else if (b instanceof Leg) {
				((Leg) b).run();
			}
	}

	public void action(Body body) {
		body.action();
	}
}
