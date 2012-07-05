package com.jesusurrutia.bomberchicken;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Baggage extends Actor {
	
	public Baggage(int x, int y)
	{
		super(x, y);

		URL loc = this.getClass().getResource("images/baggage.png");
		ImageIcon iia = new ImageIcon(loc);
		this.setImage( iia.getImage() );
	}

}