package ai;

import java.util.Random;

import entity.Entity;

public class AI {
	Entity entity;
	public AI(Entity entity){
		this.entity=entity;
	}
	
	public void action() {
		Random random = new Random();
		
		int i = random.nextInt(100)+1;
		
		if(i<=25){
			entity.direction = "up";
		}
		if(i>25&&i<=50){
			entity.direction = "down";
		}
		if(i>50&&i<=75){
			entity.direction = "left";
		}
		if(i>75){
			entity.direction = "right";
		}
	}
}
