package com.example.screenformaingameplayv13;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;

public class CommunicationManagerForStupidSamsung {

	private ConnectedThread mConnectedThread;

	public CommunicationManagerForStupidSamsung(BluetoothSocket socket) {
		mConnectedThread = new ConnectedThread(socket);
		mConnectedThread.start();
	}

	public void write(int action) {
		mConnectedThread.write(action);
	}

	// inner thread class
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;
		private Player himTheOpponent;
		
		public ConnectedThread(BluetoothSocket socket) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		public void run() {			
			int action;
			
			while (true) {
				try {
					action = mmInStream.read();
				} 
				catch (IOException e) {break;}
			}
		}

		public void write(int action) {
			try {
				mmOutStream.write(action);
			} catch (IOException e) {
			}
		}

		public void cancel() {
			try {mmSocket.close();}
			catch (IOException e) {}
		}
	}
}
