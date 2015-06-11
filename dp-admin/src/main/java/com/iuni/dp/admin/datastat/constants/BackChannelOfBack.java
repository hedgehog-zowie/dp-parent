package com.iuni.dp.admin.datastat.constants;

public enum BackChannelOfBack {
	
	IUNIGWTH(new Channel("IUNIGWTH","IUNI官网退货")),
	IUNITMTH(new Channel("IUNITMTH","IUNI天猫退货")),
	HHTH(new Channel("HHTH","换后退货")),
	IUNIPPTH(new Channel("IUNIPPTH","IUNI拍拍退货")),
	
	LPZS(new Channel("LPZS","礼品赠送")),
	SDRK(new Channel("SDRK","刷单入库")),
	;
	
	private Channel channel;

	public Channel getChannel(){
		return this.channel;
	}
	
	private BackChannelOfBack(Channel channel) {
		this.channel = channel;
	}

	public String getBackChannelCode(){
		return this.channel.getChannelCode();
	}
	
	public String getBackChannelName(){
		return this.channel.getChannelName();
	}
	
	
	
}
