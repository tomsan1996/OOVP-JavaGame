package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[99];
	FloatControl fc;
	int volumeScale = 1;
	float volume;
	
	public Sound() {
		
		soundURL[0]	= getClass().getResource("/sounds/mainbgm.wav");
		soundURL[1]	= getClass().getResource("/sounds/open.wav");
		soundURL[2]	= getClass().getResource("/sounds/pickup.wav");
		soundURL[3]	= getClass().getResource("/sounds/7.wav");
		soundURL[4]	= getClass().getResource("/sounds/8.wav");
		soundURL[5]	= getClass().getResource("/sounds/powerup.wav");
		soundURL[6]	= getClass().getResource("/sounds/takeHit.wav");
		soundURL[7]	= getClass().getResource("/sounds/swish.wav");
		soundURL[8]	= getClass().getResource("/sounds/fin.wav");
		soundURL[9]	= getClass().getResource("/sounds/hitmonster.wav");
		soundURL[10]	= getClass().getResource("/sounds/receivedamage.wav");
		soundURL[11]	= getClass().getResource("/sounds/swing.wav");
		soundURL[12]	= getClass().getResource("/sounds/doorOpen.wav");
		soundURL[13]	= getClass().getResource("/sounds/swing3.wav");
		soundURL[14]	= getClass().getResource("/sounds/burning.wav");
		soundURL[15]	= getClass().getResource("/sounds/cuttree.wav");
		soundURL[16]	= getClass().getResource("/sounds/gameover.wav");
		soundURL[17]	= getClass().getResource("/sounds/footStep.wav");
		soundURL[18]	= getClass().getResource("/sounds/swing.wav");
		soundURL[19]	= getClass().getResource("/sounds/levelup.wav");
		soundURL[20]	= getClass().getResource("/sounds/cursor.wav");
		soundURL[21]	= getClass().getResource("/sounds/burning.wav");
		soundURL[22]	= getClass().getResource("/sounds/cuttree.wav");
		soundURL[23]	= getClass().getResource("/sounds/gameover.wav");
		soundURL[24]	= getClass().getResource("/sounds/receivedamage.wav");
		
		soundURL[25]	= getClass().getResource("/sounds/cue/backSelection.wav");
		soundURL[26]	= getClass().getResource("/sounds/cue/buyItem.wav");
		soundURL[27]	= getClass().getResource("/sounds/cue/drawSword.wav");
		soundURL[28]	= getClass().getResource("/sounds/cue/equip.wav");
		soundURL[29]	= getClass().getResource("/sounds/cue/error.wav");
		soundURL[30]	= getClass().getResource("/sounds/cue/healPool.wav");
		soundURL[31]	= getClass().getResource("/sounds/cue/levelUp.wav");
		soundURL[32]	= getClass().getResource("/sounds/cue/openDoor.wav");
		soundURL[33]	= getClass().getResource("/sounds/cue/openDoor2.wav");
		soundURL[34]	= getClass().getResource("/sounds/cue/select.wav");
		soundURL[35]	= getClass().getResource("/sounds/cue/selection.wav");
		soundURL[36]	= getClass().getResource("/sounds/cue/selection (2).wav");
		soundURL[37]	= getClass().getResource("/sounds/cue/sellItem.wav");
		soundURL[38]	= getClass().getResource("/sounds/cue/sleep.wav");
		soundURL[39]	= getClass().getResource("/sounds/cue/talkMerchant.wav");
		soundURL[39]	= getClass().getResource("/sounds/cue/mining.wav");
		soundURL[40]	= getClass().getResource("/sounds/cue/axchop.wav");
		soundURL[41]	= getClass().getResource("/sounds/cue/treethud.wav");
		soundURL[42]	= getClass().getResource("/sounds/cue/boulderBreak.wav");
		soundURL[43]	= getClass().getResource("/sounds/cue/guard.wav");
		soundURL[44]	= getClass().getResource("/sounds/cue/dialogueCharacter.wav");
		soundURL[45]	= getClass().getResource("/sounds/cue/saveGame.wav");
		
		//doors
		soundURL[46]	= getClass().getResource("/sounds/cue/doors/chestClosed.wav");
		soundURL[47]	= getClass().getResource("/sounds/cue/doors/chestOpen.wav");
		soundURL[48]	= getClass().getResource("/sounds/cue/doors/doorLocked.wav");
		soundURL[49]	= getClass().getResource("/sounds/cue/doors/doorUnlocked.wav");
		soundURL[50]	= getClass().getResource("/sounds/cue/doors/ironDoorClose.wav");
		soundURL[51]	= getClass().getResource("/sounds/cue/doors/ironDoorOpen.wav");
		soundURL[52]	= getClass().getResource("/sounds/cue/doors/skullDoorClose.wav");
		soundURL[53]	= getClass().getResource("/sounds/cue/doors/skullDoorOpen.wav");
		soundURL[54]	= getClass().getResource("/sounds/cue/doors/woodDoorClose.wav");
		soundURL[55]	= getClass().getResource("/sounds/cue/doors/woodDoorOpen.wav");
		
		soundURL[56]    = getSound("/sounds/BGM/", "darkCavern");
		soundURL[57]    = getSound("/sounds/BGM/", "decisiveBattle");
		soundURL[58]    = getSound("/sounds/BGM/", "flatlands");
		soundURL[59]    = getSound("/sounds/BGM/", "illOmen");
		soundURL[60]    = getSound("/sounds/BGM/", "mainTheme");
		soundURL[61]    = getSound("/sounds/BGM/", "stolenDreams");
		soundURL[62]    = getSound("/sounds/BGM/", "tension");
		soundURL[63]	= getSound("/sounds/BGM/", "payon");
		
		soundURL[64]	= getSound("/sounds/monsters/slime/", "die");
		soundURL[65]	= getSound("/sounds/monsters/slime/", "move");
		soundURL[66]	= getSound("/sounds/monsters/slime/", "takeDamage");
		
		soundURL[67]	= getSound("/sounds/BGM/", "dotdg");
	}
	
	public URL getSound(String path, String fileName) {
		URL url = getClass().getResource(path+fileName +".wav" );
		return url;
	}
	
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
		}catch(Exception e) {
			
		}
		
	}
	
	public void play() {
		
		clip.start();
		
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() {
		
		clip.stop();
		
	}
	public void checkVolume(){
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f;break;
		case 2: volume = -10f;break;
		case 3: volume = -5f;break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);	
		
	}
	

}