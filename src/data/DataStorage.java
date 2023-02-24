package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	
	//playerstats
	int level;
	int maxLife;
	int life;
	int maxMana;
	int mana;
	int strength;
	int dexterity;
	int exp;
	int nextLevelExp;
	int coin;
	int daySpent;
	int currentDayState;
	int currentWorldX;
	int currentWorldY;
	int currentMap;
	// Player Inventory
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmount = new ArrayList<>();

	int currentWeaponSlot;
	int currentShieldSlot;
	
	// OBJECT ON MAP
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
	
	// iTiles On Map
//	String iTileNames[][];
//	int iTileWorldX[][];
//	int itTileWorldY[][];
//	boolean iTileDestroyed[][];
	
}
