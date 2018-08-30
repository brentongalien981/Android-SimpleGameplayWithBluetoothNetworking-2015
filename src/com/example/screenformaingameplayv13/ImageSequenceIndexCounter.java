package com.example.screenformaingameplayv13;

import android.util.Log;
import android.widget.TextView;

public class ImageSequenceIndexCounter implements Runnable {
	
	private Player p;

	// constructor
	public ImageSequenceIndexCounter(Player p) {
		this.p = p;
	}
	
	@Override
	public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        while (true) {
        	p.update(this);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
        }
     
        
	}

}
