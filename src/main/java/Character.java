package main.java;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character implements MouseMotionListener{
	
	private PApplet parent;
	public float x, y, radius;
	private String name;
	private ArrayList<Character> targets;
	private int mouseCenterX;
	private int mouseCenterY;

	public Character(PApplet parent, String name, float x, float y){
		
		parent.addMouseMotionListener(this);

		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.radius = 25;
		
		// Initialize the target list
		this.targets = new ArrayList<Character>();
		
	}

	public void display(){
		

		
		this.parent.noStroke();
		this.parent.fill(2, 247, 141);
		this.parent.ellipse(x,y,50,50);
		
		this.parent.textSize(26);
		this.parent.fill(255);
		this.parent.text(name, x-name.length()*10+5, y+10);
		


	}
	
    public void addTarget(Character target){ this.targets.add(target); }
	
	public ArrayList<Character> getTargets(){ return this.targets; }
	
	public int getCenterX(int mouseX) {
		// TODO Input mouse X position
		return mouseX;
	}
	
	public int getCenterY(int mouseY) {
		// TODO Input mouse Y position
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.x = this.getCenterX(e.getX());
		this.y = this.getCenterY(e.getY());
		System.out.println("test");
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
