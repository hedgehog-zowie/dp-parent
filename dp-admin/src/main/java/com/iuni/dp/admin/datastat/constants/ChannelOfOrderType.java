package com.iuni.dp.admin.datastat.constants;

/**
 * 订单渠道
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum ChannelOfOrderType {

    IUNIGW(new Channel("IUNIGW", "IUNI官网")),
    IUNITM(new Channel("IUNITM", "IUNI天猫")),
    LPZS(new Channel("LPZS", "礼品赠送")),
    NBGJ(new Channel("NBGJ", "内部购机")),
    HHDD(new Channel("HHDD", "换货订单（配件）")),
    PP(new Channel("PP", "拍拍网")),
    ;

    private Channel channel;

    ChannelOfOrderType(Channel channel) {
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

