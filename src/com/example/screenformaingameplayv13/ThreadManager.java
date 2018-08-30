package com.example.screenformaingameplayv13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//import android.app.ActionBar.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.RelativeLayout;

public class ThreadManager {
	Runnable[] runnableArr;

	ThreadPoolExecutor threadPool1, threadPool2;
	// A queue of Runnables
	private BlockingQueue<Runnable> mDecodeWorkQueue;
	private BlockingQueue<Runnable> mDecodeWorkQueue2;
	
	PlayerView player1, player2;

	private static ThreadManager myInstance;
	private Handler mHandler;

	private static final int KEEP_ALIVE_TIME = 1;
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
	private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

	public ThreadManager() {
		myInstance = this;
		setThreadPools();

		mHandler = new Handler(Looper.getMainLooper()) {
			@SuppressLint("NewApi")
			@Override
			public void handleMessage(Message inputMessage) {
                int updateType = inputMessage.what;
                
                if (updateType == GameConstants.PLAYER_VIEW_UPDATE) {  
                	Player p = (Player) inputMessage.obj;
                	PlayerView pView = p.getView();
                	
	                switch (p.getAction()) {	                   
	                    case GameConstants.PUNCH:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;                    
	                    case GameConstants.STAND_STILL:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;
	                    case GameConstants.GO_RIGHT:
	    
	                    	
	                    	/*
	                    	// These 2 lines are for Android 2.3 and earlier.
	                    	pView.getParams().leftMargin = pView.getParams().leftMargin + 10;
	                    	pView.setLayoutParams(pView.getParams());
	                    	*/
	                    	pView.setX(pView.getX() + 10);
	                    	pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;
	                }	
                }
			}
		};
	}
	
	private void setThreadPools() {
		mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();
		mDecodeWorkQueue2 = new LinkedBlockingQueue<Runnable>();

		threadPool1 = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
											NUMBER_OF_CORES, // Max pool size
											KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue);

		threadPool2 = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
											NUMBER_OF_CORES, // Max pool size
											KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue2);
	}
	
	
	public ThreadManager getInstance() {
		return myInstance;
	}
	
	public void executePlayerThreadPools(Player p1, Player p2) {
		threadPool1.execute(new ImageSequenceIndexCounter(p1));
		threadPool2.execute(new ImageSequenceIndexCounter(p2));
	}
	
	public void handleUpdate(int updateType, Player p) {
		Message completeMessage = mHandler.obtainMessage(updateType, p);
        completeMessage.sendToTarget();

	}
}
