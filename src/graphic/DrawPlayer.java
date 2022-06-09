package graphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import main.Panel;
import main.UtilityTool;

public class DrawPlayer extends DrawEntity{
	
	public DrawPlayer(Entity entity) {
		super(entity);
	}
	
	public void getImage() {
		up0 = setup("/player/up0.png");
		up1 = setup("/player/up1.png");
		up2 = setup("/player/up2.png");
		down0 = setup("/player/down0.png");
		down1 = setup("/player/down1.png");
		down2 = setup("/player/down2.png");
		left0 = setup("/player/left0.png");
		left1 = setup("/player/left1.png");
		left2 = setup("/player/left2.png");
		right0 = setup("/player/right0.png");
		right1 = setup("/player/right1.png");
		right2 = setup("/player/right2.png");
	}
	
	public BufferedImage draw() {
		BufferedImage image = null;
		switch(entity.direction){
			case "up":
				if(entity.spriteNum==0) {
					image = up0;}
				if(entity.spriteNum==1) {
					image = up1;}
				if(entity.spriteNum==2) {
					image = up2;}
				break;
			case "down":
				if(entity.spriteNum==0) {
					image = down0;}
				if(entity.spriteNum==1) {
					image = down1;}
				if(entity.spriteNum==2) {
					image = down2;}
				break;
			case "left":
				if(entity.spriteNum==0) {
					image = left0;}
				if(entity.spriteNum==1) {
					image = left1;}
				if(entity.spriteNum==2) {
					image = left2;}
				break;
			case "right":
				if(entity.spriteNum==0) {
					image = right0;}
				if(entity.spriteNum==1) {
					image = right1;}
				if(entity.spriteNum==2) {
					image = right2;}
				break;
		}
		return image;
	}
}
	
	

