package com.studio4096.portfolio.cnstants;

public enum Enabled {
	TRUE("1"),FALSE("0");
	
	public final String VAL;
	
	private Enabled(String val) {
		VAL =val;
	}
	public boolean eq(String val) {
		return VAL.equals(val);
	}

}
