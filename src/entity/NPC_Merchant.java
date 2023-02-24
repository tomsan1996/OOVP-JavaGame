package entity;



import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_KeyWooden;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity {

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		npcId = 2;
		getImage();
		setDialogue();
		setItems();
	}
	
	

	
public void getImage() {


	down1 = setup ("/npc/newNPC/newMerchant/1",gp.tileSize,gp.tileSize);;
	down2 = setup ("/npc/newNPC/newMerchant/1",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/newNPC/newMerchant/1",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/newNPC/newMerchant/1",gp.tileSize,gp.tileSize);

	}

public void setDialogue() {
	
	dialogues[0][0] = "Welcome Stranger.\nI have some good stuff.\nDo tou want to trade?";
	dialogues[1][0] = "Come again...";
	dialogues[2][0] = "You need more coin to buy that!";
	dialogues[3][0] = "You cannot carry any more!";
	dialogues[4][0] = "You cannot sell an equipped item!";

}

public void setItems() {
	
	inventory.add(new OBJ_Potion_Red(gp));
	inventory.add(new OBJ_KeyWooden(gp));
	inventory.add(new OBJ_Sword_Normal(gp));
	inventory.add(new OBJ_Axe(gp));
	inventory.add(new OBJ_Shield_Wood(gp));
	inventory.add(new OBJ_Shield_Blue(gp));
	inventory.add(new OBJ_Tent(gp));
}
	 

public void speak() {
	
	facePlayer();
	gp.gameState = gp.tradeState;
	gp.ui.npc = this;
}

}





