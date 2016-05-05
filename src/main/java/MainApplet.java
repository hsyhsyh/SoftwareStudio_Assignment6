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
        stroke(0);
        fill(255);
        ellipse(300, 300, 500, 500);
		
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
		
		data = loadJSONObject(path + file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");

		System.out.println("Number of nodes: " + nodes.size());
		System.out.println("Number of links: " + links.size());

		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			characters.add(new Character(this, node.getString("name"), random(100, 650), random(100, 650)));
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
		

	}

}
