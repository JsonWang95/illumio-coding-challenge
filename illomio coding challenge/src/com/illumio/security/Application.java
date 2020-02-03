package com.illumio.security;

import java.io.IOException;

public class Application {

	public static void main(String[] args) throws IOException {
		Firewall fw = new RuleBasedFirewall("/path/fg.csv");
		assert (fw.acceptPacket(", protocol, port, ip_address) == true)
	}
}
