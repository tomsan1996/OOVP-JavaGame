package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_Stone extends InteractiveTile{

	GamePanel gp;
	public IT_Stone(GamePanel gp, int col, int row) {
		super(gp,  col,  row);
		this.gp = gp;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		down1 = setup("/tiles_interactive/Stone1",gp.tileSize,gp.tileSize);
		down3 = setup("/tiles_interactive/Stone1",gp.tileSize,gp.tileSize);
		destructible = true;
		life = 1;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_pickaxe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
public void playSE() {
		gp.playSE(39);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_BrokenStone(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	public Color getParticleColor() {
		Color color = new Color(70,70,70);
		return color;
	}
	public int getParticleSize() {
		int speed = 5;//PIXELS
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

}
