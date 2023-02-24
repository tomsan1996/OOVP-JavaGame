package entity;



import java.awt.Rectangle;

import main.GamePanel;

public class NPC_SaveBeacon extends Entity {
	

	public NPC_SaveBeacon(GamePanel gp) {
		
		super(gp);
		npcId = 1;
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		direction = "down";
		getImage();
		setDialogue();
	}
	
	public void getImage() {

		

	up1 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	up2 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	up3 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	up4 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	down1 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	down2 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	left1 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	left2 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	left3 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	left4 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	right1 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	right2 = setup ("/npc/newNPC/saveBeacon/1",gp.tileSize,gp.tileSize);
	right3 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	right4 = setup ("/npc/newNPC/saveBeacon/2",gp.tileSize,gp.tileSize);
	
	}

	public void setDialogue() {
	
	dialogues[0][0] = "Do you want to save your\ngame progress?";
	dialogues[1][0] = "Game saved!";
	dialogues[2][0] = "Game not saved.";
	}	

	public void setAction () {
	
		;
	}

	public void speak() {
		
		gp.gameState = gp.saveState;
		gp.ui.npc = this;
	
	}

}





