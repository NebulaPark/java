package bulletGamePack;

/*--------------------------------------------
 * 프로그램명 : 사격 게임
 * 개발자       : 박성운, 최필종
 * 개발기간    : 2015/12/04 ~ 2015/12/18
 * 사용방법    : 이클립스
 --------------------------------------------*/
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class BulletGame extends JFrame {

	
	JLabel lStatusBar = new JLabel("게임을 시작합니다.. 현재 시간 : " + parseDate());
	JPanel pPointBoard = new JPanel(new GridLayout(0, 1));
	JLabel LUser = new JLabel();
	JLabel lLevel = new JLabel("       레벨");
	String[] target = { "monster", "Chikin", "enemy" };
	ImageIcon[] images = { new ImageIcon("images/monster.png"), new ImageIcon("images/TChickin.jpg"),
			new ImageIcon("images/enemy.jpg") };

	Frame infoFrame;
	GamePanel pGame;
	ColorChooser CC = new ColorChooser();  //컬러선택 클래스 호출//
	Color c;
	String[] sLevel = { "1단계", "2단계", "3단계" }; // Level 설정 콤보박스의 아이템

	JComboBox combo = new JComboBox(sLevel); // Level 설정 콤보박스 생성
	JComboBox setimg = new JComboBox(target); // 타겟 이미지 설정 콤보박스 생성

	void gameFrame() {
		pGame = new GamePanel();  //GamePanel 호출
		pGame.setBackground(c); //게임패널 배경 컬러 설정
		setTitle("BulletGame");  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(1, 1));

		createMenu();
		// 콤보박스에 Action 리스너 등록. 선택된 아이템의 이미지 출력
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox) e.getSource(); // Action 이벤트가 발생한
															// 콤보박스 알아내기
				int index = cb.getSelectedIndex(); // 콤보박스의 선택된 레벨의 인덱스 번호 알아내기
				lStatusBar.setText(sLevel[index]); // 레벨명을 StatusBar에 출력

				// 난이도 설정
				// 타겟의 속도와 총알의 속도를 조절한다.
				if (cb.getSelectedIndex() == 0) {
					pGame.tagetSpeed = 5;
					pGame.bulletSpeed = 5;
					pGame.baseLabel.grabFocus();

				} else if (cb.getSelectedIndex() == 1) {
					pGame.tagetSpeed = 10;
					pGame.bulletSpeed = 10;
					pGame.baseLabel.grabFocus();
				} else if (cb.getSelectedIndex() == 2) {
					pGame.tagetSpeed = 20;
					pGame.bulletSpeed = 20;
					pGame.baseLabel.grabFocus();
				}
			}
		});

		// 이미지 변경
		// 타겟의 이미지를 변경함.
		setimg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox si = (JComboBox) e.getSource();

				int index = si.getSelectedIndex();

				if (si.getSelectedIndex() == 0) {
					pGame.targetLabel.setIcon(images[index]);
					pGame.baseLabel.grabFocus();
				} else if (si.getSelectedIndex() == 1) {
					pGame.targetLabel.setIcon(images[index]);
					pGame.baseLabel.grabFocus();

				} else if (si.getSelectedIndex() == 2) {
					pGame.targetLabel.setIcon(images[index]);
					pGame.baseLabel.grabFocus();
				}

			}

		});

		pPointBoard.add(lLevel);
		pPointBoard.add(combo);
		pPointBoard.add(setimg);
		pPointBoard.add(LUser);
		pPointBoard.add(pGame.Ltaget);
		pPointBoard.add(pGame.Lpoint);
		pPointBoard.add(pGame.LhitPerc);
		pPointBoard.setBackground(Color.white);

		lStatusBar.setForeground(Color.GREEN);

		add(pPointBoard, BorderLayout.WEST);
		add(pGame, BorderLayout.CENTER);
		add(lStatusBar, BorderLayout.SOUTH);

		setSize(600, 400);
		setResizable(false);
		setVisible(true);

		// 화면에 모두 그려지면, 즉 컨텐츠팬을 구성하는 모든 컴포넌트들의 위치와 크기가 결정된 후
		// 게임을 시작하게 한다.
		pGame.startGame();

		// 시간 스레드 시작
		TimeThread time = new TimeThread();
		time.start();

	}

	// 메뉴를 만들어 프레임에 삽입한다.
	void createMenu() {
		JMenuBar mb = new JMenuBar(); // 메뉴바 생성
		JMenuItem[] menuItem = new JMenuItem[4];
		String[] itemTitle = { "새게임", "stop", "restart", "Exit" };
		JMenu screenMenu = new JMenu("BulletGame");
		JMenuItem[] menuSet = new JMenuItem[2];
		String[] itemSetting = { "배경색 변경", "이름 변경" };
		JMenu settingMenu = new JMenu("상태");
		JMenuItem[] miColors = new JMenuItem[7];
		JMenu helpMenu = new JMenu("도움말");

		JMenuItem[] menuHelp = new JMenuItem[1];
		String[] itemHelp = { "조원소개" };

		// 4 개의 메뉴아이템을 Screen 메뉴에 삽입한다.
		MenuActionListener listener = new MenuActionListener(); // Action 리스너 생성

		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]); // 메뉴아이템 생성
			menuItem[i].addActionListener(listener); // 메뉴아이템에 Action 리스너 등록
			screenMenu.add(menuItem[i]); // 메뉴아이템을 Screen 메뉴에 삽입
		}

		for (int i = 0; i < menuHelp.length; i++) {
			menuHelp[i] = new JMenuItem(itemHelp[i]); // 메뉴아이템 생성
			menuHelp[i].addActionListener(listener); // 메뉴아이템에 Action 리스너 등록
			helpMenu.add(menuHelp[i]); // 메뉴아이템을 Screen 메뉴에 삽입
		}

		for (int i = 0; i < menuSet.length; i++) {
			menuSet[i] = new JMenuItem(itemSetting[i]); // 메뉴아이템 생성
			menuSet[i].addActionListener(listener); // 메뉴아이템에 Action 리스너 등록
			settingMenu.add(menuSet[i]); // 메뉴아이템을 Screen 메뉴에 삽입
		}

		// 배경색 변경 창을 클릭하면

		mb.add(screenMenu); // 메뉴바에 Screen 메뉴 삽입
		mb.add(settingMenu);
		mb.add(helpMenu);
		setJMenuBar(mb); // 메뉴바를 프레임에 부착
	}

	class MenuActionListener implements ActionListener { // 메뉴아이템 처리 Action 리스너
		public void actionPerformed(ActionEvent e) {

			String itemPressed = e.getActionCommand();
			String cmd = e.getActionCommand(); // 사용자가 선택한 메뉴아이템의 문자열 리턴

			InputName in = new InputName();
			ColorChooser ch = new ColorChooser();

			switch (cmd) { // 메뉴 아이템의 종류 구분
			case "새게임":
				try {
					dispose();               //기본 창을 닫고 쓰레드 지연시간을 줘서  멈추는것을 막고 인풋을 불러 새 게임을 시작
					pGame.targetThread.sleep(20);
				} catch (InterruptedException e1) {

				}

				in.Input();
				break;

			case "stop":

				GamePanel.IS_PAUSED = true;     //IS_PAUSED가 true면 게임패널의 쓰레드를 잠시 멈춤

				break;
			case "restart":
				GamePanel.IS_PAUSED = false;     //IS_PAUSED가 false면 게임패널의 쓰레드를 다시 시작

				break;
			case "Exit":

				System.exit(0);   //종료
				break;

			case "배경색 변경":
				CC.JColorChooser();     //배경색 변경 선택시 컬러초이스 실행
				break;

			case "이름 변경":

				try {
					dispose();                                       //기본 창을 닫고 쓰레드 지연시간을 줘서  멈추는것을 막고 인풋을 불러 새 게임을 시작
					pGame.targetThread.sleep(20);
				} catch (InterruptedException e1) {

				}

				in.Input();
				break;
			case "조원소개":
				infoshow();   
				break;

			}
		}

	}

	public void infoshow() {
		infoFrame = new Frame("조원 소개");
		infoFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {

				infoFrame.dispose();
			}
		});
		JLabel Fla = new JLabel("조원: 박성운 최필종  " + " [제작기간 :  12월 4일~ 12월 18일] ");

		JLabel la4 = new JLabel(new ImageIcon("images/Chikin.png"));
		ScrollPane scp = new ScrollPane();

		String[] str = { " <프로젝트 작성 및 수정 내역> ", " 12/04  조원 모집, 평가표 및 기본 코드 확인", " 12/04 실행하여 기본적인 기능 점검",
				" 12/05 도움말에 팜업 메뉴 기능 추가", "12/06 난이도 생성 및 난이도별 속도 변경", "12/08 방향키를 이용해 발사대를 좌우로 이동 구현",
				"12/10 메뉴 기능 보완", "12/10 스레드로 발사수, 타격수,  명중률 구현 ", "12/11 타겟이미지 변경기능 추가", "12/13 메뉴 버튼 추가하기", "12/15 시작할때 플레이어 이름 받기 기능 추가",
				"12/16 조원 소개 보완", "12/17 도움말 팜업메뉴에 사진 추가" };

		JLabel[] las = new JLabel[str.length];
		// p는 las를 나타내기 위한 패널
		Panel p = new Panel();

		p.setLayout(new GridLayout(str.length, 1)); // 패널 p 그리드 레이아웃 설정, 길이는
													// str의 길이이다

		// las를 p에 추가
		for (int i = 0; i < las.length; i++) {
			las[i] = new JLabel(str[i]);
			p.add(las[i]);
		}

		scp.add(p);// 스크롤 패널에 패널을 추가
		infoFrame.setLayout(new BorderLayout()); // 순서대로 채워간다.

		infoFrame.add("North", Fla);
		infoFrame.add("Center", scp); // infoFrame 중앙에 추가 추가한다.
		infoFrame.add("South", la4); // infoFrame 남쪽에 la4 추가한다.
		la4.setSize(345, 315); // 사진 사이즈 설정
		infoFrame.pack();
		infoFrame.setSize(500, 800);
		infoFrame.setVisible(true);
	}

	public static String parseDate() {

		long time = System.currentTimeMillis();// time은 시스템 시간을 가져온다
		SimpleDateFormat format = new SimpleDateFormat("HH시 mm분 ss초"); // SimpleDateFormat클래스를
																		// SimpleDateFormat("HH시
																		// mm분
																		// ss초")로
																		// 인스턴스화
																		// 한다.
		Date dd = new Date(time); // Date클래스 인스턴스 생성
		return format.format(dd); // 가져온 시간을 시,분,초를 입력한다.
	}

	class TimeThread extends Thread {
		public void run() {
			while (true) {
				try {
					sleep(1000);//1초마다 시스템 시간을 불러옴
					String time = BulletGame.parseDate();
					
					pGame.setBackground(CC.c); //색상선택 클래스의 컬러 값을 확인하여 적용
					// 스테이터스바에 시간을 변경한다.
					lStatusBar.setText("게임을 시작합니다.. 현재 시간 : " + time);
				} catch (InterruptedException e) {

				}
			}
		}
	}

	
	public static void main(String[] args) {

		InputName ipn = new InputName();
		ipn.Input();

	}
}
