package com.example.snale;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//TODO fix food game (then add it in), make this not look awful
public class StartScreen extends Activity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		}
	
	public void executeSimon(View V){
		Intent SimonIntent=new Intent(this, Simon.class);
		startActivity(SimonIntent);
	}
	
	public void executeSign(View V){
		Intent SignIntent=new Intent(this, SignGame.class);
		startActivity(SignIntent);
	}
	
	public void executeCup(View V){
		Intent CupIntent=new Intent(this, CupGame.class);
		startActivity(CupIntent);
	}
	
	public void executeShop(View V){
		Intent ShopIntent=new Intent(this, Shop.class);
		startActivity(ShopIntent);
	}
	
}