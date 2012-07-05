package com.jesusurrutia.bomberchicken;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Board extends JPanel {

	private ArrayList<Chicken> chickens = new ArrayList<Chicken>();
	private ArrayList<Egg> eggs = new ArrayList<Egg>();
	private ArrayList<Baggage> baggages = new ArrayList<Baggage>();
	private int w = 0;
	private int h = 0;

	private String level = 
		  "BBBBBBBBBBBBBBBB\n"
		+ "B  B         G B\n"
		+ "B BBB B BB     B\n"
		+ "B     B  G     B\n"
		+ "B              B\n"
		+ "B              B\n"
		+ "BBBBBBBBBBBBBBBB\n";

	public Board()
	{
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}

	public int getBoardWidth()
	{
		return this.w;
	}

	public int getBoardHeight()
	{
		return this.h;
	}

	public final void initWorld()
	{
		int x = Commons.OFFSET;
		int y = Commons.OFFSET;

		Chicken chicken;
		Egg egg;
		Baggage baggage;

		for(int i = 0; i < level.length(); i++)
		{
			char item = level.charAt(i);

			if(item == '\n')
			{
				y += Commons.SPACE;
				if(this.w < x) this.w = x;
				x = Commons.OFFSET;
			}
			else if(item == Commons.CHAR_CHICKEN)
			{
				chicken = new Chicken(x, y);
				chickens.add(chicken);
				x += Commons.SPACE;
			}
			else if(item == Commons.CHAR_EGG)
			{
				egg = new Egg(x, y);
				eggs.add(egg);
				x += Commons.SPACE;
			}
			else if(item == Commons.CHAR_BAGGAGE)
			{
				baggage = new Baggage(x, y);
				baggages.add(baggage);
				x += Commons.SPACE;
			}
			else if(item == Commons.CHAR_EMPTY)
			{
				x += Commons.SPACE;
			}

			this.h = y;
		}
	}

	public void buildWorld(Graphics g)
	{
		g.setColor( new Color(240, 204, 170) );
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor( new Color(180, 180, 180) );
		g.fillRect(Commons.OFFSET, Commons.OFFSET, this.getBoardWidth() - Commons.OFFSET, this.getBoardHeight() - Commons.OFFSET);
		
		// Divisiones
		g.setColor( Color.white );
		for(int i = Commons.OFFSET; i <= this.getBoardWidth(); i += Commons.SPACE)
			g.drawLine(i, Commons.OFFSET, i, this.getBoardHeight());
		for(int i = Commons.OFFSET; i <= this.getBoardHeight(); i += Commons.SPACE)
			g.drawLine(Commons.OFFSET, i, this.getBoardWidth(), i);

		// Titulo
		g.setColor( Color.red );
		g.setFont( new Font("Tahoma", Font.BOLD + Font.ITALIC , 24) );
		g.drawString("Bomber Chicken", 5, 5 + g.getFontMetrics().getHeight() );

		ArrayList<Actor> world = new ArrayList<Actor>();
		Actor item;
		world.addAll(chickens);
		world.addAll(eggs);
		world.addAll(baggages);

		for(int i = 0; i < world.size(); i++)
		{
			item = (Actor) world.get(i);
			g.drawImage(item.getImage(), item.getX(), item.getY(), this);
		}
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		buildWorld(g);
	}

	class TAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();

			switch(key)
			{
				// Player 1
				case KeyEvent.VK_LEFT:
					if( checkBaggageCollision(chickens.get(0), Commons.LEFT_COLLISION) ) return;
					chickens.get(0).move(-Commons.SPACE, 0);
					break;
				case KeyEvent.VK_RIGHT:
					if( checkBaggageCollision(chickens.get(0), Commons.RIGHT_COLLISION) ) return;
					chickens.get(0).move(Commons.SPACE, 0);
					break;
				case KeyEvent.VK_UP:
					if( checkBaggageCollision(chickens.get(0), Commons.TOP_COLLISION) ) return;
					chickens.get(0).move(0, -Commons.SPACE);
					break;
				case KeyEvent.VK_DOWN:
					if( checkBaggageCollision(chickens.get(0), Commons.BOTTOM_COLLISION) ) return;
					chickens.get(0).move(0, Commons.SPACE);
					break;
				// Player 2
				case KeyEvent.VK_A:
					if( checkBaggageCollision(chickens.get(1), Commons.LEFT_COLLISION) ) return;
					chickens.get(1).move(-Commons.SPACE, 0);
					break;
				case KeyEvent.VK_D:
					if( checkBaggageCollision(chickens.get(1), Commons.RIGHT_COLLISION) ) return;
					chickens.get(1).move(Commons.SPACE, 0);
					break;
				case KeyEvent.VK_W:
					if( checkBaggageCollision(chickens.get(1), Commons.TOP_COLLISION) ) return;
					chickens.get(1).move(0, -Commons.SPACE);
					break;
				case KeyEvent.VK_S:
					if( checkBaggageCollision(chickens.get(1), Commons.BOTTOM_COLLISION) ) return;
					chickens.get(1).move(0, Commons.SPACE);
					break;
				// Reinician Nivel
				case KeyEvent.VK_R:
					restartLevel();
					break;
			}

			repaint();
		}

	}

	private boolean checkBaggageCollision(Actor actor, int type)
	{
		switch(type)
		{
			case Commons.LEFT_COLLISION:
				for(int i = 0; i < baggages.size(); i++)
				{
					Baggage baggage = (Baggage) baggages.get(i);
					if( actor.isLeftCollision(baggage) )
						return true;
				}
				return false;
			case Commons.RIGHT_COLLISION:
				for(int i = 0; i < baggages.size(); i++)
				{
					Baggage baggage = (Baggage) baggages.get(i);
					if( actor.isRightCollision(baggage) )
						return true;
				}
				return false;
			case Commons.TOP_COLLISION:
				for(int i = 0; i < baggages.size(); i++)
				{
					Baggage baggage = (Baggage) baggages.get(i);
					if( actor.isTopCollision(baggage) )
						return true;
				}
				return false;
			case Commons.BOTTOM_COLLISION:
				for(int i = 0; i < baggages.size(); i++)
				{
					Baggage baggage = (Baggage) baggages.get(i);
					if( actor.isBottomCollision(baggage) )
						return true;
				}
				return false;
		}

		return false;
	}

	public void restartLevel()
	{
		chickens.clear();
		eggs.clear();
		baggages.clear();
		initWorld();
	}

}