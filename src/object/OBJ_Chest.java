package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	
	public static final String objName = "Chest";
	
	GamePanel gp;

	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;

		name= objName;
		type = type_obstacle;
		image =  setup("/objects/chest1",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/chest2",gp.tileSize,gp.tileSize);
		price = 20;
		down1 = image;
		collision = true;

		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	


	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0]	= "You opened the chest and find a " +loot.name+ "\n... but you cannot carry any more items.";
		dialogues[1][0]	= "\nYou obtained the "+ loot.name+ "!";
		dialogues[2][0]	= "It's empty.";
	}
	
	public void interact()	{

		if(opened == false) {
			gp.playSE(12);
			if(gp.player.canObtainItem(loot) == false) {
				startDialogue(this, 0);
				
			} else {
				gp.playSE(47);
				startDialogue(this, 1);
				down1 = image2;
				opened = true;
			}

		} else {
			startDialogue(this, 2);
		}
	}

}
