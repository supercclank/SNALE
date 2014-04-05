package com.example.snale;

import java.util.ArrayList;
import java.util.Random;

import android.media.AudioManager;
import android.media.MediaPlayer;
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

public class CupGame extends Activity {
	private ArrayList<String> images=new ArrayList<String>();
	private String answer="";
	private String selected="";
	private int total=0;
	private int correct=0;
	private SoundPool pool;
	private int sounds;
	private ArrayList<String> choices=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cup);
		pool=new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
		sounds=pool.load(this, R.raw.ding,1);
		images.add("empty_cup");
		images.add("half_cup");	
		images.add("one_cup");
		images.add("one_and_a_half_cup");
		images.add("two_cup");
		try {
			this.shuffle();
			//
			this.question();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("IllegalArgument");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public void shuffle() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		System.out.println("in shuffle");
		ArrayList<String> temp=new ArrayList<String>();
		temp.addAll(this.images);
		Random gen=new Random();
		choices.clear();
		int x=1;
		while (temp.size()>0){
		String s="imageButton"+x;
		x++;
		String s2=temp.remove(gen.nextInt(temp.size()));
		if (s2.equals("empty_cup"))
			choices.add("0");
		if (s2.equals("half_cup"))
		choices.add("1/2");
		if (s2.equals("one_cup"))
		choices.add("1");
		if (s2.equals("one_and_a_half_cup"))
		choices.add("1 1/2");
		if (s2.equals("two_cup"))
			choices.add("2");
		int y=R.id.class.getField(s).getInt(null);
		Integer y2=(Integer) R.drawable.class.getField(s2).get(null);
		//System.out.println(y);
		Button b=(Button)findViewById(y);	
		b.setBackgroundResource(y2);
		}
	
	}
	
	public void question(){
		TextView q=(TextView) findViewById(R.id.textView3);
		TextView r=(TextView) findViewById(R.id.textView2);
		Random gen=new Random();
		int x=gen.nextInt(5);
		if (x==0) answer="0";
		if (x==1) answer="1/2";
		if (x==2) answer="1";
		if (x==3) answer="1 1/2";
		if (x==4) answer="2";
		q.setText("Select the Cup that shows "+answer+" cups of milk");
		r.setText(correct+" of "+total+" correct");
	}
	
	public void check() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		Toast t;
		if (selected.equals(answer))
			 {t=Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
			 correct++;
			 
			 pool.play(sounds, 0.99f, 0.99f, 1, 0, 0.99f);}
		else 
			t=Toast.makeText(getApplicationContext(), "Sorry, that was wrong, try again", Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		t.show();
		total++;
		this.shuffle();
		this.question();
	}
	
	public void b1(View v) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		selected=choices.get(0);
		this.check();
	}
	
	public void b2(View v) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		selected=choices.get(1);
		this.check();
	}
	
	public void b3(View v) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		selected=choices.get(2);
		this.check();
	}
	
	public void b4(View v) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		selected=choices.get(3);
		this.check();
	}
	
	public void b5(View v) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
		selected=choices.get(4);
		this.check();
	}
	
	private void setDimensions(){
		WindowManager mWinMgr = (WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
		int displayWidth = mWinMgr.getDefaultDisplay().getWidth();
		int displayHeight =mWinMgr.getDefaultDisplay().getHeight();
		LayoutParams params;
		TextView directions=(TextView) findViewById(R.id.textView3);
		TextView score=(TextView) findViewById(R.id.textView2);
		Button button1=(Button) findViewById(R.id.imageButton1);
		Button button2=(Button) findViewById(R.id.imageButton2);
		Button button3=(Button) findViewById(R.id.imageButton3);
		Button button4=(Button) findViewById(R.id.imageButton4);
		Button button5=(Button) findViewById(R.id.imageButton5);
		
		params=button1.getLayoutParams();
		params.width=(int) (displayWidth/5);
		params.height=(int)(displayHeight/5);
		button1.setLayoutParams(params);
		
		params=button2.getLayoutParams();
		params.width=(int) (displayWidth/5);
		params.height=(int)(displayHeight/5);
		button2.setLayoutParams(params);
		
		params=button3.getLayoutParams();
		params.width=(int) (displayWidth/5);
		params.height=(int)(displayHeight/5);
		button3.setLayoutParams(params);
		
		params=button4.getLayoutParams();
		params.width=(int) (displayWidth/5);
		params.height=(int)(displayHeight/5);
		button4.setLayoutParams(params);
		
		directions.setTextSize(displayHeight/18);
		
		score.setTextSize(displayHeight/18);

		params=button5.getLayoutParams();
		params.width=(int) (displayWidth/5);
		params.height=(int)(displayHeight/5);
		button1.setLayoutParams(params);
		
		
		System.out.println("width is "+displayWidth);//1280
		System.out.println("height is "+displayHeight);//752
		
	}
	
}

