package fallingGrade;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Grade extends Thread {
	private Image gradeImage;
	private boolean proceeded = true;
	private int x, y = 0;
	private int gradeType, speed;
	// 0 ~ 4: A
	// 5 ~ 9 : B
	// 10 ~ 12 : C
	// 13 ~ 15 : D
	// 16 ~ 19 : F

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	Random generator = new Random();

	public Grade(int gradeType) {
		//학점 이미지 생성
		if (gradeType >= 0 && gradeType <= 4) {
			if (gradeType % 2 != 0) {
				gradeImage = new ImageIcon("Data/gradeAPlus.png").getImage();
			} else
				gradeImage = new ImageIcon("Data/gradeA.png").getImage();

		} else if (gradeType >= 5 && gradeType <= 9) {
			if (gradeType % 2 != 0) {
				gradeImage = new ImageIcon("Data/gradeBPlus.png").getImage();
			} else
				gradeImage = new ImageIcon("Data/gradeB.png").getImage();
		} else if (gradeType >= 10 && gradeType <= 12) {
			if (gradeType % 2 != 0) {
				gradeImage = new ImageIcon("Data/gradeCPlus.png").getImage();
			} else
				gradeImage = new ImageIcon("Data/gradeC.png").getImage();
		} else if (gradeType >= 13 && gradeType <= 15) {
			if (gradeType % 2 != 0) {
				gradeImage = new ImageIcon("Data/gradeDPlus.png").getImage();
			} else
				gradeImage = new ImageIcon("Data/gradeD.png").getImage();
		} else if (gradeType >= 16 && gradeType <= 19) {
			gradeImage = new ImageIcon("Data/gradeF.png").getImage();
		} else if (gradeType == 20) {
			gradeImage = new ImageIcon("Data/timePlus.png").getImage();
		} else {
			gradeImage = new ImageIcon("Data/FMinus.png").getImage();
		}
		this.speed = 2 + generator.nextInt(5);
		this.x = generator.nextInt(1200);
		this.gradeType = gradeType;
	}

	public void screenDraw(Graphics g) {
		g.drawImage(gradeImage, x, y, null);
	}

	public void drop() {
		y += this.speed;
		int playerX = FallingGrade.player.getX();
		Music coin = new Music("coin.mp3", false);
		if ((y >= 410) && (y < 610) && (x >= playerX - 50) && (x <= playerX + 150)) {
			if (gradeType >= 0 && gradeType <= 4) { // A+, A 먹은 경우
				coin.start();
				Main.GRADE_COUNT += 1;
				if (gradeType % 2 != 0) {
					Main.TOTAL_SCORE += 4.5;
				} else
					Main.TOTAL_SCORE += 4;

			} else if (gradeType >= 5 && gradeType <= 9) { // B+, B 먹은 경우
				coin.start();
				Main.GRADE_COUNT += 1;
				if (gradeType % 2 != 0) {
					Main.TOTAL_SCORE += 3.5;
				} else
					Main.TOTAL_SCORE += 3;

			} else if (gradeType >= 10 && gradeType <= 12) { // C+, C 먹은 경우
				coin.start();
				Main.GRADE_COUNT += 1;
				if (gradeType % 2 != 0) {
					Main.TOTAL_SCORE += 2.5;
				} else
					Main.TOTAL_SCORE += 2;

			} else if (gradeType >= 13 && gradeType <= 15) { // D+, D 먹은 경우
				coin.start();
				Main.GRADE_COUNT += 1;
				if (gradeType % 2 != 0) {
					Main.TOTAL_SCORE += 1.5;
				} else
					Main.TOTAL_SCORE += 1;

			} else if (gradeType >= 16 && gradeType <= 19) { // F 먹은 경우
				coin.start();
				Main.FCOUNT += 1;
				if (Main.FCOUNT == 3) {
					Main.GAME_START = false;
					FallingGrade.playingGamePage = false;
					FallingGrade.gameoverPage = true;
				}
			} else if (gradeType == 20) { // 게임 시간 늘리기
				coin.start();
				Main.TIME += 3;
			} else { // F 횟수 줄이기
				coin.start();
				if (Main.FCOUNT > 0) {
					Main.FCOUNT--;
				}
			}
			if (Main.GRADE_COUNT > 0) { // NaN 오류 방지
				Main.SCORE = Main.TOTAL_SCORE / Main.GRADE_COUNT;
			} else {
				Main.SCORE = 0;
			}

			close();
		} else if (y >= 600) {
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					if (!Main.GAME_START) { // 떨어지는 학점이 있는 도중에 게임이 종료 된다면 떨어지고 있는 학점도 멈춰야 한다
						interrupt();
						break;
					}
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
