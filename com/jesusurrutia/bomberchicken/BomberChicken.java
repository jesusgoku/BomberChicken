package com.jesusurrutia.bomberchicken;

import javax.swing.JFrame;

public class BomberChicken extends JFrame implements Commons {

	public BomberChicken()
	{
		super(Commons.TITULO);

		Board board = new Board();
		add( board );

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(board.getBoardWidth() + Commons.OFFSET, board.getBoardHeight() + Commons.OFFSET);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] arguments)
	{
		BomberChicken bomberchicken = new BomberChicken();
	}

}