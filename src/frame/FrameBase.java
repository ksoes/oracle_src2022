package frame;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameBase extends JFrame { // JFrame 상속받으면 dispose사용할 수 있음!
	
	private static FrameBase instance;
	
	private FrameBase(JPanel e) { // 팀프로젝트에서는 JPanel을 JFrame으로 바꿔서 사용하면 됨
		// 해상도
		Toolkit tk = Toolkit.getDefaultToolkit(); // 해상도 가져오기
		
		// 기본 JFrame 환경설정
		setTitle("영화 예매 프로그램");
		setLayout(null); // 자동배치X
		setBounds( ( (int)tk.getScreenSize().getWidth() )/2-300, // 지정한 위치 절반에서 내가 만들 프레임의 반만큼 올라간다(그러면 패널의 중심이 화면에 중심에 위치한다)
				( (int)tk.getScreenSize().getHeight() )/2-400,
				600, 800 );
		
		add(e); // 프레임을 상속받았으므로, 프레임에 Panel e를 붙인다
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼, 기존에 있던 메모리를 모두 비운다! 닫으면 모든 창이 닫힌다
	} // 생성자
	
	// 싱글톤 기법을 사용하기 위핸 메소드
	static public void getInstance(JPanel e) {
		// 매개변수로 JPanel을 받으므로 Frame.Instance(new ~)에 ~는 JPanel을 상속받은 클래스이다! 
		instance = new FrameBase(e);
		
		instance.getContentPane().removeAll(); // 기존에 화면에 있던 모든걸 지우고
		instance.getContentPane().add(e); // 새로 생성하는 걸 더한다
		
		instance.revalidate(); // 레이아웃 자체를 처음으로 초기화
		instance.repaint(); // 새로 그리기
	}
	
}
