package com.example.snale;
//look into threads/bug/beta test (bit odd with switch to challenge)
import java.util.ArrayList;
import java.util.Random;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * 
 * 
 * 
 * 
 * */
public class Simon extends Activity {
	protected ArrayList<String> symbols=new ArrayList<String>();
	protected ArrayList<String> currentOrder=new ArrayList<String>();
	protected ArrayList<Button> buttons=new ArrayList<Button>();
	protected int position=0;
	protected boolean start=false;
	protected int x=0;
	protected int y=0;
	protected int z=0;
	protected SoundPool pool;
	protected ArrayList<Integer> sounds=new ArrayList<Integer>();
	protected boolean challengeOn=false;
	protected Thread thread2;
	protected Thread thread1;
	protected Thread thread3;
	protected static Simon currentlyRunning;
	protected AsyncTask<Object, Void, Void> task;
	
	/**
	 * 
	 * 
	 * method called on  execution of this activity
	 * adds needed symbols to symbols List
	 * adds buttons to buttons List
	 * constructs SoundPool object with sound info 
	 * adds sounds to sounds List
	 * sets this UI thread as the current thread
	 * calls add()
	 * sets dimensions of the layout
	 * */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simon);
		symbols.add("♪");
		symbols.add("★");
		symbols.add("✤");
		symbols.add("☺");
		buttons.add((Button) findViewById(R.id.redButton));
		buttons.add((Button) findViewById(R.id.blueButton));
		buttons.add((Button) findViewById(R.id.greenButton));
		buttons.add((Button) findViewById(R.id.yellowButton));
		pool=new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sounds.add(new Integer(pool.load(this, R.raw.ding, 1)));
		sounds.add(new Integer(pool.load(this, R.raw.ding, 1)));
		sounds.add(new Integer(pool.load(this, R.raw.ding, 1)));
		sounds.add(new Integer(pool.load(this, R.raw.ding, 1)));
		Simon.currentlyRunning=this;
		add();
		setDimensions();
	}
	
	/**
	 * 
	 * 
	 * default construction of dropdown menu
	 * 
	 * 
	 * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Switch to challenge mode");
		return true;
	}
	
	/**
	 * 
	 * sets the dropdown menu to switch back and forth between challenge mode on and off
	 * 
	 * 
	 * @return returns true if the menu was able to be set
	 * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case 0:
            	String title=""+item.getTitle();
            	if(title.equalsIgnoreCase("Switch to challenge mode")){
	                item.setTitle("Switch challenge mode off");
	                challengeOn=true;
	            	reset();
            		}
            	else{
                    item.setTitle("Switch to challenge mode");
                    challengeOn=false;
                    reset();
                	}
            	System.out.println(task.cancel(true));			
                  return true;
            default:
                  return super.onOptionsItemSelected(item);
        }
    }
    
	/**
	 * 
	 * 
	 * called when the user clicks the green button, checks to see if the next symbol in the pattern is  ★
	 * 
	 * if next symbol is ★ it bumps up position and checks to see if the pattern has been finished
	 * 					if it is finished it calls add()
	 * 					if it is not finished it calls setRed()
	 * 					if the user was wrong it calls reset()
	 * 
	 * */
	public void blueClicked(View v){
		if(currentOrder.get(position).equals("★"))
		{
		pool.play(sounds.get(0), 0.99f, 0.99f, 1, 0, 0.99f);
		position++;
		if(position==currentOrder.size()){
			add();
		}
		else{
			setRed();
		}
		}
		else {reset();}
	}
	

	/**
	 * 
	 * 
	 * called when the user clicks the green button, checks to see if the next symbol in the pattern is  ✤
	 * 
	 * if next symbol is ✤ it bumps up position and checks to see if the pattern has been finished
	 * 					if it is finished it calls add()
	 * 					if it is not finished it calls setRed()
	 * 					if the user was wrong it calls reset()
	 * 
	 * */
	public void greenClicked(View v){
		if(currentOrder.get(position).equals("✤"))
		{
		pool.play(sounds.get(0), 0.99f, 0.99f, 1, 0, 0.99f);
		position++;
		if(position==currentOrder.size()){
			add();
		}
		else{
			setRed();
		}
		}
		else {reset();}
	}

	/**
	 * 
	 * 
	 * called when the user clicks the yellow button, checks to see if the next symbol in the pattern is  ☺
	 * 
	 * if next symbol is ☺ it bumps up position and checks to see if the pattern has been finished
	 * 					if it is finished it calls add()
	 * 					if it is not finished it calls setRed()
	 * 					if the user was wrong it calls reset()
	 * 
	 * */
	public void yellowClicked(View v){
		if(currentOrder.get(position).equals("☺"))
		{
		pool.play(sounds.get(0), 0.99f, 0.99f, 1, 0, 0.99f);
		position++;
		if(position==currentOrder.size()){
			add();
		}
		else{
			setRed();
		}
		}
		else {reset();}
	}

	/**
	 * 
	 * 
	 * called when the user clicks the red button, checks to see if the next symbol in the pattern is  ♪
	 * 
	 * if next symbol is ♪ it bumps up position and checks to see if the pattern has been finished
	 * 					if it is finished it calls add()
	 * 					if it is not finished it calls setRed()
	 * 					if the user was wrong it calls reset()
	 * 
	 * */
	public void redClicked(View v){
		if(currentOrder.get(position).equals("♪"))
		{
		pool.play(sounds.get(0), 0.99f, 0.99f, 1, 0, 0.99f);
		position++;
		if(position==currentOrder.size()){
			add();
		}
			else{
				setRed();
			}
		}
		else {reset();}
	}
	
	/**
	 * 
	 * 
	 * called after the user has successful followed a pattern, adds a new symbol to its end and then calls setfirstRed()
	 * 
	 * 
	 * */
	public void add(){
		Random gen=new Random();
		position=0;
		currentOrder.add(symbols.get(gen.nextInt(symbols.size())));
		if(!challengeOn){
		TextView t=(TextView) findViewById(R.id.textView2);
		t.append(currentOrder.get(currentOrder.size()-1));
		}
		for (y=0; y<buttons.size(); y++){
							 buttons.get(y).setEnabled(false);
						
			}
			setFirstRed();
			task=new Something().execute(new Integer(x), currentOrder, buttons, symbols, pool, sounds);
			
	}
	
	/**
	 * 
	 * 
	 * clears the pattern and replaces it with a new one when the player fails to follow an existant pattern
	 * 
	 * 
	 * */
	public void reset(){
		TextView t=(TextView) findViewById(R.id.textView2);
		t.setText("");
		currentOrder.clear();
		add();
	}
	
	/**
	 * 
	 * 
	 * sets the first symbol in a pattern to red text
	 * called on reset() <when user has failed to follow a pattern>
	 * called on add() to make the first symbol red after the user has followed a pattern completely and needs a new symbol to be appended
	 * */
	public void setRed(){
		if(challengeOn)
			return;
	TextView t=(TextView) findViewById(R.id.textView2);
		String text=""+t.getText();
		String start=text.substring(0,position);
		String want=text.substring(position,1+position);
		String end=text.substring(position+1);
		text=start+"<font color=#cc0029>"+want+"</font>"+end;
		t.setText(Html.fromHtml(text));
	}
	
	/**
	 * 
	 * 
	 * 
	 * called after the user has correctly entered a button that is part of the sequence, sets the next symbol in red text
	 * 
	 * */
	public void setFirstRed(){
		if(challengeOn)
			return;
		TextView t=(TextView) findViewById(R.id.textView2);
		String text=""+t.getText();
		String start=text.substring(0,0);
		String want=text.substring(0,1);
		String end=text.substring(1);
		text=start+"<font color=#cc0029>"+want+"</font>"+end;
		t.setText(Html.fromHtml(text));
	}	
	
	//SWITCH THIS TO USE RELATIVE LAYOUT PARAMS 
	/**
	 * 
	 * 
	 * method that sets the dimensions of the layouy so the application can fit on screens of different sizes
	 * 
	 * TODO change to use relativeLayouParams over LayoutParams
	 * */
	private void setDimensions(){
		WindowManager mWinMgr = (WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
		int displayWidth = mWinMgr.getDefaultDisplay().getWidth();
		int displayHeight =mWinMgr.getDefaultDisplay().getHeight();
		LayoutParams params;
		TextView directions=(TextView) findViewById(R.id.textView2);
		Button blue=(Button) findViewById(R.id.blueButton);
		Button green=(Button) findViewById(R.id.greenButton);
		Button yellow=(Button) findViewById(R.id.yellowButton);
		Button red=(Button) findViewById(R.id.redButton);
		
		params=blue.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/4);
		blue.setLayoutParams(params);
		
		params=green.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/4);
		green.setLayoutParams(params);
		
		params=yellow.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/4);
		yellow.setLayoutParams(params);
		
		params=red.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/4);
		red.setLayoutParams(params);
		
		directions.setTextSize(displayHeight/18);	
		
	}
	
}

