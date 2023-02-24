package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorSkull extends Entity{
	
	public static final String objName = "Skull Door";
	GamePanel gp;
	
	public OBJ_DoorSkull(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		
		name = objName;
		type = type_obstacle;
		image = setup("/objects/doors/skullDoor/close",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/doors/skullDoor/open",gp.tileSize,gp.tileSize);
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
		dialogues[0][0] = "It's locked.";
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
			gp.playSE(53);
		}
		else if(isUnlocked == true && opened == false) {
			down1 = image2;
			collision = false;
			opened = true;
			gp.playSE(52);
		}
		
	}
	
	}


