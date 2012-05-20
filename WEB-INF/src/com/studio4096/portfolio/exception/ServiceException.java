package com.studio4096.portfolio.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5108706772884533085L;

	public ServiceException(Throwable e, Kind kind) {
		super(e);
		this.kind =kind;
	}
	
	private Kind kind;
	
	public Kind getKind() {
		return kind;
	}
	
	public enum Kind{
		DB_ERROR,
		FILE_ERROR,
		;
	}
	

}
