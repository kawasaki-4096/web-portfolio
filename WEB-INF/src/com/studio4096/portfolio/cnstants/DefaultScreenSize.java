package com.studio4096.portfolio.cnstants;

public enum DefaultScreenSize{
	  W480H320(480,320)
	  , W1024H768(1024,768)
	  ;
	    DefaultScreenSize(int w,int h){
	    	this.W = w;
	    	this.H = h;
	    }
		public final int W;
		public final int H;
	}
