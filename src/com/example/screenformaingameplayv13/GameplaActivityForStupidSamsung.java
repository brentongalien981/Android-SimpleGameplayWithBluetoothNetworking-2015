package com.example.screenformaingameplayv13;

import com.example.gameplaywithbluetoothnetworking13.*;
import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.gameplaywithbluetoothnetworking13.OurBluetoothNetwork;

public class GameplaActivityForStupidSamsung extends Activity implements Button.OnClickListener {
	
	private Button bP;
	private Button bR;
	private OurBluetoothNetwork ourBluetoothNetwork;
	private BluetoothSocket socket;
	private CommunicationManagerForStupidSamsung communicationManagerForStupidSamsung;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameplay_for_stupid_samsung);
		
		setControlButtons();
		setSocket();
		setCommunicationManager();
	}
	
	private void setControlButtons() {
		bP = (Button) findViewById(R.id.stupid_buttonPunch);
		bR = (Button) findViewById(R.id.stupid_buttonMoveRight);
		
		bP.setOnClickListener(this);
		bR.setOnClickListener(this);		
	}
	
	private void setSocket() {
		Intent intent = getIntent();
		ourBluetoothNetwork = (OurBluetoothNetwork) intent.getSerializableExtra("socket");
		socket = ourBluetoothNetwork.getBtSocket();
	}
	
	private void setCommunicationManager() {
		communicationManagerForStupidSamsung = new CommunicationManagerForStupidSamsung(socket);		
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stupid_buttonPunch:
			communicationManagerForStupidSamsung.write(GameConstants.PUNCH);
			break;
		case R.id.stupid_buttonMoveRight:
			communicationManagerForStupidSamsung.write(GameConstants.GO_RIGHT);
			break;
		case R.id.stupid_buttonMoveLeft:
			communicationManagerForStupidSamsung.write(GameConstants.GO_LEFT);
			break;
		case R.id.stupid_buttonJump:
			communicationManagerForStupidSamsung.write(GameConstants.JUMP);
			break;
		case R.id.stupid_buttonDuck:
			communicationManagerForStupidSamsung.write(GameConstants.DUCK);
			break;
		case R.id.stupid_buttonHadouken:
			communicationManagerForStupidSamsung.write(GameConstants.HADOUKEN);
			break;
		case R.id.stupid_buttonShouryuken:
			communicationManagerForStupidSamsung.write(GameConstants.SHOURYUKEN);
			break;
		case R.id.stupid_buttonTatsumaki:
			communicationManagerForStupidSamsung.write(GameConstants.TATSUMAKI);
			break;
		case R.id.stupid_buttonKick:
			communicationManagerForStupidSamsung.write(GameConstants.KICK);
			break;
		case R.id.stupid_buttonShield:
			communicationManagerForStupidSamsung.write(GameConstants.SHIELD);
			break;
		}
	}
}
