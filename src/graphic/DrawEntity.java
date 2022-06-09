package graphic;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import main.UtilityTool;

public class DrawEntity {
	BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,up0,down0,left0,right0;
	public Entity entity;
	
	public DrawEntity(Entity entity) {
		this.entity = entity;
		getImage();
	}
	
	public void getImage() {
		
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath));
			image = uTool.scaleImage(image, entity.gp.tileSizeX,entity.gp.tileSizeY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public BufferedImage draw() {
	
		return null;
	}
}
