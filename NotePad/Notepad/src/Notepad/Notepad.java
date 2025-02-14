/*--------------------------------------------------------------------------------------
 *  JAVA 기말 실습프로젝트 
 *
 *	1. 조원            : 박성운 최필종 정현석 이규용
 *	2. 개발기간      : 12/03 ~12/15
 *	3. 기본기능 소개
 * 	4. 필수기능 소개
 *      5. 선택기능 소개
 *      6. 기타 추가기능 소개 
 *---------------------------------------------------------------------------------------*/
package Notepad;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Notepad implements WindowListener, ActionListener, TextListener { // WindowListener,
	// ActionListener,
	// TextListener
	// 3가지를
	// 가져와서
	// Notepad를
	// 구현한다.
	public static Frame frame; // frame 메소드 생성
	public static Canvas canvas;//캔버스 메소드 생성
	public static TextArea txtArea; // txtArea 메소드 생성
	public static TextField txtField; // txField 메소드 생성
	public static Button bNew, bOpen, bSave; // Button 메소드 생성
	public static String filePath = null; // filePath 문자열 메소드 생성
	public static Panel mPanel; // panel 문자열 메소드 생성
	public static String curFilename = "자바메모장"; // curFileName 메소드 생성 이름은
	// "자바 메모장"
	public static String progName = " - 기말과제"; // ProgName  메소드 생성 이름은
	// "- 기말고사"
	public static boolean editState = false; // editSate의 상태가 false를 나타낸다.

	// for dialog box
	public static Frame mDialogbox; // mDialogbox명으로 Frame객체를 생성한다.
	public static Label mDialogLabel; // mDialogLabel명으로 Label객체를 생성한다.
	public static Button mDialogButton; // mDialogButton명으로 Button객체를 생성한다.


	//
	public MyCanvas MyCan; //MyCan 메소드

	// for replace
	public static Frame mReplaceBox; // mReplaceBox명으로 Frame객체를 생성한다.
	public static TextField mReplaceText01; // mReplaceText01명으로 TextField객체를
	// 생성한다.
	public static TextField mReplaceText02; // mReplaceText02명으로 TextField객체를
	// 생성한다.
	public static Label mReplaceLabel01; // mReplaceLabel01명으로 label객체를 생성한다.
	public static Label mReplaceLabel02; // mReplaceLabel02명으로 label객체를 생성한다.
	public static Label mReplaceLabel03; // mReplaceLabel03명으로 label객체를 생성한다.
	public static Button mReplaceExeButton; // mReplaceExeButton명으로 Button객체를
	// 생성한다.
	public static Button mReplaceCancelButton; // mReplaceCancelButton명으로
	// Button객체를 생성한다.

	// 폰트와 색상 설정하는 함수

	private static String[] strFonts = { "Serif", "SansSerif", "Dialog",
			"DialogInput", "Monospaced" };// 폰트설정 함수
	private static String[] strColors = { "Red", "Green", "Blue", "Cyan",
			"Magenta", "Yellow", "Default" };// 색상 설정 함수

	private MenuBar menuBar; // MenuBar 클래스 생성
	private Menu mFile, mEdit, mForm, mHelp; // Menu(총4개) 클래스 생성
	private Menu vmColor, vmFont; // sub mene for colors and fonts
	private MenuItem miFile01, miFile02, miFile03, miFile04, miFile05; // MenuItem(총5개)
	// 클래스
	// 생성
	private MenuItem miEdit01, miEdit02; // MenuItem(총 2개) 클래스 생성

	// private MenuItem miFont01, miFont02;
	// Array of MenuItems for fonts and colors
	private MenuItem[] miFonts;// 폰트 메뉴
	private MenuItem[] miColors;// 색상 메뉴

	private MenuItem miHelp01, miHelp02;// 도움말 메뉴
	Frame infoFrame;// 도움말 프레임

	public static Label lb1 = new Label("입력된 글자수는 0 입니다"); // 글자수 
	// "입력된 글자수는 0입니다" 라벨
	//
	public static Label lb2 = new Label(parseDate()); //시스템시간 라벨

	public static Label lb3 = new Label("분당 타이핑 속도 : 0 / 분 "); 
	// "분당 타이핑 속도 : 0 / 분 "라벨

	private static MessageBox mb; // 메시지 박스는 mb

	// --------------------------------------------------------
	// 생 성 자
	// --------------------------------------------------------
	Notepad() {
		frame = new Frame(curFilename + progName); // 메모장의 표시창 이름을 curfilename과
		// progname을 합쳐서 출력
		frame.addWindowListener(this);// 프레임에 윈도리스너 연결

		txtArea = new TextArea(); // txtArea를 인스턴스화
		txtArea.setFont(new Font("바탕체", Font.BOLD, 15)); // txtArea는 바탕체 볼드 15크기
		txtArea.setBackground(new Color(237, 220, 240)); // 배경색은 237,220,240의 좌표에
		// 있는 색깔로 지정
		txtArea.addTextListener(this); // txtArea 객체에 TextListener 연결

		txtField = new TextField(); // txtField를 인스턴스화
		txtField.setFont(new Font("굴림체", Font.BOLD, 15)); // txtField는 굴림체 볼드
		// 15크기
		txtField.setBackground(new Color(237, 220, 240)); // 배경색은 237,220,240의
		// 좌표에 있는 색깔로 지정
		txtField.addTextListener(this); // txtField 객체에 TextListener 연결

		bNew = new Button("New"); // bNew버튼명을 New로 지정한다.
		bNew.addActionListener(this); // bNew객체에 ActionListener 연결
		bOpen = new Button("Open"); // bOpen버튼명을 Open로 지정한다.
		bOpen.addActionListener(this); // bOpen객체에 ActionListener 연결
		bSave = new Button("Save"); // bSave버튼명을 Save로 지정한다.
		bSave.addActionListener(this); // bSave객체에 ActionListener 연결

		mPanel = new Panel(); // 패널 한 개를 생성한다.
		mPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout을 사용해
		// 컴포넌트를 왼쪽에 배치한다.
		mPanel.add(bNew); // 생성된 패널에 bNew버튼 객체를 추가한다.
		mPanel.add(bOpen); // 생성된 패널에 bOpen버튼 객체를 추가한다.
		mPanel.add(bSave); // 생성된 패널에 bSave버튼 객체를 추가한다.

		mPanel.add(lb1); // mPanel객체에 lb1를 추가한다.
		mPanel.add(lb3); // mPanel객체에 lb3를 추가한다.
		mPanel.add(lb2); // mPanel객체에 lb2를 추가한다.

		menuBar = new MenuBar(); // 메뉴바 인스턴스화
		//파일,편집,서식,도움말 메뉴 생성
		mFile = new Menu("파일(F)");
		mEdit = new Menu("편집(E)");
		mForm = new Menu("서식(O)");
		mHelp = new Menu("도움말(H)");

		vmColor = new Menu("색상 "); // 컬러 메뉴 생성
		vmFont = new Menu("글꼴");//글꼴 메뉴 생성

		miFile01 = new MenuItem("새로 만들기(N)", new MenuShortcut(KeyEvent.VK_N)); //객체를 키 이벤트로 다시 인스턴스화해서
		miFile01.addActionListener(this);                                       //  5개의 키 이벤트를
		miFile02 = new MenuItem("열 기(O)...", new MenuShortcut(KeyEvent.VK_O)); // 설정해준다.
		miFile02.addActionListener(this);
		miFile03 = new MenuItem("저 장(S)", new MenuShortcut(KeyEvent.VK_S));
		miFile03.addActionListener(this);
		miFile04 = new MenuItem("다른 이름으로 저장(A)", new MenuShortcut(KeyEvent.VK_A));
		miFile04.addActionListener(this);
		miFile05 = new MenuItem("끝내기(X)", new MenuShortcut(KeyEvent.VK_X));
		miFile05.addActionListener(this);

		miFonts = new MenuItem[strFonts.length]; // / miFonts객체에 길이를 지정해주는
		for (int i = 0; i < miFonts.length; i++) { // i를 지정해서
			miFonts[i] = new MenuItem(strFonts[i]); // miFonts[i] 클래스 인스턴스 생성
			miFonts[i].addActionListener(this); // miFonts[i] 객체에 ActionListener
			// 연결
			vmFont.add(miFonts[i]); // vmFont객체에 miFonts[i]를 추가한다. 
		}

		miColors = new MenuItem[strColors.length]; // miColors객체에 길이를 지정해주는
		for (int i = 0; i < miColors.length; i++) { // i를 지정해서
			miColors[i] = new MenuItem(strColors[i]); // miColors[i] 클래스 인스턴스 생성
			miColors[i].addActionListener(this); // miFonts[i] 객체에
			// ActionListener 연결
			vmColor.add(miColors[i]); // vmColor객체에 miColors[i]를 추가한다. 
		}

		miEdit01 = new MenuItem("바꾸기"); // 편집에 바꾸기의 객체를 추가
		miEdit01.addActionListener(this); // miEdit01의 액션 처리

		miHelp01 = new MenuItem("메모장 정보(A)"); // 도움말에 메모장정보의 객체를 추가
		miHelp01.addActionListener(this); // miHelp01의 액션 처리

		mFile.add(miFile01); // File(파일)에 메뉴바 5개 추가시킴.
		mFile.add(miFile02);
		mFile.add(miFile03);
		mFile.add(miFile04);
		mFile.addSeparator(); // 구분선을 지어준다.
		mFile.add(miFile05);

		mEdit.add(miEdit01); // Edit(편집)에 1개 추가

		mForm.add(vmFont); // From(서식)에 Font 추가
		mForm.add(vmColor); // From(서식)에 Color 추가

		mHelp.add(miHelp01); // Help(도움말)에 1개 추가

		menuBar.add(mFile); // 메뉴바에다가 File(파일), Edit(편집), Form(서식), Help(도움말)
		// 4개추가
		menuBar.add(mEdit);
		menuBar.add(mForm);
		menuBar.add(mHelp);

		MyCan = new MyCanvas();

		frame.setMenuBar(menuBar); // Frame에 메뉴바(menuBar)를 출력
		frame.add(mPanel, BorderLayout.NORTH); // mPanel를 위쪽에 둔다.
		frame.add(txtArea, BorderLayout.CENTER); // txtArea를 중앙에 놓고
		frame.add(MyCan, BorderLayout.EAST);
		frame.add(txtField, BorderLayout.SOUTH); // txtField는 맨 밑에다가 둔다.

		frame.setSize(800, 500); // frame창의 사이즈는 가로800 세로500
		frame.setVisible(true);// 프레임을 보이도록 설정
	}

	// ========================================================
	// 이 벤 트 핸 들 러 모 음
	// ========================================================

	// --------------------------------------------------------
	// 윈 도 우 이 벤 트 핸 들 러
	// --------------------------------------------------------
	public void windowClosing(WindowEvent we) { // 윈도우가 닫힐때의 액션 즉 창이 닫힐때 발생하는
		// 이벤트
		if (we.getSource() == frame) { // 윈도우 이벤트 소스는 프레임과 같다
			frame.setVisible(false); // Frame을 화면에서 보이지 않도록 한다.
			frame.dispose(); // 메모리에서 제거한다.
			System.exit(0); // 프로그램을 종료하다.


		} else if (we.getSource() == mDialogbox) {//윈도우 이벤트에 소스와 mDialogbox가 같다묜
			mDialogbox.setVisible(false);//안보이도록 설정
			mDialogbox.disable();
		} else if (we.getSource() == mReplaceBox) {//윈도우 이벤트에 소스와 mReplaceBox가 같다면
			mReplaceBox.setVisible(false);//안보이도록 설정
			mReplaceBox.disable();

		}

	}

	public void KeyListner(KeyEvent we) { //  키 이벤트 

	}

	public void windowOpened(WindowEvent we) { // 윈도우를 여는 이벤트
	}

	public void windowIconified(WindowEvent we) { // 윈도우 아이콘 활성화 이벤트
	}

	public void windowDeiconified(WindowEvent we) { //윈도우 아이콘 비활성화 이벤트
	}

	public void windowClosed(WindowEvent we) { // window를 닫는 이벤트
	}

	public void windowActivated(WindowEvent we) { // 윈도우 상태 이벤트
	}

	public void windowDeactivated(WindowEvent we) { // 윈도우 감지이벤트
	}

	// --------------------------------------------------------
	// --------------------------------------------------------
	// 텍 스 트 이 벤 트 핸 들 러
	// --------------------------------------------------------
	public void textValueChanged(TextEvent e) { // 텍스트의 값이 변경되면 불려 갑니다. 이 메소드에
		// 기술된 코드는, 텍스트가 변경될 때 필요한
		// 오퍼레이션을 실행합니다.
		editState = true; // 에디터 상태 트루
		if (e.getSource() == txtField) { // 텍스트 이벤트 소스가 텍스트필드와 같으면
			printClass();  //출력
		}
	}

	//


	public class MyHandler extends MouseMotionAdapter { //마우스 움직임을 받아옴
		public void mouseDragged(MouseEvent e) { //마우스 드래그 받아옴

			//x,y 좌표를 받아서 다시 그림
			MyCan.x = e.getX();
			MyCan.y = e.getY();
			MyCan.repaint();
		}
	}

	public class MyCanvas extends Canvas {//마이캔버스 클래스, 캔버스에서 상속 받음
		int x = 50, y = 50;//좌표 초기값

		public void update(Graphics g) { //새로 입력된 그래픽을
			paint(g);                    //그림
		}

		public void paint(Graphics g) {//선 그림을 그린다.
			g.setColor(Color.blue); //파랑색으로 설정
			g.fillOval(x, y, 5, 5); //선 그리기 ,두께는 5,5
		}

		public MyCanvas() {
			super();//상위 클래스 생성자 호출
			this.setVisible(true);//보이도록 설정
			this.setBackground(Color.GRAY);//배경색 회색
			this.setSize(200, 300);//크기 설정
			MyHandler my = new MyHandler();//생성자MyHandler
			this.addMouseMotionListener(my);//마우스움직임
		}



	}// ////////////////////////////

	// --------------------------------------------------------
	// --------------------------------------------------------
	// 액 션 이 벤 트 핸 들 러
	// --------------------------------------------------------
	public void actionPerformed(ActionEvent ae) { // actionPerformed 메소드로 버튼에
		// 발생한 이벤트 처리
		String itemPressed = ae.getActionCommand(); // 버튼이 눌렸을때 명령

		if (itemPressed.equals("새로 만들기(N)") || itemPressed.equals("New")) { // 변수에
			// 담긴
			// 데이터가
			// 제목이
			// 같으면
			if (editState) {
				mb.setOpenDialogbox(); // 메시지박스에서 다이어로그박스를 열고 설정
				String strReturn = mb.getFileinfo(); // mb.getFileinfo() 호출한다.
				if (strReturn != null) { // strReturn 에 담긴 데이터가 null이 아니면
					curFilename = strReturn; // cuFilename 변수에 strReturn 데이터 복사
				}
			} else {
				curFilename = "제목 없음"; // strReturn이 null 이면 curFilename호출
				txtArea.setText(null); // 파일명은 "제목 없음" 설정

				frame.setTitle(curFilename + progName); // 타이틀명을
				// curFilename과progName을
				// 합친다.
			}
		} else if (itemPressed.equals("열 기(O)...") // 변수에 담긴 데이터가
				|| itemPressed.equals("Open")) { // 같으면
			String strReturn = mb.openFileDialog(); // mb.openFileDialog()호출
			try {
				if (strReturn != null) { // strReturn 에 담긴 데이터가 null 가 같지 않다면
					curFilename = strReturn; // cuFilename 변수에 strReturn 데이터 복사
					frame.setTitle(curFilename + progName); // frame객체 frame 의
					// Title 변수에
					// curFilename +
					// progName 저장
					String filePath = mb.getPathinfo(); // mb.getPathinfo()
					// 호출한다.
					mb.openFile(filePath, curFilename); // mb.openFile 메소드
					// 호출(filePath,
					// curFilename)
					editState = false; // editState를 false로 변경
				}
			} catch (FileNotFoundException e1) { // 파일을 찾지 못했을 발생되는 오류를 처리 하기 위한
				// 부분
			} catch (IOException e2) { // 파일 관련 다른 오류를 처리 하기 위한 부분
			}
		} else if (itemPressed.equals("저 장(S)") || itemPressed.equals("Save")) { // itemPressed
			// 가
			// 저장(S)
			// 이라면
			try {
				if (curFilename.equals("제목 없음")) { // curFilename 변수에 "제목 없음" 저장
					String strReturn = mb.saveFileDialog(); // mb.saveFileDialog()
					// 호출
					if (strReturn != null) { // strReturn 에 담긴 데이터가 null 가 같지
						// 않다면
						curFilename = strReturn; // cuFilename 변수에 strReturn 데이터
						// 복사
						frame.setTitle(curFilename + progName); // frame객체 frame
						// 의 Title 변수에
						// curFilename +
						// progName 저장
						String filePath = mb.getPathinfo(); // mb.getPathinfo()
						// 호출
						mb.saveFile(filePath, curFilename); // mb.saveFile 메소드
						// 호출(filePath,
						// curFilename)
						editState = false; // editState를 false로 변경
					}
				} else {
					String filePath = mb.getPathinfo(); // mb.getPathinfo()
					// 호출한다.
					mb.saveFile(filePath, curFilename); // mb.saveFile 메소드
					// 호출(filePath,
					// curFilename)
				}
			} catch (FileNotFoundException e1) { // 파일을 찾지 못했을 발생되는 오류를 처리 하기 위한
				// 부분
			} catch (IOException e2) { // 파일 관련 다른 오류를 처리 하기 위한 부분
			}
		} else if (itemPressed.equals("다른 이름으로 저장(A)")) { // 변수에 담긴 데이터가 제목이 같으면
			try {
				String strReturn = mb.saveFileDialog(); // mb.saveFileDialog()
				// 호출
				if (strReturn != null) { // strReturn 에 담긴 데이터가 null 가 같지 않다면
					curFilename = strReturn; // cuFilename 변수에 strReturn 데이터 복사
					frame.setTitle(curFilename + progName); // frame객체 frame 의
					// Title 변수에
					// curFilename +
					// progName 저장
					editState = false; // editState를 false로 변경
				}
			} catch (FileNotFoundException e1) { // 파일을 찾지 못했을 발생되는 오류를 처리 하기 위한
				// 부분
			} catch (IOException e2) { // 파일 관련 다른 오류를 처리 하기 위한 부분
			}
		}

		if (itemPressed.equals("끝내기(X)")) {
			frame.setVisible(false); // 끝내기를 누르게 되면 false 값으로 0이니
			frame.dispose(); // 프로그램을 exit 하게된다.
			System.exit(0);
		}
		if (itemPressed.equals("실행취소(U)")) { // 실행취소를 누르면 CONSOLE창에 hhh라는 문장 출력
			System.out.println("hhh");
		}
		if (itemPressed.equals("자동 줄 바꿈(W)")) { // 자동줄바꿈 메뉴를 누르면 SSS라는 문장출력
			System.out.println("sss");
		}
		if (itemPressed.equals("메모장 정보(A)")) { // 메모장을 누르면 console창에 zzz라는 문장출력
			infoShow();
		} else if (itemPressed.equals("확인")) {// 확인 버튼 누르면
			infoFrame.dispose();// 도움말 프레임 종료
		}
		if (ae.getSource() == mDialogButton) { // getSource()메소드를 이용하여 이벤트를 발생시킨
			// mDialogButton를 식별한다.
			mDialogbox.setVisible(false); // mDialogbox를 호출하지 않는다.
			mDialogbox.disable();
		}

		if (ae.getSource() == mReplaceCancelButton) { // getSource()메소드를 이용하여
			// 이벤트를 발생시킨
			// mReplaceCancelButton를
			// 식별한다.
			mReplaceBox.setVisible(false); // mReplaceBox를 호출하지 않는다.
			mReplaceBox.disable();
		}

		if (ae.getSource() == mReplaceExeButton) { // getSource()메소드를 이용하여 이벤트를
			// 발생시킨 mReplaceExeButton를
			// 식별한다.
			replaceString();
		}

		if (ae.getSource() == miEdit01) { // getSource()메소드를 이용하여 이벤트를 발생시킨
			// miEdit01를 식별한다.
			replaceStringInit();
		}

		// set a text font
		for (int i = 0; i < miFonts.length; i++) { // 텍스트 폰트 설정
			if (ae.getSource() == miFonts[i]) {// miFonts는 액션이벤트 소스와 같다
				txtArea.setFont(new Font(strFonts[i], Font.BOLD, 15)); // 텍스트
				// 에어리어에
				// 볼드,크기
				// 15로
				// 폰트설정
				txtField.setFont(new Font(strFonts[i], Font.BOLD, 15));// 텍스트
				// 필드에
				// 볼드,
				// 크기
				// 15로
				// 폰트 설정
				System.out.println("font is " + strFonts[i]);// 설정된 폰트이름 출력

			}
		}

		// set a background color
		for (int i = 0; i < miColors.length; i++) { // 배경색 설정
			if (ae.getSource() == miColors[i]) { // miColors는 액션이벤트 소스와 같다
				switch (i) {
				case 0:
					txtArea.setBackground(Color.RED); // 0인 경우 배경이 Red가 지정된다.
					break;
				case 1:
					txtArea.setBackground(Color.GREEN); // 1인 경우 배경이 GREEN이
					// 지정된다.
					break;
				case 2:
					txtArea.setBackground(Color.BLUE); // 2인 경우 배경이 BLUE가 지정된다.
					break;
				case 3:
					txtArea.setBackground(Color.CYAN); // 3인 경우 배경이 CYAN이 지정된다.
					break;
				case 4:
					txtArea.setBackground(Color.MAGENTA); // 4인 경우 배경이 MAGENTA가
					// 지정된다.
					break;
				case 5:
					txtArea.setBackground(Color.YELLOW); // 5인 경우 배경이 YELLOW가
					// 지정된다.
					break;
				default:
					txtArea.setBackground(new Color(237, 220, 240)); // 텍스트 에어리어 기본 배경색

					break;
				}

				System.out.println("Color is " + strColors[i]); // "Color is " +
				// strColors[i]을
				// 출력한다.
			}
		}
	}

	public void replaceStringInit() {
		mReplaceBox = new Frame("바꾸기"); // 바꾸기 프레임
		mReplaceExeButton = new Button("바꾸기"); // 바꾸기 버튼
		mReplaceCancelButton = new Button("취소"); // 취소 버튼
		mReplaceExeButton.addActionListener(this); // mReplaceExeButton클래스를
		// ActionListener 연결
		mReplaceCancelButton.addActionListener(this); // mReplaceCancelButton클래스를
		// ActionListener 연결
		mReplaceLabel01 = new Label("찾을 내용"); // mReplaceLabel01은 "찾을 내용" 라벨
		mReplaceLabel02 = new Label("바꿀 내용"); // mReplaceLabel02은
		// Label("바꿀 내용"라벨

		mReplaceText01 = new TextField("입력"); // 찾고 싶은 내용 텍스트 필드에 출력
		mReplaceText02 = new TextField("출력"); // 바꿀 내용 텍스트 필드에 출력
		mReplaceBox.setLayout(new GridLayout(4, 2)); // GridLayout(배열)을 (4,2)로
		// 지정한다.
		mReplaceBox.addWindowListener(this); // mReplaceBox클래스를 WindowListener
		// 연결
		mReplaceBox.add(mReplaceLabel01); // 패널에 mReplaceLabel01 추가한다.
		mReplaceBox.add(mReplaceText01); // 패널에 mReplaceText01 추가한다.
		mReplaceBox.add(mReplaceLabel02); // 패널에 mReplaceLabel02 추가한다.
		mReplaceBox.add(mReplaceText02); // 패널에 mReplaceText02 추가한다.
		mReplaceBox.add(mReplaceExeButton); // 패널에 mReplaceExeButton 추가한다.
		mReplaceBox.add(mReplaceCancelButton); // 패널에 mReplaceCancelButton01
		// 추가한다.
		mReplaceBox.setSize(300, 200); // 사이즈는 가로 300, 세로 200 으로 설정한다.
		mReplaceBox.setVisible(true); // 화면에 보이게 한다.
	}

	public void replaceString() {// 바꾸고 싶은 내용을 찾아서 원하는 내용으로 바꿈
		String replaceTargetStr = mReplaceText01.getText();// replaceTargetStr은mReplaceText01에서
		// 텍스트를 가져온다
		String replaceStr = mReplaceText02.getText(); // replaceStr은
		// mReplaceText02에서 텍스트를
		// 가져온다
		if (txtArea.getText().contains(replaceTargetStr))
			// 모든 단어 재배치
			txtArea.setText(txtArea.getText().replaceAll(replaceTargetStr,
					replaceStr)); // 텍스트 에어리어에 텍스트를 가져와 재배치한다
		mReplaceBox.setVisible(false); // mReplaceBox를 보이지 않게 설정
		mReplaceBox.disable();
	}

	public void printClass(){
		if (txtField.getText().equalsIgnoreCase("객체")) {   // 변수에 담긴 데이터가 제목이 같으면   
			mDialogbox = new Frame("객체");               // 객체 프레임 생성
			mDialogButton = new Button("confirm");        // 버튼생성
			mDialogButton.addActionListener(this);          // mDialogButton클래스를 ActionListener 연결
			mDialogLabel = new Label("객체를 입력하였습니다.");    // 라벨 생성
			mDialogbox.setLayout(new FlowLayout());        //플로우 레이아웃 설정
			mDialogbox.addWindowListener(this);       //mDialogbox클래스를 WindowListener 연결
			mDialogbox.add(mDialogLabel);          // 패널에 mDialogLabel 추가한다. 
			mDialogbox.add(mDialogButton);        // 패널에 mDialogButton 추가한다. 
			mDialogbox.setSize(200, 100);         // 사이즈는 가로 200, 세로 100 으로 설정한다.
			mDialogbox.setVisible(true);         // 화면에 보이게 한다.
		} 
		txtArea.setText(txtField.getText());//텍스트필드의 글을 텍스트 에어리어에 출력 
	}

	public void infoShow() {//도움말 보여주기
		infoFrame = new Frame("메모장 정보(A)"); // infoFrame 프레임
		infoFrame.addWindowListener(new WindowAdapter() { // infoFra에 윈도우리스너
			// WindowAdapter()
			// 추가한다.
			public void windowClosing(WindowEvent we) { // 윈도우가 닫힐때의 액션
				// 즉 창이 닫힐때 발생하는
				// 이벤트
				infoFrame.dispose();
			}
		});

		Label la1 = new Label("조원: 박성운 최필종 정현석 이규용 "
				+ " [제작기간 :  12월 2일~ 12월 16일]");//설명 라벨 

		ImageIcon img = new ImageIcon("F:\\Notepad\\te.png");//ImageIcon클래스인스턴스 생성,사진 불러옴 
		JLabel la4 = new JLabel(new ImageIcon("F:\\Notepad\\te.png"));//스윙 라벨로 사진 출력



		ScrollPane scp = new ScrollPane(); // ScrollPane클래스 인스턴스 생성
		String[] str = { "NotePad Ver.10.0"
				," <프로젝트 작성 및 수정 내역> ",
				" 12/03  조원 모집, 평가표 및 기본 코드 확인", " 12/03 기본적인 기능 점검",
				" 12/04 도움말에 팜업 메뉴 기능 추가", "12/05 스레드로 시간 보여주기 기능 구현",
				"12/06 스레드로 텍스트 필드에 '객체' 입력하면 다이얼로그박스로 나타내기 구현",
				"12/06 스레드로 라벨에 시간 보여주기", "12/07 스레드로 분당 타자수기능 구현 라벨에 출력",
				"12/08 메뉴 버튼 추가하기", "12/10 글자폰트 바꾸기 기능 추가",
				"12/11 배경색 바꾸기 기능 추가", "12/12 도움말 팜업메뉴에 사진 추가",
		"12/13 캔버스에 마우스로 그림그리기 추가"};
		// las는 str을 라벨에 나타내기 위함
		Label[] las = new Label[str.length];
		// p는 las를 나타내기 위한 패널
		Panel p = new Panel();

		p.setLayout(new GridLayout(str.length, 1)); // 패널 p 그리드 레이아웃 설정, 길이는
		// str의 길이, 1

		// las를 p에 추가
		for (int i = 0; i < las.length; i++) {
			las[i] = new Label(str[i]);
			p.add(las[i]);
		}// las 라벨의 길이는 str의 길이에 따라 변한다
		// add p into scp
		scp.add(p);// 스크롤 패널에 패널을 추가
		infoFrame.setLayout(new BorderLayout()); // 순서대로 채워간다.
		infoFrame.add("North", la1); // infoFrame 북쪽에 la1 추가한다.

		infoFrame.add("Center", scp); // infoFrame 중앙에 추가 추가한다.
		infoFrame.add("South", la4); // infoFrame 남쪽에 la4 추가한다.
		la4.setSize(345, 315); // 사진 사이즈 설정

		infoFrame.pack();// 창의 하위 컴포넌트를 환경설정된 크기로 배치
		infoFrame.setSize(500, 800); // infoFrame 사이즈 설정
		infoFrame.setVisible(true); // 화면에 보이게 한다.
	}

	// --------------------------------------------------------

	// --------------------------------------------------------
	// Main 메소드
	// --------------------------------------------------------
	public static void main(String[] args) {// 메인 함수
		Notepad notepad = new Notepad();// 노트패드 생성자
		mb = new MessageBox();// 메시지박스 가져옴

		// 글자수 세기 위한 쓰레드 생성 및 시작
		TextLnegthThread textLThread = new TextLnegthThread();
		textLThread.start();
		// 시간을 나타내기 위한 쓰레드 생성 및 시작
		TimeThread timethread = new TimeThread();
		timethread.start();
		// 분당 타이핑 속도를 나타내기 위한 쓰레드 생성 및 시작
		TypingThread typingThread = new TypingThread();
		typingThread.start();
	}

	// --------------------------------------------------------
	// 시스템 시간 불러오기
	// --------------------------------------------------------
	public static String parseDate() {

		long time = System.currentTimeMillis();// time은 시스템 시간을 가져온다
		SimpleDateFormat format = new SimpleDateFormat("HH시 mm분 ss초"); // SimpleDateFormat클래스를
		// SimpleDateFormat("HH시 mm분 ss초")로
		// 인스턴스화
		// 한다.
		Date dd = new Date(time); // Date클래스 인스턴스 생성
		return format.format(dd); // 가져온 시간을 시,분,초를 입력한다.
	}

}

