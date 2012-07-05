package com.jesusurrutia.bomberchicken;

import java.awt.Image;

class Actor {

	private int x;
	private int y;
	private Image image;

	public Actor(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Image getImage()
	{
		return this.image;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public boolean isLeftCollision(Actor actor)
	{
		return ( ((this.getX() - Commons.SPACE) == actor.getX()) && (this.getY() == actor.getY()) ) ? true : false;
	}

	public boolean isRightCollision(Actor actor)
	{
		return ( ((this.getX() + Commons.SPACE) == actor.getX()) && (this.getY() == actor.getY()) ) ? true : false;
	}

	public boolean isTopCollision(Actor actor)
	{
		return ( (this.getX() == actor.getX()) && ((this.getY() - Commons.SPACE) == actor.getY()) ) ? true : false;
	}

	public boolean isBottomCollision(Actor actor)
	{
		return ( (this.getX() == actor.getX()) && ((this.getY() + Commons.SPACE) == actor.getY()) ) ? true : false;
	}

}