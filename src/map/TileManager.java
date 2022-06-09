package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Panel;
import main.UtilityTool;

public class TileManager {
	Panel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(Panel gp ) {
		this.gp=gp;
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap("/maps/map.txt");
	
	}
	public void loadMap(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col<gp.maxScreenCol&& row < gp.maxScreenRow) {
				String line = br.readLine();
				
				while(col< gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num	= Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row]=num;
					col++;
					}
				if(col==gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			
		}
	}
	public void getTileImage() {
		
		setup(0,"grass",false);
	
		setup(1,"road-doc",false);
		
		setup(2, "road-ngang", true);
		
		setup(3, "cross",true);
	}	
	
	
	public void setup(int index,String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
		
			tile[index] = new Tile();
			tile[index].image =  ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSizeX, gp.tileSizeY);
			tile[index].collision = collision;
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
	
	
		int col=0,row=0;
		int x=0,y=0;
	
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			int tileNum = mapTileNum[col][row];
			g2.drawImage(tile[tileNum].image, x, y, null);
			col++;
			x+=gp.tileSizeX;
		
			if(col==gp.maxScreenCol) {
				col = 0;
				x=0;
				row++;
				y+=gp.tileSizeY;	
			}
		}	
	}
}
