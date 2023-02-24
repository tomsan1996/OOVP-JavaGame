package entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import object.OBJ_DoorIron_Puzzle;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class NPC_Boulder extends Entity {;
	public static final String npcName = "Big Rock";

	public NPC_Boulder(GamePanel gp) {
		super(gp);
		name = npcName;
		npcId = 6;
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		direction = "down";
		speed = 4;

		
		solidArea = new Rectangle();
		solidArea.x = 2;
		solidArea.y = 6;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 40;
		
		dialogueSet = -1;
		getImage();
		setDialogue();
	}
	

	
public void getImage() {

		

	up1 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	up2 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	up3 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	up4 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	down1 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	down2 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	left1 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	left2 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	left3 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	left4 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	right1 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	right2 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	right3 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	right4 = setup ("/npc/newNPC/boulder/1",gp.tileSize,gp.tileSize);
	
	}

public void setDialogue() {
	
	dialogues[0][0] = "It's a smooth big boulder.";

}
public void move(String d) {
	this.direction = d;
	
	checkCollision();
	if(collisionOn == false) {
		switch(direction) {
		case "up" : worldY -= speed; break;
		case "down" : worldY += speed; break;
		case "left" : worldX -= speed; break;
		case "right" : worldX += speed; break;
		}
	}
	detectPlate();
}

public void setAction () {

		
 }
public void update() {
	
}

	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		// can set dialogue with conditions
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;

		}
	
	}
	
	public void detectPlate() {
		
		ArrayList<InteractiveTile> plateList = new ArrayList<>();
		ArrayList<Entity> boulderList = new ArrayList<>();
		
		// Create a plate list
		for(int i = 0; i < gp.iTile[1].length; i++) {
			if(gp.iTile[gp.currentMap][i] != null &&
					gp.iTile[gp.currentMap][i].name != null &&
					gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)){
				plateList.add(gp.iTile[gp.currentMap][i]);
						
			}
		}
		// Create a boulder list
		for(int i = 0; i < gp.npc[1].length; i++) {
			if(gp.npc[gp.currentMap][i] != null &&
					gp.npc[gp.currentMap][i].name != null &&
					gp.npc[gp.currentMap][i].name.equals(NPC_Boulder.npcName)){
				boulderList.add(gp.npc[gp.currentMap][i]);
						
			}
		}
		
		int count = 0;
		
		//SCAN Plate list
		for(int i = 0; i < plateList.size(); i++) {
			int xDistance = Math.abs(worldX - plateList.get(i).worldX);
			int yDistance = Math.abs(worldY - plateList.get(i).worldY);
			int distance = Math.max(xDistance, yDistance);
			
			if(distance < 8) {
				if(linkedEntity == null) {
					linkedEntity = plateList.get(i);
					gp.playSE(49);
				}
			} else {
				if(linkedEntity == plateList.get(i)) {
					linkedEntity = null;
				}
				
			}
		}
		
		//SCAN Boulder list
		for(int i = 0; i < boulderList.size(); i++) {
			//count the rock on the plate
			if(boulderList.get(i).linkedEntity != null) {
				count++;
			}
		}
		
		//if all the rocks are on the plates, iron door open.
		if(count == boulderList.size()) {
			for(int i = 0; i < gp.obj[1].length; i++) {
				if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_DoorIron_Puzzle.objName)){
					gp.obj[gp.currentMap][i].down1 = image2;
					gp.obj[gp.currentMap][i].collision = false;
					gp.obj[gp.currentMap][i].opened = true;
					gp.playSE(49);
				}
			}
			
		}
		
	}

}





