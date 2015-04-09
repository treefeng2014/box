package com.example.boxplatform;

import java.util.EventObject;

public class MyEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	String str;
	byte[] buf;
	public MyEvent(byte[] buf) {
		super(buf);
		this.buf = buf;
	}
	public MyEvent(String str) {
		super(str);
		this.str = str;
	}
	public String getString() {
		return str;
	}
	public byte[] getBuffer() {
		return buf;
	}

	public Object getSource() {
		return buf;
	}
}
