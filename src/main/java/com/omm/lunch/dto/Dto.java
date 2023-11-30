package com.omm.lunch.dto;

public class Dto {
	public String no;         
	public String title;  
	public String text;
	public String id;         
	public String datetime;   
	public String hit;        
	public String replyCount;
	public String replyOri;
	public String menu;
	public String category;
	public String total_like;
	
	
	
	public Dto(String category, String title, String id, String text) {
		this.category = category;
		this.title = title;
		this.id = id;
		this.text = text;
	}  
	//alt + shift + s 코드 자동 삽입
	public Dto(String no, String title, String text, String id, String datetime, String hit, String replyCount,
			String replyOri, String category) {
		this.no = no;
		this.title = title;
		this.text = text;
		this.datetime = datetime;
		this.hit = hit;
		this.replyOri = replyOri;
		this.replyCount = replyCount;
		this.id = id;
		this.category = category;
	}
	public Dto(String no, String title, String text, String id, String datetime, String hit, String replyCount,
			String replyOri) {
		this.no = no;
		this.title = title;
		this.text = text;
		this.id = id;
		this.datetime = datetime;
		this.hit = hit;
		this.replyCount = replyCount;
		this.replyOri = replyOri;
	}
	public Dto(String title, String text) { //오버로딩을 하여 매개변수 갯수를 다르게 저장해둔다. 필요할때 사용하기위해서. 이경우에는 del용이다.
		this.title = title;
		this.text = text;
	}
	//todo : board3에 맞게 Dto를 변경해줘야함. 아마 dao에 매개변수들도 순서조정을 해줘야 할수도잇다.
	public Dto(String menu, String category, String total_like) {
		this.menu = menu;
		this.category = category;
		this.total_like = total_like;
	}
	public Dto(String no, String title, String text, String id, String category) {
		this.no = no;
		this.title = title;
		this.text = text;
		this.id = id;
		this.category = category;
		
	}
}