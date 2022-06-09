package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.*;
import graphic.*;
import map.*;

public class Panel extends JPanel implements Runnable{
	
	public final int originalTileSize = 16;
	public final int scale = 3;
	
	public final int tileSizeX = originalTileSize*scale;
	public final int tileSizeY = originalTileSize*(scale+1);
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 10;
	public final int screenWidth = tileSizeX*maxScreenCol;
	public final int screenHeight = tileSizeY*maxScreenRow;
	
	public final int FPS=60;
	public boolean GameOver = false;
	
	//System
	public Graphics2D g2;
	public Thread gameThread;
	public TileManager tileM = new TileManager(this);
	public Input keyH = new Input();
	public Setup set = new Setup(this);
	public CheckCollision check = new CheckCollision(this);
	
	//Entity
	public Entity player = new Player(this);
	public DrawPlayer drawP = new DrawPlayer(player);
	public Entity monsters[] = new Monster_Normal[10];
	public DrawMonster drawM[] = new DrawMonster[10];
	public Sort sort = new Sort(this);	
	
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void setupGame() {
		set.setupGame();
	}
	
	
	public void run() {
		double delta=0;
		long currentTime = 0;
		long lastTime = System.nanoTime();
		double drawInterval = 1000000000/FPS;
		
		while (gameThread!=null) {
			currentTime = lastTime;
			lastTime = System.nanoTime();
			delta += (-currentTime +lastTime)/drawInterval;
			if(delta>=1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	

	public void update() {
		player.update();
		
		for (int i = 0; i < monsters.length; i++) {
			if(monsters[i]!=null) {
				monsters[i].update();
			}
		}
		sort.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		tileM.draw(g2);
		g2.setColor(Color.red);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,40f));
		g2.drawString("HP:"+player.HP+"/"+player.MaxHP, tileSizeX, tileSizeY/2);
		g2.setColor(Color.blue);
		g2.drawString("MP:"+player.MP+"/"+player.MaxMP, tileSizeX, tileSizeY);
		
		for (int i = 0; i < sort.YPos.length; i++) {
			if(sort.YPos[i]==drawP.entity.y) {
				if(player.invincible==true) {
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
					g2.drawImage(drawP.draw(),player.x,player.y,tileSizeX,tileSizeY,null);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));				
				}
				else {	g2.drawImage(drawP.draw(),player.x,player.y,tileSizeX,tileSizeY,null);}
				g2.setColor(Color.red);
				g2.drawRect(player.x+player.solidArea.x, player.y+player.solidArea.y,player.solidArea.width, player.solidArea.height);
			}
			
			for (int j = 0; j < drawM.length; j++) {
				if(monsters[j]!=null&&drawM[j]!=null) {
					if(sort.YPos[i]==monsters[j].y) {
						g2.drawImage(drawM[j].draw(),monsters[j].x,monsters[j].y,tileSizeX,tileSizeY,null);
						g2.drawRect(monsters[j].x+monsters[j].solidArea.x, monsters[j].y+monsters[j].solidArea.y,monsters[j].solidArea.width, monsters[j].solidArea.height);
					}
				}
			}
		}
	}
	
}
