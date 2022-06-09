package entity;

import java.awt.Rectangle;

import graphic.DrawPlayer;
import main.Panel;

public class Player extends Entity{
	
	DrawPlayer drawP;
	public Player(Panel gp) {
		super(gp);
		
		setDefault();
	}
	public void setDefault() {
		x= 100;
		y=100;
	
		solidArea = new Rectangle(10,32,28,32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		lv=1;
		speed = 4;
		ATK = 3;
		DEF = 3;
		MaxHP = 6;
		HP=MaxHP;
		MaxMP = 4;
		MP=MaxMP;
	}
	public void checkHPMP() {
		if(HP>MaxHP) HP = MaxHP;
		if(MP>MaxMP) MP = MaxMP;
		if(MP<0) MP=0;	
	}
	
	public void attack() {
		if(gp.keyH.attack==true) {
			
		}
		
	}
	
	public void move() {
		if(collisionOn==false) {
			switch(direction) {
			case "up":
				y-=speed; break;
			case "down":
				y+=speed; break;
			case "left":
				x-=speed; break;
			case "right":
				x+=speed; break;
			}
		}
		//for draw image
		spriteCounter++;
		if(spriteCounter>15) {
			if(spriteNum==1) {
				spriteNum=2;
			}
			else if(spriteNum==2) {
				spriteNum=1;
			}
			spriteCounter=0;
		}
	}
	
	public void XuLiVaCham() {
		//check Tile collision
		collisionOn = false;
		gp.check.checkTile(this);
		//check Monster collision
		int monsterIndex = gp.check.checkEntity(this, gp.monsters);
		interactMonster(monsterIndex);
	}
	
	
	public void interactMonster(int i) {
		if(i!=999) {
			getHit();
		}
	}
	
	public void getHit() {
		if(invincible==false) {
			HP--;
			invincible = true;
		}
	}
	
	public void update() {
		checkHPMP();
		//invincible
		invincibleTime++;
		if(invincibleTime>120) {
			invincible=false;
			invincibleTime=0;
		}
		if(gp.keyH.upPressed==true||gp.keyH.downPressed==true||gp.keyH.leftPressed==true||gp.keyH.rightPressed==true) {
			if(gp.keyH.upPressed==true) {
				direction = "up";
			}
			else if(gp.keyH.downPressed==true) {
				direction = "down";
			}
			else if(gp.keyH.leftPressed==true) {
				direction = "left";
			}
			else if(gp.keyH.rightPressed==true) {
				direction = "right";
			}
			XuLiVaCham();
			move();
		}
		
		
	}
}
