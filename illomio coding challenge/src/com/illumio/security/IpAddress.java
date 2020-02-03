package com.illumio.security;

public class IpAddress implements Comparable<IpAddress> {
	// 127.0.0.1
	private Integer[] parts;
	
	public IpAddress(String ip) {
		this.parts = new Integer[4];
		String[] ipArr = ip.split("\\.");
		for (int index = 0; index < 4; index = index + 1) {
			parts[index] = Integer.parseInt(ipArr[index]);
		}
	}
	
	@Override
	public int compareTo(IpAddress other) {
		for (int i = 0; i < 4; i++) {
			int compareValue = Integer.compare(this.parts[i], other.parts[i]);
			if (compareValue != 0) return compareValue;
		}
		return 0;
	}

	
}
