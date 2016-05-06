package main.java;

import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private ControlP5 cp5;
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private int radius=300;
	private int circleX=330;
	private int circleY=330;
	
	private final static int width = 1200, height = 650;
	
	
	
	public void setup() {

		size(width, height);
		characters = new ArrayList<Character>();
		loadData();
		smooth();
		cp5 = new ControlP5(this);
		cp5.addButton("reset").setLabel("Reset")
		                      .setPosition(1080,10)
		                      .setSize(100, 50);
		cp5.addButton("add").setLabel("Add All")
                              .setPosition(1080,70)
                              .setSize(100, 50);
		
	}

	public void draw() {
		
		background(255);
        stroke(0);
        fill(255);
        ellipse(circleX, circleY, 2*radius, 2*radius);
		
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
		
		int peopleNum=nodes.size();
		

		for(int i=0; i<peopleNum; i++){
			JSONObject node = nodes.getJSONObject(i);
			double theta=i*2*Math.PI/peopleNum;
			characters.add(new Character(this, node.getString("name"), (int)(circleX+radius*Math.cos(theta)), (int)(circleY+radius*Math.sin(theta))));
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
		

	}

}
