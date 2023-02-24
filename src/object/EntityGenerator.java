package object;

import entity.Entity;
import entity.NPC_Boulder;
import entity.NPC_Fountain;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import entity.NPC_SaveBeacon;
import entity.NPC_Torch;
import main.GamePanel;
import monster.MON_BlueSlime;
import monster.MON_GreenSlime;
import monster.MON_OrangeSlime;
import monster.MON_Orc;
import monster.MON_SkeletonLord;
import monster.MON_Zombie;

public class EntityGenerator {
	
	GamePanel gp;
	
	public EntityGenerator (GamePanel gp) {
		this.gp = gp;
		
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		switch(itemName) {
		//equipment type
		case OBJ_Axe.objName 				: obj = new OBJ_Axe(gp); break;
		case OBJ_Pickaxe.objName 			: obj = new OBJ_Pickaxe(gp); break;
		case OBJ_Shield_Blue.objName 		: obj = new OBJ_Shield_Blue(gp); break;
		case OBJ_Shield_Wood.objName 		: obj = new OBJ_Shield_Wood(gp); break;
		case OBJ_Sword_Normal.objName 		: obj = new OBJ_Sword_Normal(gp); break;
		case OBJ_Lantern.objName 			: obj = new OBJ_Lantern(gp); break;
		
		//consumable type
		case OBJ_Coin_Bronze.objName 		: obj = new OBJ_Coin_Bronze(gp); break;
		case OBJ_Boots.objName 				: obj = new OBJ_Boots(gp); break;
		case OBJ_Potion_Red.objName 		: obj = new OBJ_Potion_Red(gp); break;
		case OBJ_Tent.objName 				: obj = new OBJ_Tent(gp); break;
			//KEYS
		case OBJ_KeyIron.objName			: obj = new OBJ_KeyIron(gp); break;
		case OBJ_KeySkull.objName			: obj = new OBJ_KeySkull(gp); break;
		case OBJ_KeyWooden.objName			: obj = new OBJ_KeyWooden(gp); break;
		//Projectile Type
		case OBJ_Fireball.objName 			: obj = new OBJ_Fireball(gp); break;
		case OBJ_Rock.objName 				: obj = new OBJ_Rock(gp); break;
		//PickUp Only type
		case OBJ_Heart.objName 				: obj = new OBJ_Heart(gp); break;
		case OBJ_ManaCrystal.objName 		: obj = new OBJ_ManaCrystal(gp); break;
		//Obstacle type
		case OBJ_DoorWooden.objName 		: obj = new OBJ_DoorWooden(gp); break;
		case OBJ_DoorIron.objName 			: obj = new OBJ_DoorIron(gp); break;
		case OBJ_DoorIron_Puzzle.objName 	: obj = new OBJ_DoorIron_Puzzle(gp); break;
		case OBJ_DoorSkull.objName 			: obj = new OBJ_DoorSkull(gp); break;
		case OBJ_Chest.objName 				: obj = new OBJ_Chest(gp); break;
		}
		return obj;
	}
	
	public Entity getMonster(int monsterId) {
		Entity mon = null;
		switch(monsterId) {
		case 1 : mon = new MON_BlueSlime(gp); break;
		case 2 : mon = new MON_GreenSlime(gp); break;
		case 3 : mon = new MON_OrangeSlime(gp); break;
		case 4 : mon = new MON_Orc(gp); break;
		case 5 : mon = new MON_Zombie(gp); break;
		case 6 : mon = new MON_SkeletonLord(gp); break;
		}
		return mon;
	}
	
	public Entity getNPC(int npcId) {
		Entity npc = null;
		switch(npcId) {
		case 1 : npc = new NPC_SaveBeacon(gp); break;
		case 2 : npc = new NPC_Merchant(gp); break;
		case 3 : npc = new NPC_OldMan(gp); break;
		case 4 : npc = new NPC_Torch(gp); break;
		case 5 : npc = new NPC_Fountain(gp); break;
		case 6 : npc = new NPC_Boulder(gp); break;
		}
		return npc;
		}
}
