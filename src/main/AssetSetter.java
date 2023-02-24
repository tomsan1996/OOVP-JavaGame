package main;


import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;
import tile_interactive.IT_Stone;




public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	
	
	public void callObject(String itemName, int x, int y,int mapNum, int i) {
		gp.obj[mapNum][i] = gp.eGenerator.getObject(itemName);
		gp.obj[mapNum][i].worldX = gp.tileSize*x;
		gp.obj[mapNum][i].worldY = gp.tileSize*y;
		
	}
	public void callChest(String lootName, int x, int y, int mapNum, int i) {
		gp.obj[mapNum][i] = gp.eGenerator.getObject("Chest");
		gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(lootName));
		gp.obj[mapNum][i].worldX = gp.tileSize*x;
		gp.obj[mapNum][i].worldY = gp.tileSize*y;
	}
	public void callMonster(int monsterId, int x, int y,int mapNum, int i) {
		gp.monster[mapNum][i] = gp.eGenerator.getMonster(monsterId);
		gp.monster[mapNum][i].worldX = gp.tileSize*x;
		gp.monster[mapNum][i].worldY = gp.tileSize*y;
		
	}
	
	public void callNPC(int npcId, int x, int y, int mapNum, int i) {
		gp.npc[mapNum][i] = gp.eGenerator.getNPC(npcId)	;
		gp.npc[mapNum][i].worldX = gp.tileSize*x;
		gp.npc[mapNum][i].worldY = gp.tileSize*y;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		callObject("Wooden Key", 33, 26, mapNum, i++);
		callChest("Wooden Key",17 ,27, mapNum, i++);
//		callChest("Wooden Key",31 ,27, mapNum, i++);
//		callChest("Wooden Key",41 ,18, mapNum, i++);

		mapNum = 1;
		i = 0;
		callChest("Woodcutter's Axe",20 ,27, mapNum, i++);
		callObject("Lantern", 12, 27, mapNum, i++);
		callObject("Boots", 13, 22, mapNum, i++);
		callObject("Wooden Door", 19, 25, mapNum, i++);
		
		mapNum = 2;
		i = 0;
		callObject("Iron Door",11 ,25, mapNum, i++);
		
		
		mapNum = 5;
		i = 0;
		callChest("Miner's Pickaxe", 37, 18, mapNum, i++);
		callChest("Red Potion", 18, 22, mapNum, i++);
		
		callObject("Iron Door",17, 42, mapNum, i++);
		callObject("Iron Key",45, 33, mapNum, i++);
		callObject("Puzzle Iron Door",17, 25, mapNum, i++);
		
		mapNum = 6;
		i = 0;
		
		callChest("Skull Key", 43, 18, mapNum, i++);
	
		callObject("Iron Door", 35, 23, mapNum, i++);
		callObject("Skull Door", 43, 40, mapNum, i++);
//		gp.obj[mapNum][i] = new OBJ_Chest(gp);
//		gp.obj[mapNum][i].setLoot(new OBJ_KeyWooden(gp));
//		gp.obj[mapNum][i].worldX = 41 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Chest(gp);
//		gp.obj[mapNum][i].setLoot(new OBJ_KeyWooden(gp));
//		gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_DoorWooden(gp);
//		gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_DoorIron(gp);
//		gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_DoorSkull(gp);
//		gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
//		i++;
		

//		gp.obj[mapNum][i] = new OBJ_Axe(gp);
//		gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 79 * gp.tileSize;
//		i++;
		
//		gp.obj[mapNum][i] = new OBJ_Key(gp);
//		gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 46 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Key(gp);
//		gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 32 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Door(gp);
//		gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Door(gp);
//		gp.obj[mapNum][i].worldX = 7 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 46 * gp.tileSize;
//		i++;

//		gp.obj[mapNum][i] = new OBJ_Chest(gp);
//		gp.obj[mapNum][i].worldX = 42 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 4 * gp.tileSize;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Boots(gp);
//		gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
//		gp.obj[mapNum][i].worldY = 47 * gp.tileSize;
//		i++;



		
		
	}
	
	public void setNPC() {
		
		// MAP 0 = Overworld_01
		int mapNum = 0;
		int i = 0;
		callNPC(1, 39, 32, mapNum, i++);
		callNPC(3, 38, 30, mapNum, i++);
		
		//MAP 3 = Overworld_02
		mapNum = 3;		
		i = 0;
		callNPC(1, 25, 25, mapNum, i++);
		callNPC(5, 11, 24, mapNum, i++);

		//MAP 5 = Dungeon_02
		mapNum = 5;		
		i = 0;
		callNPC(1, 16, 22, mapNum, i++);
		callNPC(2, 28, 3, mapNum, i++);
		callNPC(6, 13, 29, mapNum, i++);
		callNPC(6, 13, 33, mapNum, i++);
		callNPC(6, 20, 35, mapNum, i++);
		callNPC(6, 21, 38, mapNum, i++);
		
	}
	
	

	
	public void setMonster() {
		
		//OVERWORLD 01
		int mapNum = 0;
		int i = 0;
		callMonster(1, 31, 33, mapNum, i++);
		callMonster(1, 32, 32, mapNum, i++);
		callMonster(2, 31, 32, mapNum, i++);
		callMonster(2, 32, 33, mapNum, i++);
		callMonster(3, 33, 34, mapNum, i++);
		callMonster(1, 33, 14, mapNum, i++);
		callMonster(1, 32, 15, mapNum, i++);
		callMonster(2, 31, 13, mapNum, i++);
		callMonster(2, 33, 12, mapNum, i++);
		callMonster(3, 33, 15, mapNum, i++);
		
		//OVERWORLD 03
		mapNum = 3;
		i = 0;
		callMonster(1, 21, 36, mapNum, i++);
		callMonster(1, 22, 35, mapNum, i++);
		callMonster(2, 23, 33, mapNum, i++);
		callMonster(2, 21, 31, mapNum, i++);
		callMonster(3, 24, 34, mapNum, i++);
		callMonster(1, 37, 20, mapNum, i++);
		callMonster(1, 38, 21, mapNum, i++);
		callMonster(2, 39, 22, mapNum, i++);
		callMonster(2, 39, 20, mapNum, i++);
		callMonster(3, 37, 21, mapNum, i++);
		callMonster(4, 27, 16, mapNum, i++);
		
		mapNum = 5;
		i = 0;
		callMonster(5, 5, 34, mapNum, i++);
		callMonster(5, 35, 19, mapNum, i++);
		callMonster(5, 35, 25, mapNum, i++);
		callMonster(5, 46, 25, mapNum, i++);
		callMonster(5, 45, 33, mapNum, i++);
		callMonster(5, 33, 37, mapNum, i++);
		callMonster(5, 35, 38, mapNum, i++);
		callMonster(5, 13, 35, mapNum, i++);
		callMonster(5, 21, 36, mapNum, i++);
		callMonster(5, 17, 38, mapNum, i++);

		mapNum = 6;
		i = 0;
		callMonster(6, 22, 20, mapNum, i++);

		
	}
	
	public void setInteractiveTile( ) {
		
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 35, 27);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 18, 15);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 18, 14);i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 12);i++;

		gp.iTile[mapNum][i] = new IT_Stone(gp, 18, 16);i++;
		gp.iTile[mapNum][i] = new IT_Stone(gp, 42, 19);i++;
		gp.iTile[mapNum][i] = new IT_Stone(gp, 42, 18);i++;	
		gp.iTile[mapNum][i] = new IT_Stone(gp, 41, 23);i++;
		gp.iTile[mapNum][i] = new IT_Stone(gp, 41, 24);i++;
		gp.iTile[mapNum][i] = new IT_Stone(gp, 42, 27);i++;
		
		
		mapNum = 3;
		i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 20);i++;
		
		mapNum = 5;
		i = 0;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 29, 8);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 42, 25);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 43, 25);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 42, 33);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 43, 33);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 14, 30);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 30);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 14, 32);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 32);i++;
		
	}

}