/**
 * 
 *@Author Cory Brzycki
 * @version 1.0
 *inner class that deals with cycling through buttons with 'flashing' and playing sounds related to each button 
 * 
 * */
class Something extends AsyncTask<Object, Void, Void>{

	/**
	 * 
	 * @param takes an array of objects in the following order:
	 				int x, ArrayList<String> currentOrder, ArrayList<Button> buttons, ArrayList<String> symbols
	 	 			SoundPool pool, ArrayList<Integer> sounds
	 * method that runs when .execute() is called, does the 'flashing' and plays the sounds
	 * */
	@Override
	protected Void doInBackground(Object... params) {
		int x=((Integer) params[0]).intValue();
		final ArrayList<String> currentOrder=(ArrayList<String>) params[1];
		final ArrayList<Button> buttons=(ArrayList<Button>) params[2];
		final ArrayList<String> symbols=(ArrayList<String>) params[3];
		SoundPool pool=(SoundPool) params[4];
		ArrayList<Integer> sounds= (ArrayList<Integer>) params[5];
		for(x=0; x<currentOrder.size(); x++){
			final int y=x;
			try {
				SystemClock.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			Simon.currentlyRunning.thread1=	new Thread(){
				public void run()
				{buttons.get(symbols.indexOf(currentOrder.get(y))).setPressed(true);
			}
			};
			Simon.currentlyRunning.thread2=new Thread(){
				public void run()
				{
					if(!Simon.currentlyRunning.task.isCancelled())
						buttons.get(symbols.indexOf(currentOrder.get(y))).setPressed(false);
			}
			};
			Simon.currentlyRunning.thread3=new Thread(){
				public void run()
				{
						buttons.get(0).setEnabled(true);
						buttons.get(1).setEnabled(true);
						buttons.get(2).setEnabled(true);
						buttons.get(3).setEnabled(true);}
			};
			
			Simon.currentlyRunning.runOnUiThread(Simon.currentlyRunning.thread1
								);										
						
		pool.play(sounds.get(symbols.indexOf(currentOrder.get(x))), 0.99f, 0.99f, 1, 0, 0.99f);
		try {
			SystemClock.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Simon.currentlyRunning.runOnUiThread(Simon.currentlyRunning.thread2
								);		

		
		try {
			SystemClock.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		
		Simon.currentlyRunning.runOnUiThread(Simon.currentlyRunning.thread3
				);
		return null;
	}
	
	/**
	 * 
	 * 
	 * 'kills' the three theads that were created in doInBackground so that the user can change game mode at any time
	 * 
	 * 
	 * */
	void onCanceled(){
		try {
			Simon.currentlyRunning.thread1.wait();
			Simon.currentlyRunning.thread2.wait();
			Simon.currentlyRunning.thread3.wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//Simon.reset();
		}
	}
}
					
	
