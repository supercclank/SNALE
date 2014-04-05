package com.example.snale;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author Cory Brzycki
 * @version 1.0
 */
public class ResourceManager {
	private Class[] innerClasses;
	private HashMap<String,Field[]> classes;
	
	/**
	 * Constructor for ResourceManager Object
	 * 				   -gets all of the Declared inner classes from R and puts them in
	 *                  the Array of classes innerClasses
	 *                 -maps each Class contained in innerClasses to classes using the
	 *                  by a String variable representation of the Class names
	 */
	public ResourceManager(){
		innerClasses = R.class.getDeclaredClasses();
		classes = new HashMap<String,Field[]>();
		for (Class c : innerClasses){
			classes.put(c.toString().split("\\$")[1],c.getDeclaredFields());
		}
	}

	/**
	 * @param String resource - the resource type to get (id, style, drawable, ect..)
	 * @return a HashMap that maps all of the fields of the given resource type by
	 * 					 a String variable representation of the variable names
	 */
	public HashMap<String,Field> get(String resource){
		Field[] rawGet = classes.get(resource);
		return (rawGet==null) ? null: map(rawGet,resource);
	}
	
	/**
	 * @param String resource - the resource type to get (id, style, drawable, ect..)
	 * @param String filter - the filter to be used in this get, a filter is a String
	 * 					 that is required to be found in the name of the variable
	 * 					 for it to be mapped
	 * @return a HashMap that maps all of the fields of the given resource type by
	 * 					 a String variable representation of the variable names that
	 * 					 have the filter String
	 */
	public HashMap<String,Field> getWithFilter(String resource, String filter){
		HashMap<String,Field> mappedGet = get(resource);
		return (mappedGet==null) ? null: filter(mappedGet,filter);
	}
	
	/**
	 * @param mappedGet - the HashMap to apply the filter to
	 * @param String filter - the filter to be used in this get, a filter is a String
	 * 					 that is required to be found in the name of the variable
	 * 					 for it to be mapped
	 * @return a HashMap that maps all of the fields of the given resource type by
	 * 					 a String variable representation of the variable names that
	 * 					 have the filter String
	 */
	private HashMap<String,Field> filter(HashMap<String,Field> mappedGet, String filter){
		HashMap<String,Field> filtered = new HashMap<String,Field>();
		for (String s : mappedGet.keySet()){
			if (s.indexOf(filter)>=0){
				String[] hold = s.split(filter+"_*");
				filtered.put(hold[hold.length-1],mappedGet.get(s));
			}
		}
		return (filtered.size()<0) ? null : filtered;
	}
	
	/**
	 * @param Field[] rawGet - raw, unmapped Array of Fields for a given resource type
	 * @param String resource - the resource type to get (id, style, drawable, ect..)
	 * @return a HashMap that maps all of the fields of the given resource type by
	 * 					 a String variable representation of the variable names that
	 * 					 have the filter String
	 */
	private HashMap<String,Field> map(Field[] rawGet, String resource){
		HashMap<String,Field> mapped = new HashMap<String,Field>();
		for (Field f : rawGet){
			mapped.put(f.toString().split("\\$"+resource+".")[1],f);
		}
		return (mapped.size()<0) ? null : mapped;
	}
	
	/**
	 * @return a String representation of the Resources that this ResourceManager
	 *                  is able to manage (the names of the inner classes of R)
	 */
	public String toString(){
		return classes.keySet().toString();
	}
	
}