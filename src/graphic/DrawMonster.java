package graphic;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import main.UtilityTool;

public class DrawMonster extends DrawEntity{
	
	public DrawMonster(Entity entity) {
		super(entity);
	}
	
	public void getImage() {
		up1 = setup("/monster/zup1.png");
		up2 = setup("/monster/zup2.png");
		down1 = setup("/monster/zdown1.png");
		down2 = setup("/monster/zdown2.png");
		left1 = setup("/monster/zleft1.png");
		left2 = setup("/monster/zleft2.png");
		right1 = setup("/monster/zright1.png");
		right2 = setup("/monster/zright2.png");
		
		
	}
	
	public BufferedImage draw() {
		BufferedImage image = null;
		switch(entity.direction){
			case "up":
				if(entity.spriteNum==1) {
					image = up1;}
				if(entity.spriteNum==2) {
					image = up2;}
				break;
			case "down":
				if(entity.spriteNum==1) {
					image = down1;}
				if(entity.spriteNum==2) {
					image = down2;}
				break;
			case "left":
				if(entity.spriteNum==1) {
					image = left1;}
				if(entity.spriteNum==2) {
					image = left2;}
				break;
			case "right":
				if(entity.spriteNum==1) {
					image = right1;}
				if(entity.spriteNum==2) {
					image = right2;}
				break;
		}
		return image;
	}
	
}