// --------------------------------------------------------
// 글자수를 세기위한 쓰레드
// --------------------------------------------------------
class TextLnegthThread extends Thread {
	public void run() {

		while (true) {// 참일때
			try { 
				sleep(500); // 딜레이 500ms
				if (Notepad.txtArea.getText().length() >= 1) {// 노트패드 텍스트 에어리어의
					// 길이가 1보다 크거나
					// 같으면 실행
					// 카운트는 글자수는 "\n"
					// 인덱스 함수는 글자수가 증가하면 증가

					int count = 0, index = 0; // 카운트,인덱스 함수 초기값 0
					int checkchar = Notepad.txtArea.getText().length(); // 텍스트에어리어의
					// 텍스트를
					// 가져와
					// 길이 확인
					while (true) {

						index = Notepad.txtArea.getText().indexOf("\n", index) + 1; // 인덱스는는
						// 텍스트
						// 에어리어에
						// 텍스트를가겨와서
						// 인덱스를
						// 증가
						if (index == 0) // 인덱스가 0이면 정지
							break;
						else {// 아니면
							count++; // 카운트를 1씩 더함
							System.out.println("index : " + index
									+ ", count : " + count);
						}
					}
					checkchar -= count; // substract the number of '\t'
					//System.out.println(checkchar);//콘솔창에 글자수 출력

					// 콘솔 창에 글자수 출력

					Notepad.lb1.setText("입력된 글자수는 " + checkchar + "입니다");// 라벨에
					// 출력
					// 문구
				}

			} catch (InterruptedException e) { // 인터럽트 발생시 예외처리
			}
		}
	}

}

