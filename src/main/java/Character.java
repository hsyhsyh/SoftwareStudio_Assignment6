package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private PApplet parent;
	public float x, y, radius;
	private String name;
	private ArrayList<Character> targets;

	public Character(PApplet parent, String name, float x, float y){

		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.radius = 25;
		
		// Initialize the target list
		this.targets = new ArrayList<Character>();
		
	}

	public void display(){
		
		this.parent.stroke(0);
		this.parent.fill(255);
		this.parent.ellipse(300, 300, 500, 500);
		
		this.parent.stroke(255);
		this.parent.fill(2, 247, 141);
		this.parent.ellipse(x,y,50,50);
		
		this.parent.textSize(26);
		this.parent.fill(255);
		//this.parent.text(name, x-name.length()*10+5, y+10);
		


	}
	
    public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }
	
}
