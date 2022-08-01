package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import movieVo.Movie;

public class FrameMovieInfo extends JPanel {
	
	 // 호출하는 순간 프레임 만들어짐!
	public FrameMovieInfo(Movie m) { // Movie 클래스의 객체를 생성해서 Movie클래스의 메소드들을 사용해서 필드 접근! 
		
		// JPanel 세팅
		setBackground(new Color(250, 244, 192));
		setLayout(null);
		setSize(600, 800);
		
		// 포스터 부분
		final int movieNum = 8;
		int movieflag = 0;
		
		String[] movieurl = {
			"image/라라랜드2.png", "image/말할수없는비밀2.jpg", "image/인사이드아웃2.png", 
			"image/겨울왕국2.png", "image/당신거기있어줄래요2.png", "image/스파이더맨2.png",
			"image/어벤져스2.png", "image/부산행2.png" 
		};
		
		ImageIcon[] movieImg = new ImageIcon[movieNum]; // 포스터를 담아놓을 ImageIcon 배열 생성
		
		for (int i=0; i<movieNum; i++) {
			movieImg[i] = new ImageIcon(movieurl[i]); // 사진을 가져옴
			if (m.getmName().equals("라라랜드")) {
				movieflag = 0; // 고유번호 0번은 라라랜드
			} else if (m.getmName().equals("말할수없는비밀")) {
				movieflag = 1; // 고유번호 1번은 말할수없는비밀
			} else if (m.getmName().equals("인사이드아웃")) {
				movieflag = 2; // 고유번호 2번은 인사이드아웃
			} else if (m.getmName().equals("겨울왕국")) {
				movieflag = 3; // 고유번호 3번은 겨울왕국
			} else if (m.getmName().equals("당신거기있어줄래요")) {
				movieflag = 4; // 고유번호 4번은 스파이더맨
			} else if (m.getmName().equals("스파이더맨")) {
				movieflag = 5; // 고유번호 5번은 당신거기있어줄래요
			} else if (m.getmName().equals("어벤져스")) {
				movieflag = 6; // 고유번호 6번은 어벤져스
			} else if (m.getmName().equals("부산행")) {
				movieflag = 7; // 고유번호 7번은 부산행
			}
		} // for문
		
		// -------------------------------------------
		
		JLabel img = new JLabel(movieImg[movieflag]);
		img.setBounds(50, 13, 285, 350); // 이미지가 크게 열릴것이므로 크기 조정
		add(img); // 프레임에다가 붙임
		
		// 영화 정보 텍스트
//		TextArea story = new TextArea(m.getmName() + m.getScore() + m.getStory() ); // 버튼이 눌렸을 때 받은 movie에 해당하는 객체(m)를 갖고와서
		TextArea story = new TextArea("\n◎영화제목" + "\n" + "   " + m.getmName() + 
				"\n\n" + "◎평     점" + "\n" + "   " + 
				String.format("%.1f", m.getScore()) + " / 5 점\n\n" + 
				"◎줄 거 리" + "\n" + "   " + m.getStory(),
				0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY); // m.getmName()으로 영화 제목을 갖고오고, m.getScore()로 영화 평점을 가져오고, m.getStory()로 영화 줄거리를 가져온다
		story.setSize(580,270);
		story.setLocation(0, 375);
		story.setBackground(new Color(0xFFD700));
		story.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		add(story);
		
		// 리뷰 보기
		JButton btnReview = new JButton("리뷰"); // JPanel을 가져왔기 때문에 JButton을 쓸 수 있다
		btnReview.setBackground(new Color(229, 216, 92));
		btnReview.setSize(150, 40);
		btnReview.setLocation(400, 325);
		btnReview.setFont(new Font("나눔고딕코딩", Font.BOLD, 18));
		
		btnReview.addActionListener(new ActionListener() { // 리뷰 버튼이 눌리면 이벤트 발생
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameReviewRead(m); // 영화정보를 가지고 리뷰읽기 를 호출한다
			}
		});
		
		add(btnReview); // 패널에 버튼 붙이기
		
		// 하단 (패널)
		JPanel bottomSet = new JPanel();

		bottomSet.setBounds(0, 660, 600, 100);
		bottomSet.setLayout(null);
		bottomSet.setBackground(new Color(0xFFD700));

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBackground(new Color(0xA6A6A6));
		btnBack.setSize(183, 87);
		btnBack.setLocation(5, 0);
		btnBack.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottomSet.add(btnBack);
		
		int buttonNum = movieflag; // 현재 넘어온 영화플래그를 저장해놓음
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (0 <= buttonNum && buttonNum <= 3) {
					FrameBase.getInstance(new FrameMovieSelect());
				} else {
					FrameBase.getInstance(new FrameMovieSelect2());
				}
			}
		});
		
		// 처음으로 돌아가는 버튼
		JButton btnHome = new JButton("처음으로");
		btnHome.setBackground(new Color(0xA6A6A6));
		btnHome.setSize(183, 87);
		btnHome.setLocation(198, 0);
		btnHome.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottomSet.add(btnHome);
		
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getInstance(new FrameBegin());
			}
		});
		
		// 예매하기
		JButton btnBuy = new JButton("예매하기");
		
		btnBuy.setBackground(new Color(0xA6A6A6));
		btnBuy.setSize(183, 87);
		btnBuy.setLocation(391, 0);
		btnBuy.setFont(new Font("나눔고딕코딩", Font.BOLD, 22));
		bottomSet.add(btnBuy);

		btnBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getInstance(new FrameReserve(m)); // m(영화객체)를 주면서 어떤 영화를 예매할 것인지
			}
		});
		
		add(bottomSet, BorderLayout.SOUTH);

	}
	
}
