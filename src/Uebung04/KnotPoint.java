public abstract class KnotPoint {
	// ========== SENSORS ==========
	public static boolean hasObstacle() {
		return kp != null ? kp.hasObstacle() : false;
	}

	public static long battery() {
		return kp != null ? kp.battery() : 0;
	}

	public static boolean binFull() {
		return kp != null ? kp.binFull() : false;
	}

	// ========== ACTUATORS ==========
	public static void move() {
		if (kp != null) {
			kp.move();
		}
	}

	public static void turnClockwise() {
		if (kp != null) {
			kp.turnClockwise();
		}
	}

	public static void moveBack() {
		if (kp != null) {
			kp.moveBack();
		}
	}

	// ========== START HOOVERING ==========
	public static final void main(String[] args) {
		try {
			javakara.JavaKaraProgram.MIN_SLEEP_TIME = 2;
			if (args.length == 1) { // this must be a path to a JavaKara world file
				kp = new JavaKaraProgramToKnotPointProxy();
				kp.run(args[0]);
			} else if (args.length == 5) { // this must be width and height of a new JavaKara world resp. x- and y-position as well as direction of JavaKara
				kp = new JavaKaraProgramToKnotPointProxy(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
				kp.run();
			} else { // just start with the default empty JavaKara world
				kp.run();
			}
		} catch (Throwable t) {
			System.err.println("Something went wrong while starting up...: " + t.getClass().getName());
		}
	}

	// ========== KARA PROXY ==========
	private static JavaKaraProgramToKnotPointProxy kp = new JavaKaraProgramToKnotPointProxy(30, 20, 15, 10, javakara.JavaKaraProgram.JavaKaraWorld.WEST);

	private static final class JavaKaraProgramToKnotPointProxy extends javakara.JavaKaraProgram {
		private int worldWidth, worldHeight, posX, posY, dir;

		private JavaKaraProgramToKnotPointProxy() {
			this(-1, -1, -1, -1, -1);
		}

		private JavaKaraProgramToKnotPointProxy(int worldWidth, int worldHeight, int posX, int posY, int dir) {
			super();
			this.worldWidth = worldWidth;
			this.worldHeight = worldHeight;
			this.posX = posX;
			this.posY = posY;
			this.dir = dir;
		}

		private long batteryCapacity = 269; // batteryCapacity required for GardenAlpha: 269
		private int binCapacity = 18; // binCapacity required for GardenAlpha: 18

		private boolean hasObstacle() {
			return kara.treeFront();
		}

		private long battery() {
			return batteryCapacity;
		}

		private boolean binFull() {
			return binCapacity <= 0;
		}

		private void move() {
			if (batteryCapacity <= 0) {
				throw new ThreadDeath();
			}
			kara.move();
			batteryCapacity--;
			if (kara.onLeaf()) {
				if (binCapacity <= 0) {
					throw new ThreadDeath();
				} else {
					kara.removeLeaf();
					binCapacity--;
				}
			}
		}

		private void turnClockwise() {
			if (batteryCapacity <= 0) {
				throw new ThreadDeath();
			}
			kara.turnRight();
		}

		private void moveBack() {
			if (batteryCapacity <= 0) {
				throw new ThreadDeath();
			}
			kara.turnRight();
			kara.turnRight();
			kara.move();
			batteryCapacity--;
			kara.turnLeft();
			kara.turnLeft();
			if (kara.onLeaf()) {
				if (binCapacity <= 0) {
					throw new ThreadDeath();
				} else {
					kara.removeLeaf();
					binCapacity--;
				}
			}
		}

		@Override
		public void myMainProgram() {
			if (worldWidth != -1) {
				world.setSize(worldWidth, worldHeight);
				kara.setPosition(posX, posY);
				kara.setDirection(dir);
			}
			batteryCapacity = 269; // prepared for GardenAlpha
			binCapacity = 13; // to small for GardenAlpha...
			System.out.println("Pass I: Ready, steady, go! (I feel good: batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + ")");
			PHP.hoover();
			System.out.println("Pass I: Finished with batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + " (should be 0 for GardenAlpha)");
			System.out.println("====================");
			System.out.println("Loading battery - please empty my dust bin...");
			batteryCapacity = 269;
			binCapacity = 1;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			System.out.println("Pass II: Ready, steady, go! (Bin fast full: batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + ")");
			PHP.hoover();
			System.out.println("Pass II: Finished with batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + " (should be 0 for GardenAlpha)");
			System.out.println("====================");
			System.out.println("Loading battery - throwing up dust bin (done it myself)...");
			batteryCapacity = 269;
			binCapacity = 46;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			System.out.println("Pass III: Ready, steady, go! (I feel good: batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + ")");
			PHP.hoover();
			System.out.println("Pass III: Finished with batteryCapacity=" + batteryCapacity + ", binCapacity=" + binCapacity + " (should be 42 for GardenAlpha)");
			System.out.println("====================");
			System.out.println("Going to sleep now... pssst!");
		}
	}
}