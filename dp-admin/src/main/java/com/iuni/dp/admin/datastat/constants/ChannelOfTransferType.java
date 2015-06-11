package com.iuni.dp.admin.datastat.constants;

/**
 * 调拔渠道
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum ChannelOfTransferType {

    JD(new Channel("JD", "京东")),
    SN(new Channel("SN", "苏宁"));

    private Channel channel;

    ChannelOfTransferType(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public String getChannelCode(){
        return this.channel.getChannelCode();
    }

    public String getChannelNmae(){
        return this.channel.getChannelName();
    }

}

