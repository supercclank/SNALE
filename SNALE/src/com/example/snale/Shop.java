package com.example.snale;
//BROKEN COMPLETELY FIX LATER
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Shop extends Activity {
	protected static ArrayList<Integer> buttons = new ArrayList<Integer>();
	protected static ArrayList<Integer> fruit = new ArrayList<Integer>();
	protected static ArrayList<Integer> vegetable = new ArrayList<Integer>();
	protected static ArrayList<Integer> dairy = new ArrayList<Integer>();
	protected static ArrayList<Integer> sauce = new ArrayList<Integer>();
	protected static ArrayList<Integer> spice = new ArrayList<Integer>();
	protected static ArrayList<Integer> fish = new ArrayList<Integer>();
	protected static ArrayList<Integer> bread = new ArrayList<Integer>();
	protected static ArrayList<Integer> dessert = new ArrayList<Integer>();
	protected static ArrayList<Integer> meat = new ArrayList<Integer>();
	protected static ArrayList<Integer> paper = new ArrayList<Integer>();
	protected static Field[] fields;
	protected static ArrayList<Integer> pressed= new ArrayList<Integer>();
	protected static ArrayList<String> sPressed= new ArrayList<String>();
	protected static int total=0;
	protected static int numCorrect=0;
	protected static ArrayList<ImageButton> presses=new ArrayList<ImageButton>();
	private class check extends Thread{
		public void run(){
			while (true){
				if (sPressed.size()==2){
					total++;
					String s1=sPressed.get(0);
					s1=s1.substring(s1.indexOf(" ")+1);
					String s2=sPressed.get(1);
					s2=s2.substring(s2.indexOf(" ")+1);
				if(s1.equals(s2)){
					numCorrect++;
					System.out.println("Both objects are: "+s1);
					sPressed.clear();
					pressed.clear();
				}
				else{
					System.out.println("They are different");

					sPressed.clear();
					pressed.clear();
				}
				Shop.this.runOnUiThread(new Runnable() {
		            @Override
		            public void run() {
				for (int x=0; x<presses.size(); x++){
					presses.get(x).clearColorFilter();
				}}});
				presses.clear();
				setResult();
				}
			}
		}
		

	}
	
	private class populate extends AsyncTask<Void, Void, Void> {
		public Void doInBackground(Void... V){
			 fields = R.drawable.class.getDeclaredFields();
			for (Field current: fields){
				if(java.lang.reflect.Modifier.isStatic(current.getModifiers())){
					if (current.toString().indexOf("fruit")>-1){
						try {
							fruit.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("vegetable")>-1){
						try {
							vegetable.add((Integer)current.get(null));
							String q=current.getName();
							System.out.println(current.get(null));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("dairy")>-1){
						try {
							dairy.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("sauce")>-1){
						try {
							sauce.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("spice")>-1){
						try {
							spice.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("fish")>-1){
						try {
							fish.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("bread")>-1){
						try {
							bread.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("dessert")>-1){
						try {
							dessert.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("meat")>-1){
						try {
							meat.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (current.toString().indexOf("paper")>-1){
						try {
							paper.add((Integer)current.get(null));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
			
			return null;
		}
		
		protected void onPostExecute(Void result){
			ArrayList<Integer> toAdd=new ArrayList<Integer>();
			ArrayList<String> foods=new ArrayList<String>();
			
			
			
			for (int x=1;x<=24;x++){
				toAdd.add(x);
			}
			Random r=new Random();
			for (int x = 1; x <= 24; x++){
				int y=0;
				while(true){
					y=r.nextInt(24)+1;
				if(toAdd.indexOf(y)>-1){
					toAdd.remove(toAdd.indexOf(y));
					break;
				}
				}
				try {
				//	R.id.class.getField("ImageButton" + x).getInt(null);
					ImageButton b=(ImageButton) findViewById((Integer)R.id.class.getField("ImageButton"+y).get(null));
						if(x<=2)
							{int z=r.nextInt(fruit.size());
							b.setImageResource(fruit.get(z));
							b.setTag(fruit.get(z)+" fruit");
							fruit.remove(z);
							}
						if(x>2&&x<=4)
							{int z=r.nextInt(vegetable.size());
							b.setImageResource(vegetable.get(z));
							b.setTag(vegetable.get(z)+" vegetable");
							vegetable.remove(z);
							}
						if(x>4&&x<=6)
							{int z=r.nextInt(dairy.size());
							b.setImageResource(dairy.get(z));
							b.setTag(dairy.get(z)+" dairy");
							dairy.remove(z);
							}
						if(x>6&&x<=8)
							{int z=r.nextInt(sauce.size());
							b.setImageResource(sauce.get(z));
							b.setTag(sauce.get(z)+" sauce");
							sauce.remove(z);
							}
						if(x>8&&x<=10)
							{int z=r.nextInt(spice.size());
							b.setImageResource(spice.get(z));
							b.setTag(spice.get(z)+" spice");
							spice.remove(z);
							}
						if(x>10&&x<=12)
							{int z=r.nextInt(fish.size());
							b.setImageResource(fish.get(z));
							b.setTag(fish.get(z)+" fish");
							fish.remove(z);
							}
						if(x>12&&x<=14)
							{int z=r.nextInt(bread.size());
							b.setImageResource(bread.get(z));
							b.setTag(bread.get(z)+" bread");
							bread.remove(z);
							}
						if(x>14&&x<=16)
							{int z=r.nextInt(dessert.size());
							b.setImageResource(dessert.get(z));
							b.setTag(dessert.get(z)+" dessert");
							dessert.remove(z);
							}
						if(x>16&&x<=18)
							{int z=r.nextInt(meat.size());
							b.setImageResource(meat.get(z));
							b.setTag(meat.get(z)+" meat");
							meat.remove(z);
							}
						if(x>18&&x<=20)
							{int z=r.nextInt(paper.size());
							b.setImageResource(paper.get(z));
							b.setTag(paper.get(z)+" paper");
							paper.remove(z);
							}
						if(x>20)
							{
							foods.add("fruit");
							foods.add("vegetable");
							foods.add("dairy");
							foods.add("sauce");
							foods.add("spice");
							foods.add("fish");
							foods.add("bread");
							foods.add("dessert");
							foods.add("meat");
							foods.add("paper");
							int z=0;
							ArrayList<Integer> o;
							String f;
							while(true){
							z=r.nextInt(foods.size());
							System.out.println(z);
							f=foods.get(z);
							
							System.out.println(Shop.class.getDeclaredField(f));
							o=(ArrayList<Integer>) Shop.class.getDeclaredField(f).get(null);
							System.out.println("Breaks here 1");
							System.out.println(o);
							if (o.size()>0)
							break;
						}
							System.out.println("IN HERE");
							System.out.println();
							z=r.nextInt(o.size());
							b.setImageResource(o.get(z));
							b.setTag(o.get(z)+" "+f);
							o.remove(z);
							
							System.out.println("Breaks here 2");
							}
				
				} catch (Exception e) {
					Log.e("ButtonArrayError", "Error Writing: Image Button" + y);
				}}
		//Arrays.sort(fields);
		}
}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		populate p=new populate();
		p.execute();
		check c=new check();
		c.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void setResult(){
		Shop.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	TextView r=(TextView)findViewById(R.id.Results);
            	r.setText("You have gotten "+numCorrect+" out of "+total+" correct");}});
		}
	
	public void clicked(View V){
		ImageButton b=(ImageButton) V;
		String tag=b.getTag().toString();
		presses.add(b);
		int t=Integer.parseInt(tag.substring(0,tag.indexOf(" ")));
		if (pressed.indexOf(t)<0)
			{b.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			pressed.add(t);
			sPressed.add(tag);
			}
		else
			{b.clearColorFilter();
			presses.remove(presses.indexOf(b));
			pressed.remove(pressed.indexOf(t));
			sPressed.remove(sPressed.indexOf(tag));
			}
		System.out.println(sPressed);
		System.out.println("The following are pressed: "+pressed);
		for(int y=0; y<fields.length; y++){
			try {
				int q=(Integer) fields[y].get(null);
				if(q==t){
				System.out.println("These are the same:");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}


	
}
