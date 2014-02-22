package com.example.snale;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
//TODO edit for animal picture to include 'animal_' in front to ensure that this works with all resources in a file
public class Word extends Activity {
	protected static ArrayList<String> animals=new ArrayList<String>();
	protected static ArrayList<String> sortedMals=new ArrayList<String>();
	protected TextView prompt;
	protected static String guess;
	protected static String answer;
	protected static String question;
	protected static int correct=0;
	protected static int total=0;
	protected TextView result;
	private SoundPool pool;
	private int sounds;
	protected ArrayList<Integer> picts=new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pool=new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sounds=pool.load(this, R.raw.ding,1);
		setContentView(R.layout.activity_word);
		prompt=(TextView) findViewById(R.id.Prompt);
		result=(TextView) findViewById(R.id.score);
		setDimensions();
		Scanner s=new Scanner(getResources().openRawResource(R.raw.wordlist));
		while(s.hasNextLine()){
			animals.add(s.nextLine());
		}
		Field[] fields = R.drawable.class.getDeclaredFields();
		Field[] fields2 = new Field[10];
		int yy=0;
		for (int x=0; x<fields.length; x++){
			if (fields[x].toString().indexOf("animal")>-1){
				Field q=fields[x];
				fields2[yy]=q;
				yy++;
			}
		}
		
	//	for(int x=0; x<fields2.length; x++){
		//	System.out.println(fields2[x]);
		//}
		
		ArrayList<String> stringz=new ArrayList<String>();
		ArrayList<Field> f=new ArrayList<Field>();
		for (int x=0; x<fields.length; x++){
			f.add(fields[x]);
			String ss=fields[x].toString();
			ss=ss.substring(ss.lastIndexOf(".")+1);
			if(ss.indexOf("animal")>-1){
				ss=ss.substring(ss.indexOf("animal_")+7);
			stringz.add(ss);
			}
		}
		for (int x=0; x<10; x++){
			sortedMals.add(animals.get(x).substring(animals.get(x).indexOf(";")+1).toLowerCase());
		}
		
		//System.out.println(stringz);
		//System.out.println(sortedMals);
		for(int x=0; x<fields2.length; x++){
			
			try {
				int y=stringz.indexOf(sortedMals.get(x));
				//System.out.println(sortedMals.get(x));
				//System.out.println(y);
				picts.add((Integer)fields2[y].get(null));
				
			} 
			catch (Exception e) {
			e.printStackTrace();
			} 
		}
	//	System.out.println(sortedMals);
		//System.out.println(picts);
		set();
		//new monitor().start();
		
		
		EditText editText = (EditText) findViewById(R.id.input);

		editText.setOnEditorActionListener(new OnEditorActionListener(){
			public boolean onEditorAction(TextView v, int ID, KeyEvent e) {
				if (ID==EditorInfo.IME_NULL&& (e.getAction()==KeyEvent.KEYCODE_ENTER||e.getAction()==KeyEvent.ACTION_DOWN)){
					//Toast t;
					//t=Toast.makeText(getApplicationContext(), ""+e.getAction()+"   "+KeyEvent.ACTION_DOWN, Toast.LENGTH_SHORT);
					//t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					//t.show();
					check(v);
					return true;
				}
				return false;
			}
		});
		}
	
	private class monitor extends Thread{
		public void run(){
		
			
		boolean t=true;
		EditText edits=(EditText) findViewById(R.id.input);
	while (true){
		if (edits.isFocused()){
			if(t){
			t=false;
			edits.setText("");
			//clear();
			}
		}
		else {
		//	textSet();
			edits.setText("");
			t=true;
			}
		}
		
	}
		
	
	}

	protected void setDimensions(){
		WindowManager mWinMgr = (WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
		int displayWidth = mWinMgr.getDefaultDisplay().getWidth();
		int displayHeight =mWinMgr.getDefaultDisplay().getHeight();
		//System.out.println(displayWidth);
		//System.out.println(displayHeight);
		result=(TextView) findViewById(R.id.score);
		LayoutParams params=result.getLayoutParams();
		params.width=displayWidth;
		result.setLayoutParams(params);
		result.setTextSize(TypedValue.COMPLEX_UNIT_SP, displayHeight*displayWidth/25000);
		result.setGravity(Gravity.CENTER_HORIZONTAL);
		
		prompt=(TextView) findViewById(R.id.Prompt);
		params=prompt.getLayoutParams();
		params.width=displayWidth;
		prompt.setLayoutParams(params);
		prompt.setTextSize(TypedValue.COMPLEX_UNIT_SP, displayHeight*displayWidth/25000);
		prompt.setGravity(Gravity.CENTER_HORIZONTAL);
		
		EditText edits=(EditText) findViewById(R.id.input);
		params=edits.getLayoutParams();
		params.width=displayWidth;
		edits.setLayoutParams(params);
		edits.setText("");
		edits.setGravity(Gravity.CENTER_HORIZONTAL);
		
		Button submit=(Button) findViewById(R.id.submit);
		params=submit.getLayoutParams();
		params.width=displayWidth;
		submit.setLayoutParams(params);
		edits.setGravity(Gravity.CENTER_HORIZONTAL);
		
		ImageView picture=(ImageView) findViewById(R.id.image);
		params=picture.getLayoutParams();
		params.width=(int) (displayWidth*.75);
		params.height=(int) (displayHeight*.30);
		picture.setLayoutParams(params);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	private void set(){
		Random r=new Random();
		question=animals.get(r.nextInt(animals.size()));
		this.prompt.setText(question.substring(0,question.indexOf(";")));
		answer=question.substring(question.indexOf(";")+1).toLowerCase();
		ImageView b=(ImageView) findViewById(R.id.image);
		System.out.println(sortedMals);
		System.out.println(answer);
		b.setImageResource(picts.get(sortedMals.indexOf(answer)));
	}
	
	public void check(View v){
		boolean f=false;
		EditText p=(EditText)findViewById(R.id.input);
		guess=p.getText().toString();
		if (guess.equalsIgnoreCase(answer))
		{correct++;
		f=true;
		 pool.play(sounds, 0.99f, 0.99f, 1, 0, 0.99f);
		}
		total++;
		result.setText(correct+" out of "+total+" Correct So far");
		set();
		p.setText("");
		Toast t;
		if(f){
			t=Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
		}
		else{
			t=Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT);
		}
		t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		t.show();
	}

}
