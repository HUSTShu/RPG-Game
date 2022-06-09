package main;

public class Sort {
	Panel gp;
	public int YPos[] ;
	public Sort(Panel gp) {
		this.gp=gp;
		YPos = new int[1+gp.monsters.length];
	}
	
	public void update() {
		getY();
		sort();
	}
	
	
	public void getY() {
		YPos[0] = gp.player.y;
		for (int i =0; i < gp.monsters.length; i++) {
			if(gp.monsters[i]!=null) {
				YPos[i+1] = gp.monsters[i].y;
			}
		}
	}
	
	
	
	public void sort() {
		int temp=0;
		for (int i = 0; i < YPos.length; i++) {
			for (int j = i; j < YPos.length; j++) {
				if(YPos[i]>YPos[j]) {
					temp = YPos[i];
					YPos[i]=YPos[j];
					YPos[j]=temp;
				}
			}
			
		}
	}
}
