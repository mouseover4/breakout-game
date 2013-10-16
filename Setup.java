/**
 * This file draws the graphics for the game, Breakout
 * @author Helen Lawrence
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.util.ArrayList;

public class Setup extends GraphicsProgram {
	/** Applet width and height */
	public static final int APPLET_WIDTH = 400;
	public static final int APPLET_HEIGHT = 600;

	/** Paddle dimensions and offset */
	public static final int P_WIDTH = 60;
	public static final int P_HEIGHT = 10;
	public static final int P_OFFSET = 30; // gap between bottom of the applet
	// and paddle

	/** Number of brick rows */
	private static final int NROWS = 10;

	/** Number of brick columns */
	private static final int NCOLUMNS = 10;

	/** Brick dimensions and offsets */
	private static final int BRICK_HEIGHT = 8;
	private static final int BRICK_OFFSET = 70; // gap between top of applet and bricks
	private static final int BRICK_GAP = 4; // separation between bricks
	private static final int BRICK_WIDTH = (APPLET_WIDTH / NCOLUMNS) - BRICK_GAP;

	/** Ball dimensions */
	public static final int BALL_RADIUS = 10;
	
	/** Ball coordinates */
	public double x = (APPLET_WIDTH / 2) - BALL_RADIUS;
	public double y = (APPLET_HEIGHT / 2) - BALL_RADIUS;
	
	/** Create an ArrayList to store the bricks */
	ArrayList <GRect> brickRows = new ArrayList<GRect>();
	
	public GRect paddle;
	public GOval ball;
	public GObject collider;
	public GRect brick;

	public void createBricks() {
		for (int i = 0; i < NROWS; i++) {
			for (int j = 0; j < NCOLUMNS; j++) {
				int x = j * (BRICK_WIDTH + BRICK_GAP);
				int y = i * (BRICK_HEIGHT + BRICK_GAP) + BRICK_OFFSET;
				GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				if (i <= 1) {
					rect.setColor(Color.RED);
					rect.setFilled(true);
				} else if (i > 1 && i < 4) {
					rect.setColor(Color.ORANGE);
					rect.setFilled(true);
				} else if (i >= 4 && i < 6) {
					rect.setColor(Color.YELLOW);
					rect.setFilled(true);
				} else if (i >= 6 && i < 8) {
					rect.setColor(Color.GREEN);
					rect.setFilled(true);
				} else {
					rect.setColor(Color.CYAN);
					rect.setFilled(true);
				}
				add(rect);
				this.brick = rect;
				brickRows.add(rect);
			}
		}// end for loop
	}

	public void createPaddle() {
		GRect paddle = new GRect(170, APPLET_HEIGHT - P_OFFSET, P_WIDTH,
				P_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		this.paddle = paddle;
	}

	public void createBall() {
		GOval ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
		this.ball = ball;
	}
	
	public void makeGameBoard(){
		createBricks();
		createPaddle();
		createBall();
	}

}
