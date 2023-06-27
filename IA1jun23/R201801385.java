package IA1jun23;

import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * R201801385 - a robot by (your name here)
 */
public class R201801385 extends AdvancedRobot {

	private int direccion = 1;

	private boolean atacando = false;
	private double distanciaAtk = 0;

	public void run() {

		setBodyColor(Color.pink);
		setGunColor(Color.pink);
		setRadarColor(Color.pink);
		setBulletColor(Color.pink);
		setScanColor(Color.pink);

		// Robot main loop
		while (true) {

			// ahead(50);
			// turnGunRight(360);
			// back(100);
			// turnGunRight(360);

			tatakaeee();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		stop();
		fire(1);

		distanciaAtk = e.getDistance();
		setTurnRight(e.getBearing()); // me volteo pa donde esta el otro robot
		atacando = true;
	}

	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like

		double anguloBala = 0;

		if (e.getHeading() >= 0 && e.getHeading() < 180) {
			anguloBala = -90;
		} else {
			anguloBala = 90;
		}

		setTurnRight(anguloBala);
		setAhead(-400);
		waitFor(new TurnCompleteCondition(this));

	}

	public void onHitWall(HitWallEvent e) {

		revertirDireccion();
	}

	public void onHitRobot(HitRobotEvent e) {
		revertirDireccion();
	}

	public void revertirDireccion() {
		// si hay un muro, avanzo en la direccion contraria
		direccion = direccion * -1;
		setAhead(1000 * direccion);
		waitFor(new TurnCompleteCondition(this));
	}

	// MIS METODOS

	public void tatakaeee() {
		// metodo default para ir a la pelea (el titulo es una referencia a SNK)

		// si no estoy atacando a alguien, ando escaneando a quien atacar

		if (atacando) {
			setAhead(distanciaAtk - 150);
			atacando = false;
		} else {
			setTurnGunRight(90);
			setAhead(1500 * direccion);
		}

		waitFor(new TurnCompleteCondition(this));

	}
}
