package tile_interactive;

import main.GamePanel;

public class IT_BrokenStone extends InteractiveTile{

	GamePanel gp;
	public IT_BrokenStone(GamePanel gp, int col, int row) {
		super(gp,  col,  row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		down1 = setup("/tiles_interactive/Stone2",gp.tileSize,gp.tileSize);
		down3 = setup("/tiles_interactive/Stone2",gp.tileSize,gp.tileSize);
		destructible = true;
		

		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void playSE() {
		gp.playSE(42);
	}
}
