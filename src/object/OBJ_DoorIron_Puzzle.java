package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorIron_Puzzle extends Entity{
	
	public static final String objName = "Puzzle Iron Door";
	GamePanel gp;
	
	public OBJ_DoorIron_Puzzle(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		name = objName;
		type = type_obstacle;
		image = setup("/objects/doors/ironDoor/close",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/doors/ironDoor/open",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		 
			
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "It wont budge.";
	}
	public void interact() {
		
		if(opened == false && isUnlocked == false) {
			startDialogue(this, 0);
			gp.playSE(48);
		}
		else if(isUnlocked == true && opened == true) {
			down1 = image;
			collision = true;
			opened = false;
			gp.playSE(51);
		}
		else if(isUnlocked == true && opened == false) {
			down1 = image2;
			collision = false;
			opened = true;
			gp.playSE(50);
		}
		
	}
	
	}


