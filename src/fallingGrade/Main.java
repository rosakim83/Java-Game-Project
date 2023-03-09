package fallingGrade;

public class Main {
	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 700;
	public static final int SLEEP_TIME = 10;
	public static int TIME = 31;
	public static double TOTAL_SCORE = 0;
	public static double SCORE = 0;
	public static double GRADE_COUNT = 0;
	public static int FCOUNT = 0;
	
	public static boolean GAME_START = true;

	public static void main(String[] args) {
		new FallingGrade();
	}

}
