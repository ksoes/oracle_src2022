package frame;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import movieDAO.MovieDAO;
import movieVo.Movie;
import movieVo.MyMovie;

public class FrameReserve extends JPanel {
	
	public FrameReserve(Movie m) { // 객체로 m을 넘겨주므로 매개변수로 Movie타입을 받는다
		
		// JPanel 구조
		setBackground(new Color(0xFFD700)); 
		setLayout(null); // 자동배치 끄고(borderLayout이나 greedLayout등 이런애들을 주면 알아서 배치가 되므로 내 맘대로 설정못함)
		setSize(600, 800); // 사이즈 직접 설정
		
		// 디자인
		JPanel top = new JPanel(); // 상단부분의 패널
		top.setBackground(new Color(0xFFD700));
		top.setLayout(null);
		top.setSize(600, 300); // 각각 가로는 똑같이 크기 맞추지만, 세로는 다르게! 패널 부분이 나뉘어져야 하므로
		top.setLocation(0, 0); // 큰 패널에 top패널을 부착한다
		
		JPanel center = new JPanel();
		center.setBackground(new Color(0xFFD700)); 
		center.setLayout(new GridLayout(5, 8)); // 5행 8열 그리드로 자동정렬배치함!!!
		center.setSize(600, 350); // 각각 가로는 똑같이 크기 맞추지만, 세로는 다르게! 패널 부분이 나뉘어져야 하므로
		center.setLocation(0, 305); // 큰 패널에 center패널을 부착한다
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0xFFD700)); 
		bottom.setLayout(null);
		bottom.setSize(600, 200); // 각각 가로는 똑같이 크기 맞추지만, 세로는 다르게! 패널 부분이 나뉘어져야 하므로
		bottom.setLocation(0, 660); // 큰 패널에 bottom패널을 부착한다
		
		
		
		// 구현 Top JPanel(영화 포스터, 이름, 시간대 선택)
		final int movieNum = 8;
		int movieflag = 0;
		String[] movieurl = { 
				"image/라라랜드.png", "image/말할수없는비밀.jpg", "image/인사이드아웃.png", 
				"image/겨울왕국.png", "image/당신거기있어줄래요.png", "image/스파이더맨.png",
				"image/어벤져스.png", "image/부산행.png" }; // 그림 String으로 만들어두고

		ImageIcon[] movieImg = new ImageIcon[movieNum]; // 그림 불러오기
