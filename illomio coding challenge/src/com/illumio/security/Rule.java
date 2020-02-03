package com.illumio.security;

public class Rule {
	private Direction direction;
	private Protocol protocol;
	private int portStartRange;
	private int portEndRange;
	private IpAddress ipAddressStart;
	private IpAddress ipAddressEnd;
	
	public Rule(Direction direction, Protocol protocol, int portStartRange, int portEndRange, String ipAddressStart,
			String ipAddressEnd) {
		super();
		this.direction = direction;
		this.protocol = protocol;
		this.portStartRange = portStartRange;
		this.portEndRange = portEndRange;
		this.ipAddressStart = new IpAddress(ipAddressStart);
		this.ipAddressEnd = new IpAddress(ipAddressEnd);
	}
	public Direction getDirection() {
		return direction;
	}
	public Protocol getProtocol() {
		return protocol;
	}
	public int getPortStartRange() {
		return portStartRange;
	}
	public int getPortEndRange() {
		return portEndRange;
	}
	public IpAddress getIpAddressStart() {
		return ipAddressStart;
	}
	public IpAddress getIpAddressEnd() {
		return ipAddressEnd;
	}
}
