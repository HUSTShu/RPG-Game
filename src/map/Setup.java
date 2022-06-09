package map;

import entity.Monster_Normal;
import entity.Monster_Shot;
import graphic.DrawMonster;
import main.Panel;

public class Setup {
	Panel gp;
	public Setup(Panel gp) {
		this.gp=gp;
	}
	public void setupGame() {
		setMonster();
	}
	public void setMonster() {
		gp.monsters[0] = new Monster_Normal(gp);
		gp.monsters[0].x = 9*gp.tileSizeX;
		gp.monsters[0].y = 7*gp.tileSizeY;
		
		gp.monsters[1] = new Monster_Shot(gp);
		gp.monsters[1].x = 10*gp.tileSizeX;
		gp.monsters[1].y = 7*gp.tileSizeY;
		
		for (int i = 0; i < gp.monsters.length; i++) {
			if(gp.monsters[i]!=null) {
				gp.drawM[i] = new DrawMonster(gp.monsters[i]);
			}
		}
	}
	public void setItem() {
		
	}
}