//		ImageIcon[] movieImg = new ImageIcon["image/라라랜드.png"]; // 한 개 불러오기
		
		for (int i = 0; i < movieNum; i++) {
			movieImg[i] = new ImageIcon(movieurl[i]);
			if (m.getmName().equals("라라랜드")) {
				movieflag = 0;
			} else if (m.getmName().equals("말할수없는비밀")) {
				movieflag = 1;
			} else if (m.getmName().equals("인사이드아웃")) {
				movieflag = 2;
			} else if (m.getmName().equals("겨울왕국")) {
				movieflag = 3;
			} else if (m.getmName().equals("당신거기있어줄래요")) {
				movieflag = 4;
			} else if (m.getmName().equals("스파이더맨")) {
				movieflag = 5;
			} else if (m.getmName().equals("스파이더맨")) {
				movieflag = 5;
			} else if (m.getmName().equals("어벤져스")) {
				movieflag = 6;
			} else if (m.getmName().equals("부산행")) {
				movieflag = 7;
			}
		}//for

		JLabel img = new JLabel(movieImg[movieflag]); // 이미지는 꼭 JLabel에 붙여야 한다 
		img.setBounds(50, 15, 175, 248);
		
		JLabel name = new JLabel(m.getmName()); // 영화 이름으로 라벨 생성하기
		name.setFont(new Font("나눔고딕코딩", Font.BOLD, 32));
		name.setBounds(260, 35, 300, 175);
		
		Choice movieTime = new Choice();
		long time = System.currentTimeMillis(); // 현재의 시간이 time에 담긴
		
		// 여기까지는 String
		SimpleDateFormat dayTime = new SimpleDateFormat("MM월 dd일 (EE)일"); // MM:월 dd:일 EE:요일
		String str = dayTime.format(new Date(time)); // 여기서 날짜형식으로 format()을 해줌
		
		String[] timeSlot = { 
				"시간대 선택", // 첫번째 choice는 "시간대 선택"이라는 문구를 보고 싶음
				str + " 06:30~08:30", 
				str + " 09:00~11:00", 
				str + " 11:30~13:30",
				str + " 14:00~16:00", 
				str + " 16:30~18:30", 
				str + " 19:00~21:30", 
				str + " 22:00~00:00",
				str + " 00:30~02:30" };

		for (int i = 0; i < timeSlot.length; i++) {
			movieTime.add(timeSlot[i]);
		}
		
		// Choice상자의 위치와 크기
		movieTime.setFont(new Font("나눔고딕코딩", Font.PLAIN, 18));
		movieTime.setSize(270, 200);
		movieTime.setLocation(260, 200);
		
		// ----------------------------상단 끝--------------------------------
		
		JLabel screen = new JLabel("[스 크 린]");
		screen.setFont(new Font("나눔고딕코딩", Font.BOLD, 32));
		screen.setBounds(180, 223, 240, 120);
		screen.setVisible(false);
		
		// -------------------중앙부 JPanel (좌석 선택)------------------
		ButtonGroup bg = new ButtonGroup();
		final int seatNum = 5 * 8; // 5행 8열 
		JRadioButton[] seatChoice = new JRadioButton[seatNum];
		String[] seatn = { "1", "2", "3", "4", "5", "6", "7", "8" };
		String seatAl = "A"; // i가 0일 때는 여기 있음
		
		for (int i=0; i<5; i++) {
			if (i == 1)
				seatAl = "B"; // seatAl = 65;
			else if (i==2) 
				seatAl = "C";
			else if (i==3) 
				seatAl = "D";
			else if (i==4) 
				seatAl = "E";
			
			for (int j=0; j<8; j++) {
				seatChoice[(i*8)+j] = new JRadioButton(seatAl+seatn[j]);
				bg.add(seatChoice[(i*8)+j]); // 버튼그룹에다가 붙이고
				center.add(seatChoice[(i*8)+j]); // 센터 패널에다가 붙임
				seatChoice[(i*8)+j].setVisible(false);
			} // inner for
			
		}
		
		JButton btnBack = new JButton("뒤로가기");

		btnBack.setBackground(new Color(0xA6A6A6));
		btnBack.setSize(183, 87);
		btnBack.setLocation(5, 0);
		btnBack.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottom.add(btnBack);
		JButton btnHome = new JButton("처음 화면으로");

		btnHome.setBackground(new Color(0xA6A6A6));
		btnHome.setSize(183, 87);
		btnHome.setLocation(198, 0);
		btnHome.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottom.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getInstance(new FrameBegin());
			}
		});
		
		JButton btnBuy = new JButton("결제하기");

		btnBuy.setBackground(new Color(0xA6A6A6));
		btnBuy.setSize(183, 87);
		btnBuy.setLocation(391, 0);
		btnBuy.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottom.add(btnBuy);
		
		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyMovie myM = new MyMovie();
				myM.setmName(m.getmName()); // myM에 m에담겨있는 영화제목을 가져와서 넣어줌
				myM.setTime(movieTime.getSelectedItem()); // Choice의 getSelectedItem(): 내가 선택한 것(요일 선택할 때 사용했었음)
				int seatIndex = -1;
				
				for (int i=0; i<seatNum; i++) {
					if (seatChoice[i].isSelected()) {// isSelected(): 내가 선택한 것
						seatIndex = i; // 시트 인덱스는 i번째다
					}
				}
				
				if (movieTime.getSelectedItem().equals(timeSlot[0])) { // 영화시간대 선택이 "시간대 선택" 이냐
					JOptionPane.showMessageDialog(null, "시간을 선택하시오"); // null: 현재 창에 떠라
				} else if (seatIndex == -1) {
					JOptionPane.showMessageDialog(null, "좌석을 선택하시오");
				} else {
					myM.setSeat(seatChoice[seatIndex].getText()); // 선택한 좌석에 번호를 넣어서 자리를 set해준다
					// 결제 성공 코드 추가
					FrameBase.getInstance(new FrameMovieConfirm(m, myM));
				}
			}
		}); // addActionListener
		
		top.add(img); // 현재 프레임 그 자체라서 그냥 add()해줘도 된다
		top.add(name);
		top.add(movieTime);
		top.add(screen);
		
		add(top);
		add(center);
		add(bottom);
		
		// 이미 예약된 정보인지 확인해서 예약되었으면 setVisible을 false로 바꾸기
		movieTime.addItemListener(new ItemListener() { // choicebox, radiobutton 같은 것들은 item
			@Override
			public void itemStateChanged(ItemEvent e) {
				for (int i=0; i<seatNum; i++) {
					seatChoice[i].setVisible(true);
				}
				
				if (e.getItem().equals("시간대 선택")) { // e(이벤트)를 getIntem(가져와서) equals("1")(문자열과 같은지 비교)
					for (int i=0; i<seatNum; i++) {
						seatChoice[i].setVisible(false); // 선택된 좌석은 잠궈놓는다
						screen.setVisible(false);
					}
				} else { // 시간대 선택이 되어있는 경우
					for (int i=0; i<seatNum; i++) {
						seatChoice[i].setVisible(true); // true로 바꿔서 좌석들을 보이도록 해놓는다
						screen.setVisible(true);
					} // inner for
				} // if-else
			
				// 예매된 것인지 확인
				MovieDAO setSeat = new MovieDAO();
				for (int j=0; j<seatNum; j++) {
					// dupSeat메소드를 이용하여 예매를 확인하고 예매된 좌석은 비활성화 한다.
					if (setSeat.dupSeat(m.getmName(), e.getItem().toString(), seatChoice[j].getText())) {// choice는 choice box에서 가져왔으므로 getItem
						seatChoice[j].setEnabled(false); // 비활성화
					} // if
				} // for
				
			} // itemStateChanged
		}); // Listener
		
	} // 생성자
} // 클래스
