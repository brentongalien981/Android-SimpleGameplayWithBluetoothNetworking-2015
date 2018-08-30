package com.example.screenformaingameplayv13;

import android.graphics.Bitmap;

public class Player {
	private ThreadManager threadManager;
	private PlayerView pView;
	private PlayerImageManager pImageManager;
	private int facingDirection;
	private int action;
	private int index;
	private Bitmap image;
	
	public Player(PlayerView pView, ThreadManager threadManager, PlayerImageManager pImageManager, int facingDirection) {
		this.pView = pView;
		this.threadManager = threadManager;
		this.pImageManager = pImageManager;
		this.facingDirection = facingDirection;
		this.action = GameConstants.STAND_STILL;
		this.index = 0;
		this.image = null;
	}

	
	public PlayerView getView() {
		return pView;
	}
	
	private int getCurrentIndex() {
		// reset to stance position
		if (index == 6) { 
			index = 0; 
			if (action != GameConstants.STAND_STILL) {
				action = GameConstants.STAND_STILL;
			}
		} 
		
		int currentIndex = index;
		++index;
		return currentIndex;
	}
	
	public void setAction(int action) {
		this.action = action;
		index = 0;
	}
	
	public int getAction() {
		return action;
	}
	
	public void setFacingDirection(int facingDirection) {
		this.facingDirection = facingDirection;
	}
	
	public int getFacingDirection() {
		return facingDirection;
	}
	
	public Bitmap getImage() {
		return image;
	}

	public void update(ImageSequenceIndexCounter pThread) {
		int tempCurrentIndex = getCurrentIndex();
		image = pImageManager.getImage(action, facingDirection, tempCurrentIndex);
		
		threadManager.handleUpdate(GameConstants.PLAYER_VIEW_UPDATE, this);
		
	}
}
