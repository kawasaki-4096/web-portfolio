package com.studio4096.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 小数を含む四則演算を正確に行うためのクラスです。
 * 精度等デフォルトの設定は{@link java.math.MathContext#DECIMAL32 }を参照してください。
 * @author h.kawasaki
 * @version $Revision$ $Date$ $Author$
 */
public class Calculator {

	private BigDecimal bd;
	private MathContext mc;
	{
		// default setting.
		this.bd = new BigDecimal(0);
		this.mc = MathContext.DECIMAL32;
	}
	public Calculator() {
		super();
	}
	public Calculator(MathContext mc) {
		this.mc = mc;
	}
	public Calculator reset(){
		this.bd = new BigDecimal(0);
		return this;
	}
	
	public Calculator setMathContext(MathContext mc){
		this.mc = mc;
		return this;
	}

	public Calculator add(long arg) {
		add(newBigDecimal(arg));
		return this;
	}

	public Calculator add(int arg) {
		add(newBigDecimal(arg));
		return this;
	}

	public Calculator add(double arg) {
		add(newBigDecimal(arg));
		return this;
	}

	public Calculator add(float arg) {
		add(newBigDecimal(arg));
		return this;
	}

	public Calculator add(BigDecimal arg) {
		bd = bd.add(arg,mc);
		return this;
	}

	public Calculator multiply(long arg) {
		multiply(newBigDecimal(arg));
		return this;
	}

	public Calculator multiply(int arg) {
		multiply(newBigDecimal(arg));
		return this;
	}

	public Calculator multiply(double arg) {
		multiply(newBigDecimal(arg));
		return this;
	}

	public Calculator multiply(float arg) {
		multiply(newBigDecimal(arg));
		return this;
	}

	public Calculator multiply(BigDecimal arg) {
		bd = bd.multiply(arg,mc);
		return this;
	}

	public Calculator divide(long arg) {
		divide(newBigDecimal(arg));
		return this;
	}

	public Calculator divide(int arg) {
		divide(newBigDecimal(arg));
		return this;
	}

	public Calculator divide(double arg) {
		divide(newBigDecimal(arg));
		return this;
	}

	public Calculator divide(float arg) {
		divide(newBigDecimal(arg));
		return this;
	}

	public Calculator divide(BigDecimal arg) {
		bd = bd.divide(arg,mc);
		return this;
	}

	public Calculator roundHalfUp(int newScale) {
		bd = bd.setScale(newScale, RoundingMode.HALF_UP);
		return this;
	}

	public Calculator floor(int newScale) {
		bd = bd.setScale(newScale, RoundingMode.FLOOR);
		return this;
	}

	public long getResultAsLong() {
		return bd.longValue();
	}

	public int getResultAsInt() {
		return bd.intValue();
	}

	public float getResultAsFloat() {
		return bd.floatValue();
	}

	public double getResultAsDouble() {
		return bd.doubleValue();
	}
	
	public BigDecimal getResult() {
		return bd;
	}
	
	@Override
	public String toString() {
		return bd.toString();
	}

	private BigDecimal newBigDecimal(float f1) {
		// IEEE 754 浮動小数点数を用いているためfloatを
		// そのまま渡すと誤差が生じるためBigDecimal(String)を用いる。
		return new BigDecimal(String.valueOf(f1),mc);
	}

	private BigDecimal newBigDecimal(long l) {
		return new BigDecimal(l,mc);
	}

	private BigDecimal newBigDecimal(int i) {
		return new BigDecimal(i,mc);
	}

	private BigDecimal newBigDecimal(double d1) {
		// IEEE 754 浮動小数点数を用いているためdoubleを
		// そのまま渡すと誤差が生じるためBigDecimal(String)を用いる。
		return new BigDecimal(String.valueOf(d1),mc);
	}

	/**
	 * @param args 
	 * @Deprecated For test.
	 */
	@Deprecated
	public static void main(String[] args) {
		double d1, d2;
		long l1, l2;// 未テスト
		int i1, i2;
		float f1, f2;// 未テスト
		{
			System.out.println("1 +  3 = 4");
			i1 = 1;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).add(i2);
			System.out.println(calc.getResultAsInt());// 4
			System.out.println(calc.getResultAsLong());// 4
			System.out.println(calc.getResultAsFloat());//  4.0
			System.out.println(calc.getResultAsDouble());// 4.0 
		}
		{
			System.out.println("1 -  3 = -2");
			i1 = 1;
			i2 = -3;
			Calculator calc = new Calculator().add(i1).add(i2);
			System.out.println(calc.getResultAsInt());// -2
			System.out.println(calc.getResultAsLong());// -2
			System.out.println(calc.getResultAsFloat());//  -2.0
			System.out.println(calc.getResultAsDouble());// -2.0 
		}
		{
			System.out.println("2 * 3 = 6");
			i1 = 2;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).multiply(i2);
			System.out.println(calc.getResultAsInt()); // 6
			System.out.println(calc.getResultAsLong()); // 6
			System.out.println(calc.getResultAsFloat()); //  6.0
			System.out.println(calc.getResultAsDouble()); // 6.0
		}
		{
			System.out.println("1 /  3 = 0.333333...");
			i1 = 1;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).divide(i2);
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());//  0.3333333
			System.out.println(calc.getResultAsDouble());// 0.3333333
		}
		{
			System.out.println("2 /  3 = 0.666666...");
			i1 = 2;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).divide(i2);
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());//  0.6666667
			System.out.println(calc.getResultAsDouble());// 0.6666667
		}
		{
			System.out.println("2 /  3 = 0.666666...");
			i1 = 1;
			i2 = 3;
			Calculator calc = new Calculator(MathContext.UNLIMITED).add(i1).setMathContext(MathContext.DECIMAL128).divide(i2);
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());//  0.33333334
			System.out.println(calc.getResultAsDouble());// 0.3333333333333333
		}
		{
			System.out.println("2 /  3 = 0.666666...");
			i1 = 2;
			i2 = 3;
			Calculator calc = new Calculator(MathContext.UNLIMITED).add(i1).setMathContext(MathContext.DECIMAL128).divide(i2);
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());//  0.6666667
			System.out.println(calc.getResultAsDouble());// 0.6666666666666666
		}
		{
			System.out.println("1 /  3 = 0.333333...");
			i1 = 1;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).divide(i2);
			calc.roundHalfUp(2);// 小数点以下2桁目を支社五入
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());// 0.33
			System.out.println(calc.getResultAsDouble());// 0.33
		}
		{
			System.out.println("2 /  3 = 0.666666...");
			i1 = 2;
			i2 = 3;
			Calculator calc = new Calculator().add(i1).divide(i2);
			calc.roundHalfUp(2);// 小数点以下2桁目を支社五入
			System.out.println(calc.getResultAsInt());// 0
			System.out.println(calc.getResultAsLong());// 0
			System.out.println(calc.getResultAsFloat());// 0.67
			System.out.println(calc.getResultAsDouble());// 0.67
		}
		{
			System.out.println("1 + 1.3 = 2.3");
			i1 = 1;
			d2 = 1.3;
			Calculator calc = new Calculator().add(i1).add(d2);
			System.out.println(calc.getResultAsInt());// 2
			System.out.println(calc.getResultAsLong());// 2
			System.out.println(calc.getResultAsFloat());// 2.3
			System.out.println(calc.getResultAsDouble());// 2.3
		}
		{
			System.out.println("2 * 1.3 = 2.6");
			i1 = 2;
			d2 = 1.3;
			Calculator calc = new Calculator().add(i1).multiply(d2);
			System.out.println(calc.getResultAsInt());// 2
			System.out.println(calc.getResultAsLong());// 2
			System.out.println(calc.getResultAsFloat());// 2.6
			System.out.println(calc.getResultAsDouble());// 2.6
		}
		{
			System.out.println("2 / 1.3 = 1.5384615384615384...");
			i1 = 2;
			d2 = 1.3;
			Calculator calc = new Calculator().add(i1).divide(d2);
			System.out.println(calc.getResultAsInt());// 1
			System.out.println(calc.getResultAsLong());// 1
			System.out.println(calc.getResultAsFloat());//  1.538462
			System.out.println(calc.getResultAsDouble());// 1.538462
		}
		{
			System.out.println("2 / 1.3 = 1.5384615384615384...");
			i1 = 2;
			d2 = 1.3;
			Calculator calc = new Calculator(MathContext.UNLIMITED).add(i1).setMathContext(MathContext.DECIMAL128).divide(d2);
			System.out.println(calc.getResultAsInt());// 1
			System.out.println(calc.getResultAsLong());// 1
			System.out.println(calc.getResultAsFloat());//  1.5384616
			System.out.println(calc.getResultAsDouble());// 1.5384615384615385
		}
		{
			System.out.println("2.3 + 1.34 = 3.34");
			d1 = 2.3;
			d2 = 1.34;
			Calculator calc = new Calculator().add(d1).add(d2);
			System.out.println(calc.getResultAsInt());// 3
			System.out.println(calc.getResultAsLong());// 3
			System.out.println(calc.getResultAsFloat());// 3.64
			System.out.println(calc.getResultAsDouble());// 3.64
		}
		{
			System.out.println("2.3 * 1.34 = 3.082");
			d1 = 2.3;
			d2 = 1.34;
			Calculator calc = new Calculator().add(d1).multiply(d2);
			System.out.println(calc.getResultAsInt());// 2
			System.out.println(calc.getResultAsLong());// 2
			System.out.println(calc.getResultAsFloat());//  3.082
			System.out.println(calc.getResultAsDouble());// 3.082
		}
		{
			System.out.println("2.3 / 1.34 = 1.7164179104477...");
			d1 = 2.3;
			d2 = 1.34;
			Calculator calc = new Calculator().add(d1).divide(d2);
			System.out.println(calc.getResultAsInt());// 2
			System.out.println(calc.getResultAsLong());// 2
			System.out.println(calc.getResultAsFloat());//  1.716418
			System.out.println(calc.getResultAsDouble());// 1.716418
		}
		int price = new Calculator().add(100).multiply(1.05).getResultAsInt();
		System.out.println(price);
	}
}