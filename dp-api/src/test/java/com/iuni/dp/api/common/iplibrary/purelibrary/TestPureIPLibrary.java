package com.iuni.dp.api.common.iplibrary.purelibrary;

import com.iuni.dp.api.common.iplibrary.purelibrary.IPSeeker;

/**
 * Pure IP Library Test
 * @author CaiKe
 * @version dp-api-V1.0.2
 */
public class TestPureIPLibrary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long stime = System.currentTimeMillis();
		IPSeeker ipSeeker = IPSeeker.getInstance();
		String ip = "122.11.41.227";
		String area;
		String location;
		String address;
		area = ipSeeker.getArea(ip);
		location = ipSeeker.getLocation(ip);
		address = ipSeeker.getAddress(ip);
		System.out.println("地理区域： " + area + " 具体位置或网络服务：" + location + " 地址： " + address
				+ "  cost time : " + (System.currentTimeMillis()-stime));
	}

}
