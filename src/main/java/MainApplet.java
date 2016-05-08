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
	private int PeopleinsideCirCle;
	
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
		
		background(105,227,241);
        stroke(0);
        fill(107,239,164);
        ellipse(circleX, circleY, 2*radius, 2*radius);
        
        if(!this.mousePressed){
        	PeopleinsideCirCle=0;
        	for(Character character: this.characters){
        		if((character.x-circleX)*(character.x-circleX)+(character.y-circleY)*(character.y-circleY)<=(radius+5)*(radius+5))
        			PeopleinsideCirCle++;
        	}
        	if(PeopleinsideCirCle>0){
        		double theta=2*Math.PI/PeopleinsideCirCle;
        		for(Character character: this.characters){
        			if((character.x-circleX)*(character.x-circleX)+(character.y-circleY)*(character.y-circleY)<=(radius+5)*(radius+5)){
        			character.x=(int)(circleX+radius*Math.cos(theta));
        			character.y=(int)(circleY+radius*Math.sin(theta));
        			theta+=2*Math.PI/PeopleinsideCirCle;
        			}
        		}
        	}
        }
		
        noFill();
		stroke(60, 119, 119);
		strokeWeight(3);
		for(Character character: this.characters){
			for(Character target: character.getTargets())
				if((character.x-circleX)*(character.x-circleX)+(character.y-circleY)*(character.y-circleY)<=(radius+1)*(radius+1)
				    &&(target.x-circleX)*(target.x-circleX)+(target.y-circleY)*(target.y-circleY)<=(radius+1)*(radius+1)) {
					curve(character.x, character.y, character.x, character.y, (character.x+2*circleX)/3, (character.y+2*circleY)/3, (target.x+2*circleX)/3, (target.y+2*circleX)/3);
			        curve(character.x, character.y, (character.x+2*circleX)/3, (character.y+2*circleY)/3, (target.x+2*circleX)/3, (target.y+2*circleX)/3, target.x, target.y);
			        curve((character.x+2*circleX)/3, (character.y+2*circleY)/3, (target.x+2*circleX)/3, (target.y+2*circleX)/3, target.x, target.y, target.x, target.y);
			      //line(character.x, character.y, target.x, target.y);
				}
			        
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
	
	public void reset(){
		int i=0;
		int j=0;
		for(Character character: this.characters){
			character.x=1000-j;
			character.y=50+i;
			if(i==540){
				i=0;
				j+=60;
			}
			else i+=60;
		}
			
	}
	
	public void add(){
		int peopleNum=nodes.size();
		double theta=0;
		for(Character character: this.characters){
			character.x=(int)(circleX+radius*Math.cos(theta));
			character.y=(int)(circleY+radius*Math.sin(theta));
			theta+=2*Math.PI/peopleNum;

		}
		
	}

}
