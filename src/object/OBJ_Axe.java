package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public static final String objName = "Woodcutter's Axe";
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);		
		type = type_axe;
		name = objName;
		knockBackPower = 10;
		//Duration for atkspeed
		motion1_duration = 20;
		motion2_duration = 40;
		down1 = setup("/objects/axe",gp.tileSize,gp.tileSize);
		down3 = setup("/objects/axe",gp.tileSize,gp.tileSize);
		
		attackValue = 1;
		attackArea.width =36;
		attackArea.height = 36;
		description = "["+name+"]\ncan cut some trees.";
		price = 200;
	}
}
