package com.rkc.zds.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	SMTPAuthenticator() {
	}

	public PasswordAuthentication getPasswordAuthentication() {
		String username = "richard.campion";
		String password = "ChangeIt";
		return new PasswordAuthentication(username, password);
	}
}
