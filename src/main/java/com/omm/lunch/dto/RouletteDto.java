package com.omm.lunch.dto;

public class RouletteDto {
	public String menu;
	public String category;
	public String total_like;
	public String weekly_like;

	//todo : board3에 맞게 Dto를 변경해줘야함. 아마 dao에 매개변수들도 순서조정을 해줘야 할수도잇다.
	public RouletteDto(String menu, String category, String total_like) {
		this.menu = menu;
		this.category = category;
		this.total_like = total_like;
	}

	public RouletteDto(String menu, String category, String total_like,String weekly_like) {
		this.menu = menu;
		this.category = category;
		this.total_like = total_like;
		this.weekly_like = weekly_like;
	}
}
