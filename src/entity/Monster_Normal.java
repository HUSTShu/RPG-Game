package entity;

import java.util.Random;

import ai.AI;
import main.Panel;

public class Monster_Normal extends Entity{
	AI ai = new AI(this);
	Random random = new Random();
	public Monster_Normal(Panel gp) {
		super(gp);

		speed = 1;
		MaxHP = random.nextInt(15);
		HP = MaxHP;
		DEF = random.nextInt(3);
		ATK = random.nextInt(3);
	}
	
	public void setAI() {
		AICounter++;
		if(AICounter==120) {
			ai.action();
			AICounter=0;
		}
	}

}
