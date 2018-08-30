package com.example.screenformaingameplayv13;

public interface GameConstants {
	/* Movement */
	public static final int GO_RIGHT = 99;
	public static final int GO_LEFT= 98;
	public static final int STAND_STILL= 97;
	
	/* Actions */
	public static final int JUMP = 7;
	public static final int DUCK = 8;
	public static final int PUNCH = 2;
	public static final int KICK = 3;
	public static final int SHIELD = 21;
	public static final int HADOUKEN= 22;
	public static final int TATSUMAKI = 23;
	public static final int SHOURYUKEN = 24;
	
	/* These are offended reactions. */
	public static final int GOT_BUMPED = 4;
	
	/* 
	 * These should be put in a different Interface java file, 
	 * probably "GameConstants.java".
	 */
	/* Facing directions */
	public static final int FACING_LEFT = 5;
	public static final int FACING_RIGHT = 6;
	
	public static final int LIFE_BAR_WIDTH = 384;
	public static final int LIFE_BAR_HEIGHT = 30;
	public static final int RHYTHM_BAR_WIDTH = 384;
	public static final int RHYTHM_BAR_HEIGHT = 10;
	
	public static final int PLAYER_VIEW_UPDATE = 80;
	public static final int GAME_STATUS_UPDATE = 81;
	public static final int END_OF_ROUND = 82;
	public static final int GAME_LAYOUT_UPDATE = 83;
	
	public static final int THE_SERVER = 84;
	public static final int THE_CLIENT = 85;
}
