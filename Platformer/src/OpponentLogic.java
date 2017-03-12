public class OpponentLogic {

	private PlatformerMap map;
	private Player opponentPlayer;

	private int numShots;

	public OpponentLogic(PlatformerMap map, Player opponentPlayer) {
		this.map = map;
		this.numShots = 0;
		this.opponentPlayer = opponentPlayer;
	}

	public void opponentMove() {
		GameObject closestObject = map.objects().get(0);
		double closestDistance = opponentPlayer.location.distanceTo(closestObject.location);

		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);

			if ((go.equals(opponentPlayer)) || (go instanceof Bullet)) { continue; }

			double distance = opponentPlayer.location.distanceTo(go.location);
			if (distance < closestDistance) {
				closestDistance = distance;
				closestObject = go;
			}
		}

		double closestX = closestObject.location.getX();
		double closestY = closestObject.location.getY();

		double opponentX = opponentPlayer.location.getX();
		double opponentY = opponentPlayer.location.getY();

		double angle = Math.atan2(closestY - opponentY, closestX - opponentX);

		if (closestDistance < 200 && (shouldShoot())) {
			opponentPlayer.aimWeapon(angle);
			opponentPlayer.shoot();
			numShots++;
		} else if (closestDistance > 200) {
			opponentPlayer.speed = 1;
			opponentPlayer.direction = angle;
			opponentPlayer.move();
		}
	}

	private boolean shouldShoot() {
		if (numShots == (int)(Math.random() * 20)) {
			numShots = 0;
			return true;
		}
		return false;
	}

}
