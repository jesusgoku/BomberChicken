package com.jesusurrutia.bomberchicken;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Chicken extends Actor {

	public Chicken(int x, int y)
	{
		super(x, y);

		URL loc = this.getClass().getResource("images/chicken.png");
		ImageIcon iia = new ImageIcon(loc);
		this.setImage( iia.getImage() );
	}

	public void move(int x, int y)
	{
		int nx = this.getX() + x;
		int ny = this.getY() + y;
		this.setX(nx);
		this.setY(ny);
	}

}