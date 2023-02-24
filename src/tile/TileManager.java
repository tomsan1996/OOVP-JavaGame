package tile;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;	
	public int[][][] mapTileNum;	
	boolean drawPath = true;
	
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
		
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		// read tile data from text file.
		
		
		// can input all tile data to tiledata.txt for overworld, interior, dungeon here.
		InputStream is = getClass().getResourceAsStream("/newMap/tiledata.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		// GETTING TILE NAME AND COLLISION INFO FROM FILE
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		//INITIALIZE TILE ARRAY BASED ON fileNames size.
		tile = new Tile[fileNames.size()];
		getTileImage();
		
		// GET maxWorldCol & Row; for MAP SIZE.
		
		is = getClass().getResourceAsStream("/newMap/overworld_01.txt");
		br = new BufferedReader(new InputStreamReader(is));
		
		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
			
			br.close();
		} catch(IOException e) {
			System.out.println("Error loading Map Size.");
		}
		
		
		loadMap("/newMap/overworld_01.txt", 0);
		loadMap("/newMap/interior_01.txt", 1);
		loadMap("/newMap/dungeon_01.txt", 2);
		
		loadMap("/newMap/overworld_02.txt",3);
		loadMap("/newMap/interior_02.txt", 4);
		loadMap("/newMap/dungeon_02.txt", 5);
		loadMap("/newMap/dungeon_02_2F.txt", 6);
		
//		loadMap("/newMap/worldV3.txt", 0);
//		loadMap("/newMap/interior01.txt", 1);
//		loadMap("/newMap/overworld.txt", 2);
	}
	
	public void getTileImage() {
		
		
		for(int i = 0; i < fileNames.size(); i++){
			String fileName;
			boolean collision;
			
			// get file name
			fileName = fileNames.get(i);
			
			// get collision status from string to boolean
			if(collisionStatus.get(i).equals("true")){
				collision = true;
			} else {
				collision = false;
			}
			
			setup(i, fileName, collision);	
		}
				
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/newTiles/" + imageName));
			tile [index].image = uTool.ScaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile [index].collision = collision;
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath, int map) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while(col <gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
		
		}catch(Exception e) {
		
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				
			}
			

			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;

				worldRow++;

			}
						
		}
		
		// PATHFINDING DEBUG > SHOW PATH WITH RED COLOR
		if (drawPath == true) {
			g2.setColor(new Color (255,0,0, 50));
			for(int i = 0; i <gp.pFinder.pathList.size(); i++) {
				int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
				int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
			}
		}
	}
}
