package com.illumio.security;

public interface Firewall {

	boolean acceptPacket(String direction, String protocol, int port, String ip_address);
}