// --------------------------------------------------------
// 시간을 나타내기 위한 쓰레드
// --------------------------------------------------------
class TimeThread extends Thread {
	public void run() { // Thread 클래스의 run메소드를 오버라이딩한다.
		while (true) {
			try {
				sleep(1000);// 딜레이 1000
				String time = Notepad.parseDate(); // 시간 가저오는 함수 time
				Notepad.lb2.setText(time);// 라벨2에 설정
			} catch (InterruptedException e) { // 인터럽트 발생시 예외처리

			}
		}
	}

}

// --------------------------------------------------------
// 쓰레드로 타자실력(타수/분당)보여주기
// --------------------------------------------------------
class TypingThread extends Thread {
	public void run() { // //Thread 클래스의 run메소드를 오버라이딩한다.
		int temp = 0; // 함수temp 초기값 0
		while (true) {
			try {
				sleep(1000); // 딜레이 1000

				if (Notepad.txtArea.getText().length() >= 1) { // 텍스트에어리어의 텍스트
					// 길이가 1보다 크거나
					// 같으면
					temp = temp + 1;                            //temp 1 증가
					String checkchar = Notepad.txtArea.getText(); // 텍스트에어리어에
					// 텍스트를 가져옴
					int textSize = checkchar.length();// 가져온 텍스트 길이 확인
					Notepad.lb3.setText("분당 타이핑 속도 : " + (textSize * 60) / temp 	+ " / 분");
					// 분당 타이핑 속도 계산하여 라벨에 출력 , (텍스트길이 곱하기 60)나누기 1(분)
				}
			}

			catch (InterruptedException e) { // 인터럽트 발생시 예외처리
			}

		}
	}

}
