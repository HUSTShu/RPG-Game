package entity;

import java.awt.Rectangle;

import main.Panel;

public class Entity {
	public Panel gp;
	//Entity Status
	public int lv,x,y,speed;
	public String direction;
	public int HP,MaxHP,MP,MaxMP,ATK,DEF;

	public boolean invincible = false;
	public int invincibleTime = 0;
	
	//XuLyVaCham
	public Rectangle solidArea = new Rectangle(10,30,28,34);//to check Collision;
	public boolean collision;
	public boolean collisionOn = false;
	public int solidAreaDefaultX,solidAreaDefaultY;

	//for draw
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int AICounter =0;
	
	public Entity(Panel gp) {
		this.gp=gp;
		direction = "down";
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

	public void setAI() {}
	
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
		//for draw
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
		collisionOn = false;
		gp.check.checkTile(this);
		gp.check.checkPlayer(this);
		gp.check.checkEntity(this, gp.monsters);
		
	}
	
	public void attackPlayer() {
		boolean touchPlayer = gp.check.checkPlayer(this);
		if(touchPlayer==true) {
			gp.player.getHit();
		}
	}
	
	public void getHit() {
		if(invincible==false) {
			HP--;
			invincible =true;
		}
	}
	
	
	public void update() {
		setAI();
		XuLiVaCham();	
		attackPlayer();
		move();
		
		
	}
}
