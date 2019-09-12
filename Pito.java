package pito;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Pito - a robot by vinicius t.
 */
public class Pito extends AdvancedRobot
{
	private double enemyEnergy = 100; //energia inicial do robo inimigo
	private int directionToMove = 1; //direcao q o robo ira mover
	private int scanDirection = 1;
	private double distance = 0; //distancia do robo inimigo
	/**
	 * run: Pito's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			/* Faz um scan do campo para encontrar o robo inimigo */
			turnRadarRight(5*scanDirection);
		}
		
		
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 * ira verificar se o robo inimigo vai disparar contra nosso robo
	 * caso verdadeiro nosso robo muda de direcao. Entao mira no inimigo
	 * e atira um projetil com poder variavel de acordo com a distancia
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//sempre fica  a 90 graus do inimigo
		setTurnRight(e.getBearing() + 90 - 30*directionToMove);
		
		//se um inimigo perde 3 de vida ele muda a direcao para desviar dos tiros
		double diferencaEnergia = enemyEnergy - e.getEnergy();
		if(diferencaEnergia > 0 && diferencaEnergia <= 3) {
			directionToMove = -directionToMove;
			ahead((e.getDistance()/4+90)*directionToMove);
		}
		
		scanDirection = -scanDirection;
		turnRadarRight(360*scanDirection);
		
		//mira a arma no inimigo pela distancia mais curta
		//setTurnGunRight(getHeading()-getGunHeading()+e.getBearing());
		
		distance = e.getDistance();
		
		if(distance < 50) {
			if(e.getEnergy() < 12) {
				mira(e.getBearing());
				tiroFatal(e.getEnergy());
				System.out.println(tiroFatal(e.getEnergy()));
			} else {
				mira(e.getBearing());
				fire(2);
				System.out.println(fire(e.getEnergy()));
			}
		} else if (distance < 100) {
			if(e.getEnergy() < 12) {
				mira(e.getBearing());
				tiroFatal(e.getEnergy());
			} else {
				mira(e.getBearing());
				fire(1.5);
			}
		} else if (distance < 200) {
			if(e.getEnergy() < 12) {
				mira(e.getBearing());
				tiroFatal(e.getEnergy());
			} else {
				mira(e.getBearing());
				fire(1);
			}
		} else {
			if(e.getEnergy() < 12) {
				mira(e.getBearing());
				tiroFatal(e.getEnergy());
			} else {
				mira(e.getBearing());
				fire(0.1);
			}
		}
		
		enemyEnergy = e.getEnergy();
	}
	public void tiroFatal(double EnergiaIni) {
		double Tiro = (EnergiaIni / 4) + .1;
		fire(Tiro);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		System.out.println("Acerta de novo ae");
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		directionToMove = -directionToMove;
		ahead(27*directionToMove);
	}	
	
	public void mira(double Adv) {
		double A=getHeading()+Adv-getGunHeading();
		if (!(A > -180 && A <= 180)) {
			while (A <= -180) {
				A += 360;
			}
			while (A > 180) {
				A -= 360;
			}
		}
		turnGunRight(A);
	}
	
	public void dancinha3() { 
		setMaxVelocity(5);
		setTurnGunRight(10000);
		while(true) {
			ahead(20);
			back(20);  
			if (getEnergy() > 0.1) {
				fire(0.1);
			}
		}
	}
}
