package fallingGrade;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FallingGrade extends JFrame {

	// 배경 이미지 생성
	private Image backGroundImage = new ImageIcon("Data/startPage.png").getImage();

	// 메뉴바 이미지 생성
	private JLabel menuBar = new JLabel(new ImageIcon("Data/NO_USE.png"));
	// 메뉴바 종료 이미지 생성
	private ImageIcon menuBarExitBasicImage = new ImageIcon("Data/exitGame.png");
	private ImageIcon menuBarExitMouseImage = new ImageIcon("Data/exitGameOnMouse.png");

	// 게임시작버튼 이미지 생성
	private ImageIcon startButtonBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/start.png");
	private ImageIcon startButtonMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/startOnMouse.png");

	// 임시종료버튼 이미지 생성
	private ImageIcon exitButtonBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/end.png");
	private ImageIcon exitButtonMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/endOnMouse.png");

	private Image selectPlayerImage = new ImageIcon("Data/selectPlayerPage.png").getImage();
	private boolean selectPlayerPage = false;

	// 캐릭터선택 이미지 생성
	private ImageIcon kibokBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/character1Basic.png");
	private ImageIcon kibokMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/character1Mouse.png");
	private ImageIcon junhyeonBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/character2Basic.png");
	private ImageIcon junhyeonMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/character2Mouse.png");

	// 난이도 선택요구 이미지 생성
	private Image selectDifficultyImage = new ImageIcon("Data/selectDifficultyPage.png").getImage();
	private boolean selectDifficultyPage = false;

	// 난이도선택 이미지 생성
	private ImageIcon easyBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/easy.png");
	private ImageIcon easyMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/easyOnMouse.png");
	private ImageIcon normalBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/normal.png");
	private ImageIcon normalMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/normalOnMouse.png");
	private ImageIcon hardBasicImage = new ImageIcon( // 기본 상태 이미지
			"Data/hard.png");
	private ImageIcon hardMouseImage = new ImageIcon( // 마우스 올려졌을 경우 이미지
			"Data/hardOnMouse.png");

	// 선택캐릭터 이미지 생성
	private Image gameImage = new ImageIcon("Data/playingGamePage.png").getImage();

	//게임 화면창
	public static boolean playingGamePage = false;

	// 게임 결과 창 생성
	private Image clearPageImage = new ImageIcon("Data/clearPage.png").getImage();
	public static boolean gameEndPage = false;
	private Image gameoverImage = new ImageIcon("Data/gameOverPage.png").getImage();
	public static boolean gameoverPage = false;

	// 다시하기 이미지 생성
	private ImageIcon restartImage = new ImageIcon("Data/restart.png"); // 기본 상태 이미지
	private ImageIcon restartOnMouse = new ImageIcon("Data/restartOnMouse.png"); // 마우스 올려졌을 경우 이미지

	// 필요한 버튼 생성
	private JButton menuBarExitButton = new JButton(menuBarExitBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton selectKibokButton = new JButton(kibokBasicImage);
	private JButton selectJunhyeonButton = new JButton(junhyeonBasicImage);
	private JButton easyButton = new JButton(easyBasicImage);
	private JButton normalButton = new JButton(normalBasicImage);
	private JButton hardButton = new JButton(hardBasicImage);
	private JButton restartButton = new JButton(restartImage);

	private int mouseX, mouseY;

	public static Game game;
	public static Player player = new Player();

	public FallingGrade() {
		setUndecorated(true); // 기본 메뉴바 가리기
		setTitle("하늘에서 학점이 떨어져요"); // 프레임 이름 설정
		setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT); // 프레임 크기 설정
		setResizable(false); // 실행 후 프레임 크기 조절 불가
		setLocationRelativeTo(null); // 실행 하면 프레임이 모니터 정 중앙에 출력
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 종료 시 전체 프로그램 종료
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null); // 삽입 위치에 바로 넣게 하는 용도
		addKeyListener(new KeyListener());

		Music intro = new Music("backgroundMusic.mp3", true); // 배경음악 삽입
		intro.start();

		// 메뉴바종료버튼 삽입
		menuBarExitButton.setBounds(1240, 0, 40, 40);
		menuBarExitButton.setBorderPainted(false);
		menuBarExitButton.setContentAreaFilled(false);
		menuBarExitButton.setFocusPainted(false);
		menuBarExitButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				menuBarExitButton.setIcon(menuBarExitMouseImage);
				Music click = new Music("click.mp3", false);
				click.start();
				menuBarExitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {

				menuBarExitButton.setIcon(menuBarExitBasicImage);
				menuBarExitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 프로그램 종료
			public void mousePressed(MouseEvent mouse) {
				System.exit(0);
			}
		});
		add(menuBarExitButton);

		// 메뉴바 삽입 
		menuBar.setBounds(0, 0, 1280, 40);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouse) {
				mouseX = mouse.getX();
				mouseY = mouse.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent mouse) { // 마우스를 드래그 할 때 마다 x, y 좌표를 가져와 JFrame 위치 바꿔주기
				int x = mouse.getXOnScreen();
				int y = mouse.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}

		});
		add(menuBar);

		// 시작버튼 삽입
		startButton.setBounds(250, 450, 227, 94);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				startButton.setIcon(startButtonMouseImage);
				Music click = new Music("click.mp3", false);
				click.start();
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 캐릭터 선택창으로 이동
			public void mousePressed(MouseEvent mouse) {

				// 캐릭터 선택창으로 넘어가기
				startButton.setVisible(false);
				exitButton.setVisible(false);
				selectKibokButton.setVisible(true);
				selectJunhyeonButton.setVisible(true);
				selectPlayerPage = true;

			}
		});
		add(startButton);

		// 종료버튼 삽입
		exitButton.setBounds(803, 450, 227, 94);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				exitButton.setIcon(exitButtonMouseImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 프로그램 종료
			public void mousePressed(MouseEvent mouse) {

				System.exit(0);
			}
		});
		add(exitButton);

		// 기복선택버튼 삽입
		selectKibokButton.setVisible(false);
		selectKibokButton.setBounds(250, 400, 200, 200);
		selectKibokButton.setBorderPainted(false);
		selectKibokButton.setContentAreaFilled(false);
		selectKibokButton.setFocusPainted(false);
		selectKibokButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				selectKibokButton.setIcon(kibokMouseImage);
				selectKibokButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				selectKibokButton.setIcon(kibokBasicImage);
				selectKibokButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 기복캐릭터 선택
			public void mousePressed(MouseEvent mouse) {

				selectKibokButton.setVisible(false);
				selectJunhyeonButton.setVisible(false);
				easyButton.setVisible(true);
				normalButton.setVisible(true);
				hardButton.setVisible(true);

				player.selectedPlayerImage = new ImageIcon("Data/character1Basic.png").getImage();
				selectPlayerPage = false;
				selectDifficultyPage = true; // 난이도선택창 넘어가기
			}
		});
		add(selectKibokButton);

		// 준현선택버튼 삽입
		selectJunhyeonButton.setVisible(false);
		selectJunhyeonButton.setBounds(803, 400, 200, 200);
		selectJunhyeonButton.setBorderPainted(false);
		selectJunhyeonButton.setContentAreaFilled(false);
		selectJunhyeonButton.setFocusPainted(false);
		selectJunhyeonButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				selectJunhyeonButton.setIcon(junhyeonMouseImage);
				selectJunhyeonButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				selectJunhyeonButton.setIcon(junhyeonBasicImage);
				selectJunhyeonButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 준현캐릭터 선택
			public void mousePressed(MouseEvent mouse) {

				selectKibokButton.setVisible(false);
				selectJunhyeonButton.setVisible(false);
				easyButton.setVisible(true);
				normalButton.setVisible(true);
				hardButton.setVisible(true);

				player.selectedPlayerImage = new ImageIcon("Data/character2Basic.png").getImage();
				selectPlayerPage = false;
				selectDifficultyPage = true; // 난이도선택창 넘어가기

			}
		});
		add(selectJunhyeonButton);

		// 임시 쉬움난이도버튼 삽입
		easyButton.setVisible(false);
		easyButton.setBounds(136, 450, 227, 94);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				easyButton.setIcon(easyMouseImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				easyButton.setIcon(easyBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 쉬움난이도 선택
			public void mousePressed(MouseEvent mouse) {

				selectDifficultyPage = false;
				playingGamePage = true;
				easyButton.setVisible(false);
				normalButton.setVisible(false);
				hardButton.setVisible(false);

				game = new Game("EASY");
				game.start();
				startTimer();
			}
		});
		add(easyButton);

		// 임시 보통난이도버튼 삽입
		normalButton.setVisible(false);
		normalButton.setBounds(499, 450, 281, 94);
		normalButton.setBorderPainted(false);
		normalButton.setContentAreaFilled(false);
		normalButton.setFocusPainted(false);
		normalButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				normalButton.setIcon(normalMouseImage);
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				normalButton.setIcon(normalBasicImage);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 보통난이도 선택
			public void mousePressed(MouseEvent mouse) {

				selectDifficultyPage = false;
				playingGamePage = true;
				easyButton.setVisible(false);
				normalButton.setVisible(false);
				hardButton.setVisible(false);

				game = new Game("NORMAL");
				game.start();
				startTimer();
			}
		});
		add(normalButton);

		// 임시 어려움난이도버튼 삽입
		hardButton.setVisible(false);
		hardButton.setBounds(917, 450, 227, 94);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				hardButton.setIcon(hardMouseImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				hardButton.setIcon(hardBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 어려움난이도 선택
			public void mousePressed(MouseEvent mouse) {
				selectDifficultyPage = false;
				playingGamePage = true;
				easyButton.setVisible(false);
				normalButton.setVisible(false);
				hardButton.setVisible(false);

				game = new Game("HARD");
				game.start();
				startTimer();
			}
		});
		add(hardButton);

		// 다시하기버튼 삽입
		restartButton.setVisible(false);
		restartButton.setBorderPainted(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setFocusPainted(false);
		restartButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스 올라갔을때 이미지 바꾸기
			public void mouseEntered(MouseEvent mouse) {
				Music click = new Music("click.mp3", false);
				click.start();
				restartButton.setIcon(restartOnMouse);
				restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼위에 올라갔을 경우 손가락 모양 커서로 변경
			}

			@Override // 마우스 나왔을때 원상태로 복귀
			public void mouseExited(MouseEvent mouse) {
				restartButton.setIcon(restartImage);
				restartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override // 눌렀을 경우 다시시작
			public void mousePressed(MouseEvent mouse) {
				Main.TIME = 31;
				Main.TOTAL_SCORE = 0;
				Main.SCORE = 0;
				Main.GRADE_COUNT = 0;
				Main.FCOUNT = 0;
				Main.GAME_START = true;

				selectPlayerPage = true;
				selectDifficultyPage = false;
				playingGamePage = false;
				gameEndPage = false;
				gameoverPage = false;
				restartButton.setVisible(false);

				selectKibokButton.setVisible(true);
				selectJunhyeonButton.setVisible(true);
				exitButton.setVisible(false);
			}
		});
		add(restartButton);
	}

	public void paint(Graphics g) {
		screenDraw(g);
	}

	public void screenDraw(Graphics g) { // frame 그리기 함수
		g.drawImage(backGroundImage, 0, 0, null);

		if (selectPlayerPage) { // 캐릭터 선택 창인경우
			g.drawImage(selectPlayerImage, 0, 0, null);

		}
		if (selectDifficultyPage) { // 난이도 선택 창인경우 
			g.drawImage(selectDifficultyImage, 0, 0, null);

		}

		if (playingGamePage) { // 게임 실행 창인경우
			g.drawImage(gameImage, 0, 0, null);
			player.screenDraw(g);
			game.screenDraw(g);

		}
		if (gameEndPage) { // 게임 결과 창인경우

			g.drawImage(clearPageImage, 0, 0, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("맑은 고딕", Font.BOLD, 100));
			g.drawString(String.format("%.2f", Main.SCORE), 500, 350);
			restartButton.setBounds(250, 500, 312, 94);
			restartButton.setVisible(true);

			exitButton.setBounds(803, 500, 227, 94);
			exitButton.setVisible(true);

		}

		if (gameoverPage) { // 게임오버 창 인경우
			g.drawImage(gameoverImage, 0, 0, null);
			restartButton.setBounds(250, 500, 312, 94);
			restartButton.setVisible(true);

			exitButton.setBounds(803, 500, 227, 94);
			exitButton.setVisible(true);
		}
		paintComponents(g); // 메뉴바 그리기
		this.repaint();
	}

	public void startTimer() {
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			@Override
			public void run() {
				Main.TIME -= 1;
				if (Main.TIME == 0 || Main.FCOUNT == 3) {
					m_timer.cancel();
					Main.GAME_START = false;
					playingGamePage = false;
					gameEndPage = true;
				}
			}
		};
		m_timer.schedule(m_task, 0, 1000);
	}

}
