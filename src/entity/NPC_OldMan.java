package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		npcId = 3;
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		direction = "down";
		speed = 1;
		dialogueSet = -1;
		getImage();
		setDialogue();
	}
	

	
public void getImage() {

		

	up1 = setup ("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
	up2 = setup ("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
	up3 = setup ("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
	up4 = setup ("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
	down1 = setup ("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
	down2 = setup ("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
	down3 = setup ("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
	down4 = setup ("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
	left1 = setup ("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
	left2 = setup ("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
	left3 = setup ("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
	left4 = setup ("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
	right1 = setup ("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
	right2 = setup ("/npc/oldman_right_2",gp.tileSize,gp.tileSize);
	right3 = setup ("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
	right4 = setup ("/npc/oldman_right_2",gp.tileSize,gp.tileSize);
	
	}

public void setDialogue() {
	
	dialogues[0][0] = "Hello, lad.";
	dialogues[0][1] = "So you've come to this island to \n find the treasure?.";
	dialogues[0][2] = "I have an axe in my hut, you can use it.";
	dialogues[0][3] = "well, good luck on you.";
	
	dialogues[1][0] = "If you're tired, you can try\ndrinking the fountain water.";
	dialogues[1][1] = "however it will awaken \nthe monsters in this island.";
	dialogues[1][2] = "Take it easy will ya?";

	
	dialogues[2][0] = "I heard there's a big monster somewhere..";
}

public void setAction () {
	
	if(onPath == true) {
		int goalCol = 16;
		int goalRow = 33;
		
		// FOR FOLLOWING PLAYER, goalCol and goalRow = playerPosition.
//		int goalCol = (gp.player.worldX +gp.player.solidArea.x)/gp.tileSize;
//		int goalRow = (gp.player.worldY +gp.player.solidArea.y)/gp.tileSize;
		searchPath(goalCol, goalRow);
		
	} else {
		 actionLockCounter ++;
		 
		 if( actionLockCounter == 120) {
			 
				Random random = new Random();
				int i = random.nextInt(100)+1; //pick up number
				
				if ( i <= 25) {
					direction = "up";
				}
				
				if ( i > 25 && i <= 50) {
					direction = "down";
				}
				
				if ( i > 50 && i <= 75) {
					direction = "left";
				}
				
				if ( i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
	}
	


		
 }

	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		// can set dialogue with conditions
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
			onPath = true;

		}
	
	}

}





