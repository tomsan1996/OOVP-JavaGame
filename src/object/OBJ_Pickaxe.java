package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

	public static final String objName = "Miner's Pickaxe";
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		type = type_pickaxe;
		name = objName;
		knockBackPower = 5;
		//Duration for atkspeed
		motion1_duration = 20;
		motion2_duration = 40;
		down1 = setup("/objects/pickaxe",gp.tileSize,gp.tileSize);
		down3 = setup("/objects/pickaxe",gp.tileSize,gp.tileSize);
		
		attackValue = 2;
		attackArea.width =36;
		attackArea.height = 36;
		description = "["+name+"]  \ncan be used to mine stones.";
		price = 30;
	}


}
