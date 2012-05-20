package com.studio4096.portfolio.dto;

import java.io.Serializable;

public class RectSize implements Serializable{
	public RectSize(){
		super();
	}
	public RectSize(int w,int h){
		this();
		this.w =w;
		this.h=h;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -4117540997684396228L;
	public Integer w;
	public Integer h;
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public RectSize fitIn(RectSize target){
		RectSize fitted =new RectSize(target.w,target.h);
		return fitted;
	}
}
