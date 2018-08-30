package com.example.screenformaingameplayv13;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;

public class CommunicationManager {

	private ConnectedThread mConnectedThread;

	public CommunicationManager(BluetoothSocket socket, Player himTheOpponent) {
		mConnectedThread = new ConnectedThread(socket, himTheOpponent);
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
		
		public ConnectedThread(BluetoothSocket socket, Player himTheOpponent) {
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;
			this.himTheOpponent = himTheOpponent;

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
					himTheOpponent.setAction(action);
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
