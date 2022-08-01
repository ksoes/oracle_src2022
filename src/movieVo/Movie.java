package movieVo;

public class Movie {
	
	// 필드
	String mName; // 영화
	int price; // 영화 가격
	double score; // 영화 평점
	String story; // 영화 줄거리
	String review; // 영화 평점
	int total; // 평점 누적
	int personScore; // 방금 등록한 평점
	String rev; // 한줄 평 등록
	int personNum; // 평점을 등록한 사람 수
	String reviewName; // 평점 등록한 사람의 이름
	
	// 생성자
	public Movie(String mName, int price, double score, String story, String review) {
		this.mName = mName;
		this.price = price;
		this.total = (int)score;
		this.story = story;
		this.review = review;
		this.personNum = 1;
	}
	
	// getter & setter
	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getScore() {
		return score;
	}

	public void setScore() { // total/사람수 를 해야 평점평균이 나옴
		this.score = (double)this.total / this.personNum;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getReview() {
		return review;
	}

	public void setReview() {
		this.review += this.personScore + "점\t<"+this.reviewName+">\t"+ this.rev +"\n"; // 리뷰에 추가 : 점수+리뷰단사람+한줄평
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int personN) {
		this.total += personN; // total은 평점을 누적하는 것이므로 
	}

	public int getPersonScore() {
		return personScore;
	}

	public void setPersonScore(int personScore) {
		this.personScore = personScore;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	public int getPersonNum() {
		return personNum;
	}
	
	public void setPersonNum() {
		this.personNum++; // 리뷰를 남길때 마다 personNum 1씩 증가
	}
	
	public String getReviewName() {
		return reviewName;
	}
	
	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}
	
	@Override
	public String toString() { // 객체를 불렀을 때 자동으로 해시코드 대신 아래의 형식으로 값이 넘어온다!!!
		return "★영화 제목 : " + mName + 
				"\n★가격 : " + price + "\n★평점 : " + score + 
				"\n★최신 한줄 평 : " + review;
	}
	
	
}
