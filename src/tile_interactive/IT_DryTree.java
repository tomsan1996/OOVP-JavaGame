package tile_interactive;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile{

	GamePanel gp;
	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp,  col,  row);
		this.gp = gp;

		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		down1 = setup("/tiles_interactive/bigTree1",gp.tileSize*3,gp.tileSize*3);
		down3 = setup("/tiles_interactive/bigTree1",gp.tileSize,gp.tileSize);
		destructible = true;
		life = 3;
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
public void playSE() {
		gp.playSE(40);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	public Color getParticleColor() {
		Color color = new Color(65,50,30);
		return color;
	}
	public int getParticleSize() {
		int speed = 6;//PIXELS
		return speed;
		
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 25;
		return maxLife;
	}
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		int tempScreenX =  screenX;
		int tempScreenY = screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			tempScreenX = screenX - gp.tileSize;
			tempScreenY = screenY - gp.tileSize*2;
			g2.drawImage(down1, tempScreenX, tempScreenY, null);

		}
	}

}
