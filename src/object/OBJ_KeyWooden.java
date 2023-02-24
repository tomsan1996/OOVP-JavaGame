package object;




import entity.Entity;
import main.GamePanel;

public class OBJ_KeyWooden extends Entity{
	public static final String objName = "Wooden Key";
	GamePanel gp;

	public OBJ_KeyWooden(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = true;
		down1 = setup ("/objects/keys/woodenKey",gp.tileSize,gp.tileSize);
		down3 = setup ("/objects/keys/woodenKey",gp.tileSize,gp.tileSize);
		description = "["+name+"]\n It Opens a door.";
		price = 1000;
		setDialogue();

	}
	public void setDialogue() {
		dialogues[0][0] = "You use the " + name + " and open the door.";
		dialogues[1][0] = "What are you gonna do with it?";
	}
	public boolean use(Entity entity) {
		startDialogue(this, 0);
		int objIndex = getDetected(entity, gp.obj,"Wooden Door");
		if (objIndex != 999) {
			gp.playSE(49);
			gp.obj[gp.currentMap][objIndex].collision = false;
			gp.obj[gp.currentMap][objIndex].isUnlocked = true;
			gp.obj[gp.currentMap][objIndex].opened = true;
			gp.obj[gp.currentMap][objIndex].down1 = gp.obj[gp.currentMap][objIndex].image2;
			gp.playSE(54);
			return true;
		} else {
			startDialogue(this, 1);
			gp.playSE(29);
			return false;
		}
	}
}
