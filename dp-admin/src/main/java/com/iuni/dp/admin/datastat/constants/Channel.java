package com.iuni.dp.admin.datastat.constants;

/**
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public class Channel {
    private String channelCode;
    private String channelName;

    Channel(String channelCode, String channelName) {
        this.channelCode = channelCode;
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
