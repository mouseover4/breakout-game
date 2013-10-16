/**
 * This file creates the the game, Breakout
 * @author Helen Lawrence
 */

import acm.util.RandomGenerator;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


public class Breakout extends Setup {
	/** Instance variables used to move ball */
	
	/** X Velocity */
	private double vx = 4;

	/** Y Velocity */
	private double vy = 4;

	private static final int DELAY = 15;	

	public void init() {
		setSize(APPLET_WIDTH, APPLET_HEIGHT);		
		makeGameBoard();
		addKeyListeners();
	}

	public void run() {		
		setInitialVelocity();
		while (play()) {
			moveBall();
			checkForCollision();
			pause(DELAY);
		}
	}
	
	private void moveBall() {
		if (x > getWidth() - 2 * BALL_RADIUS || (x < 0)) {
			vx = -vx;
		}

		if (y > getHeight() - 2 * BALL_RADIUS || (y < 0)) {
			vy = -vy;
		}
		
		ball.move(vx, vy);
		x += vx;
		y += vy;
	}

	private void setInitialVelocity() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		vx = rgen.nextDouble(1, 6);
		vx = -1;
		if (rgen.nextBoolean() == false) {
			vx = -vx;
		}
	}

	private void checkForCollision() {
		double[] cornerX = { ball.getX(), ball.getX() + ball.getWidth(),
				ball.getX() + ball.getWidth(), ball.getX() };
		double[] cornerY = { ball.getY(), ball.getY(),
				ball.getY() + ball.getHeight(), ball.getY() + ball.getHeight() };
		boolean hit = false;
		for (int i = 0; i < 4; i++) {
			collider = getElementAt(cornerX[i], cornerY[i]);
			if (collider != null) {
				hit = true;
				if (collider != paddle) {
					remove(collider);
					brickRows.remove(brickRows.size()-1);
				}
			}
		}
		if (hit) {
			vy = -vy;
		}
	}
	
	/**
	 * Check if game is over -- returns true if ball goes below the paddle 
	 * or if all bricks have been removed. All of the bricks are stored in 
	 * an ArrayList when the game begins. If the number of bricks in the 
	 * ArrayList is 0, the player wins.
	 */
	private boolean play() {		
		if(ball.getY() > APPLET_HEIGHT - P_OFFSET + P_HEIGHT)
		{		
			gameOver();
			return false;			
		}
		if(brickRows.size()== 0){
			youWin();
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private void gameOver(){
		JOptionPane.showMessageDialog(null,
			    "GAME OVER", "Try Again",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	private void youWin(){
		JOptionPane.showMessageDialog(null,
			    "YOU WIN", "Congratulations",
			    JOptionPane.PLAIN_MESSAGE);
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.move(-10, 0);
			break;
		case KeyEvent.VK_RIGHT:
			paddle.move(10, 0);
			break;
		}
	}

}