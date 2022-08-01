package frame;

import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import movieVo.Movie;

public class FrameReviewRead extends JFrame { // 프레임 자체를 만들고 가져와서 frame.add가 아닌 그냥 add()만 하면 붙는다
	
	public FrameReviewRead(Movie m) {
		
		// 후기 출력 창
		TextArea review = new TextArea("평점\t이름\t\t\t한줄평\n", 0, 0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		
		review.setEditable(false);
		review.append(m.getReview());
		review.setSize(800,300);
		review.setLocation(700,200);
		review.setFont(new Font("맑은고딕",Font.PLAIN,18));
		add(review);
		
		Frame fr = new Frame(); // 새로운 창 하나 만들기
		fr.setTitle(m.getmName()+"의 리뷰");
	    fr.setSize(800, 300); // 창 사이즈 설정
	    fr.setLocation(700, 200); // 창 위치 설정
	    fr.add(review); // 프레임 창에 출력후기 textArea객체 붙이기 
	    fr.setVisible(true); // 창 보이기
	    fr.addWindowListener(new WindowAdapter() { // 창 눌렸을 때 이벤트
	         @Override
	         public void windowClosing(WindowEvent e) {
	            fr.setVisible(false);
	            fr.dispose();
	         }
	      });
	    
	    
	}
	
}
