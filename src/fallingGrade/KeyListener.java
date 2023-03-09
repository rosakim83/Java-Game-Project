package fallingGrade;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override // 키보드를 눌렀을때 처리하는 함수
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			FallingGrade.player.pressRight();
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			FallingGrade.player.pressLeft();
		}
	}

	@Override // 키보드를 눌렀다 뗐을때 처리하는 함수
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

		}
	}

}

