package main;

import entity.Entity;

public class CheckCollision {
	Panel gp;
	
	public CheckCollision(Panel gp) {
		this.gp =gp;
	}
	
public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.x + entity.solidArea.x;
		int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.y + entity.solidArea.y;
		int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSizeX;
		int entityRightCol = entityRightWorldX/gp.tileSizeX;
		int entityTopRow = entityTopWorldY/gp.tileSizeY;
		int entityBottomRow = entityBottomWorldY/gp.tileSizeY;
		
		int tileNum1,tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSizeY;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn = true;
			}
			
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSizeY;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSizeX;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSizeX;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision==true||gp.tileM.tile[tileNum2].collision==true) {
				entity.collisionOn = true;
			}
			break;	
		
		}
		
		
		
	}
	
	/*public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i]!=null) {
				//get entity solid
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get object solid
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y-=entity.speed;break;
				case "down":
					entity.solidArea.y+=entity.speed;break;
				case "left":
					entity.solidArea.x-=entity.speed;break;
				case "right":
					entity.solidArea.x+=entity.speed;break;
				}
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if(gp.obj[i].collision == true) {
						entity.collisionOn = true;
						if(player==true) {
							index = i;
						}
					}
				}
				//reset solid
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}*/
	//check NPC or monster collision
	public int checkEntity(Entity entity,Entity[] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if(target[i]!=null) {
				//get entity solid
				entity.solidArea.x = entity.x + entity.solidArea.x;
				entity.solidArea.y = entity.y + entity.solidArea.y;
				//get object solid
				target[i].solidArea.x = target[i].x +target[i].solidArea.x;
				target[i].solidArea.y = target[i].y + target[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y-=entity.speed;break;
				case "down":
					entity.solidArea.y+=entity.speed;break;
				case "left":
					entity.solidArea.x-=entity.speed;break;
				case "right":
					entity.solidArea.x+=entity.speed;break;
				}
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(entity!=target[i]) {
					entity.collisionOn = true;
					index = i;}
				}
				//reset solid
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;		
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	
	//check player collision (use for npc or monster)
	public boolean checkPlayer(Entity entity) {
		boolean touchPlayer = false;
		
		//get entity solid
		entity.solidArea.x = entity.x + entity.solidArea.x;
		entity.solidArea.y = entity.y + entity.solidArea.y;
		//get object solid
		gp.player.solidArea.x = gp.player.x +gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y-=entity.speed;break;
		case "down":
			entity.solidArea.y+=entity.speed;break;
		case "left":
			entity.solidArea.x-=entity.speed;break;
		case "right":
			entity.solidArea.x+=entity.speed;break;
		}
		
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			touchPlayer = true;
		}
		
		//reset solid
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	
		return touchPlayer;
	}

}
