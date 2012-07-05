package com.jesusurrutia.bomberchicken;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

class Egg extends Actor {

	public Egg(int x, int y)
	{
		super(x, y);

		URL loc = this.getClass().getResource("images/egg3.png");
		ImageIcon iia = new ImageIcon(loc);
		this.setImage( iia.getImage() );
	}

}