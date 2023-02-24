package object;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{

	public static final String objName = "Boots";
	
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		
		name = objName;
		description = "["+name+"]\nWearing these will increase\nmovement speed.";
		type = type_boots;
		down1 =  setup("/objects/boots",gp.tileSize,gp.tileSize);	
		price = 200;
		speedValue = 2;
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
