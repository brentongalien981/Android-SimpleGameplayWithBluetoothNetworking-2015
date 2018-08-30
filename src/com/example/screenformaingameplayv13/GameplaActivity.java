package com.example.screenformaingameplayv13;

import com.example.gameplaywithbluetoothnetworking13.*;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameplaActivity extends Activity implements Button.OnClickListener {

	private GameplaActivity context;
	private ThreadManager threadManager;
	private Button bP;
	private PlayerImageManager p1ImageManager, p2ImageManager;
	private PlayerView p1View, p2View;
	private RelativeLayout mainLayout;
	private android.widget.RelativeLayout.LayoutParams params, params2;
	private Player p1, p2, meThePlayer, himTheOpponent;
	private TextView tv1;
	private OurBluetoothNetwork ourBluetoothNetwork;
	private BluetoothSocket socket;
	private CommunicationManager communicationManager;
	private int thePlayer;
	private PlayerHealthView p1HealthView, p2HealthView;
	private Button bR;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameplay);
		
		// for debug
		tv1 = (TextView) findViewById(R.id.stupid_textView1);
		
		context = this;
		
		setThreadManager();
		setControlButtons();
		setPlayerImageManagers();
		//setPlayerHealthViews();
		setPlayerViews();
		setLayouts();
		setSocket();
		setPlayers();	
		setCommunicationManager();
		
		threadManager.executePlayerThreadPools(p1, p2);
	}
	
	private void setPlayerHealthViews() {
		p1HealthView = new PlayerHealthView(context, "Ryu");
		p2HealthView = new PlayerHealthView(context, "Mattaba");		
	}
	
	private void setCommunicationManager() {
		communicationManager = new CommunicationManager(socket, himTheOpponent);		
	}

	private void setSocket() {
		Intent intent = getIntent();
		ourBluetoothNetwork = (OurBluetoothNetwork) intent.getSerializableExtra("socket");
		socket = ourBluetoothNetwork.getBtSocket();
		
		thePlayer = intent.getIntExtra("thePlayer", 0);
	}

	private void setPlayers() {
		p1 = new Player(p1View, threadManager.getInstance(), p1ImageManager, GameConstants.FACING_RIGHT);
		p2 = new Player(p2View, threadManager.getInstance(), p2ImageManager, GameConstants.FACING_LEFT);
		
		if (thePlayer == GameConstants.THE_SERVER) {
			meThePlayer = p1;
			himTheOpponent = p2;
		}
		else {
			meThePlayer = p2;
			himTheOpponent = p1;
		}
	}
	
	private void setLayouts() {	
		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);		
		mainLayout.addView(p1View, p1View.getParams());
		mainLayout.addView(p2View, p2View.getParams());	
	}
	
	private void setPlayerViews() {
		p1View = new PlayerView(context);
		p2View = new PlayerView(context);
		
		p1View.setParams(300, 200);
		p2View.setParams(300, 200);
		p2View.getParams().leftMargin = 300;
		//p2View.setLayoutParams(params)
	}
	
	private void setPlayerImageManagers() {
		p1ImageManager = new PlayerImageManager(context);
		p2ImageManager = new PlayerImageManager(context);
		
	}

	private void setControlButtons() {
		bP = (Button) findViewById(R.id.buttonPunch);
		bR = (Button) findViewById(R.id.buttonMoveRight);
		
		bP.setOnClickListener(this);
		bR.setOnClickListener(this);		
	}

	private void setThreadManager() {
		threadManager = new ThreadManager();
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPunch:
			meThePlayer.setAction(GameConstants.PUNCH);
			communicationManager.write(GameConstants.PUNCH);
			break;
		case R.id.buttonMoveRight:
			meThePlayer.setAction(GameConstants.GO_RIGHT);
			communicationManager.write(GameConstants.GO_RIGHT);
			break;
		}	
	}
}