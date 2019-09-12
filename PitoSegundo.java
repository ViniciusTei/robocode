package pito;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * PitoSegundo - a robot by (your name here)
 */
public class PitoSegundo extends Robot
{	
	int mudaDirecao = 1;
	
	/**
	 * run: PitoSegundo's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			turnRight(5*mudaDirecao);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		System.out.println("bearing: " + e.getBearing());
		if(e.getBearing() >= 0) {
			mudaDirecao = 1;
		} else {
			mudaDirecao = -1;
		}
		
		turnRight(e.getBearing());
		ahead((e.getDistance() + 5));
		fire(2);
		scan();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
	
	//atropela o mininu e atira nele
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			mudaDirecao = 1;
		} else {
			mudaDirecao = -1;
		}
		turnRight(e.getBearing());
		
		fire(3);
		ahead(40);
	}
}
