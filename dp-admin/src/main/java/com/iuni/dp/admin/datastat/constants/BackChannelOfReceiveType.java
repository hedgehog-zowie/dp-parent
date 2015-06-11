package com.iuni.dp.admin.datastat.constants;

public enum BackChannelOfReceiveType {
	
	JDTH(new Channel("JDTH", "京东退货")),
	SNTH(new Channel("SNTH","苏宁退货"));
	
    private Channel channel;

	BackChannelOfReceiveType(Channel channel) {
        this.channel = channel;
    }
	
	public Channel getChannel(){
		return this.channel;
	}
	
	public String getBackChannelCode(){
		return this.channel.getChannelCode();
	}
	
	public String getBackChannelName(){
		return this.channel.getChannelName();
	}

	
}
