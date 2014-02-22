package com.example.snale;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
//finish, must bug test
public class SignGame extends Activity {

	private static ArrayList<Field> Signs=new ArrayList<Field>();
	private static ArrayList<Button> Buttons=new ArrayList<Button>();
	private static ArrayList<String> signNames=new ArrayList<String>();
	private static String answer;
	private SoundPool pool;
	private int sounds;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signgame);
		Field[] fields=R.drawable.class.getDeclaredFields();
		for(int x=0; x<fields.length; x++){
			if(fields[x].toString().indexOf("ble.sign")>-1){
					Signs.add(fields[x]);

				} 
			}
		Buttons.add((Button) findViewById(R.id.Button01));
		Buttons.add((Button) findViewById(R.id.Button02));
		Buttons.add((Button) findViewById(R.id.Button03));
		Buttons.add((Button) findViewById(R.id.button1));
		System.out.println(Signs);
		signNames.add("Deer Crossing");
		signNames.add("Stop Light Ahead");
		signNames.add("Playground");
		signNames.add("Rail Road Crossing");
		//signNames.add("Rail Road Crossing");
		signNames.add("Stop Sign");
		signNames.add("No Trucks Allowed");
		signNames.add("Entering Tunnel");
		signNames.add("Windy Road");
		signNames.add("Road Work");
		pool=new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sounds=pool.load(this, R.raw.ding,1);


		//setDimensions();
		shuffle();
		}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	public void check(View v){
		Button b=(Button) v;
		String guess=""+b.getTag();
		Toast t;
		if (answer.indexOf(guess)>-1){
			System.out.println("correct");
			t=Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
			pool.play(sounds, 0.99f, 0.99f, 1, 0, 0.99f);
		}
		else
			t=Toast.makeText(getApplicationContext(), "Sorry, that was wrong, try again", Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		t.show();
		System.out.println("guess was "+guess);
		System.out.println("answer was "+answer);
		shuffle();
	}
	
	public void shuffle(){
		Random gen=new Random();
		ArrayList<Button> buttons=new ArrayList<Button>();
		ArrayList<Field> signs=new ArrayList<Field>();
		ArrayList<String> sn=new ArrayList<String>();
		sn.addAll(signNames);
		signs.addAll(Signs);
		int picked=gen.nextInt(Signs.size());
		String option;
		ImageView v=(ImageView) findViewById(R.id.imageView1);
		
		try {
			v.setImageResource((Integer)Signs.get(picked).get(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		answer=signs.remove(picked).toString();
//		option=answer.substring(answer.indexOf("ble.sign")+8);
//		if (option.indexOf("1")>-1||option.indexOf("2")>-1||option.indexOf("road")>-1){
//			option=option.substring(0,4);	
//			}
//		answer=""+option;
		buttons.addAll(Buttons);
		Button B=buttons.remove(gen.nextInt(buttons.size()));
		//System.out.println(sn.toString());
		B.setText(sn.remove(picked));
		B.setTag(answer);
		while(buttons.size()>0){
			int x=gen.nextInt(sn.size());
			Button BB=buttons.remove(gen.nextInt(buttons.size()));
			option=signs.remove(x).toString();
			option=option.substring(option.indexOf("ble.sign")+8);
			if (option.indexOf("1")>-1||option.indexOf("2")>-1||option.indexOf("road")>-1){
				option=option.substring(0,4);	
				}
			
			BB.setTag(option);
			BB.setText(sn.remove(x));
			
		
		}
	}
	
	private void setDimensions(){
		WindowManager mWinMgr = (WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
		int displayWidth = mWinMgr.getDefaultDisplay().getWidth();
		int displayHeight =mWinMgr.getDefaultDisplay().getHeight();
		LayoutParams params;
		TextView directions=(TextView) findViewById(R.id.textView1);
		Button button1=(Button) findViewById(R.id.Button01);
		Button button2=(Button) findViewById(R.id.Button02);
		Button button3=(Button) findViewById(R.id.Button03);
		Button button4=(Button) findViewById(R.id.button1);
		ImageView prompt=(ImageView) findViewById(R.id.imageView1);
		
		params=button1.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/5);
		button1.setLayoutParams(params);
		
		params=button2.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/5);
		button2.setLayoutParams(params);
		
		params=button3.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/5);
		button3.setLayoutParams(params);
		
		params=button4.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int)(displayHeight/5);
		button4.setLayoutParams(params);
		
		directions.setTextSize(displayHeight/18);
		
		params=prompt.getLayoutParams();
		params.width=(int) (displayWidth/3);
		params.height=(int) (displayHeight/3);
		prompt.setLayoutParams(params);
		
		System.out.println("width is "+displayWidth);//1280
		System.out.println("height is "+displayHeight);//752
		
	}
	
}
