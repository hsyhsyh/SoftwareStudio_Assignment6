package main.java;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		characters = new ArrayList<Character>();
		loadData();
		smooth();
		
	}

	public void draw() {
        background(255);
		
		stroke(60, 119, 119);
		strokeWeight(4);
		for(Character character: this.characters){
			for(Character target: character.getTargets())
				line(character.x, character.y, target.x, target.y);
		}
		
		for(Character character: this.characters)
			character.display();

	}
	
	public void keyPressed(){
		if(keyCode==32)
			setup();
	}

	private void loadData(){
		

	}

}
