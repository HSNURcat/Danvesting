package com.dandan.danvesting.stock.model;

public class StockBarResult {
	private Number c;	// 설정기간 동안의 종가
	private Number h;	// 설정기간 동안 가장 높은 가격
	private Number l;	// 설정기간 동안 가장 낮은 가격
	private Number n;	// 집계창 안에 있는 매매거리 숫자들
	private Number o;	// 시장 시작 가격
	private int t;	// 집계창 시작시간 Unix Msec timestamp
	private Number v;	//설정 기간동안의 거래량
	private Number vw;	//거래량 가중 평균가격
	public Number getC() {
		return c;
	}
	public void setC(Number c) {
		this.c = c;
	}
	public Number getH() {
		return h;
	}
	public void setH(Number h) {
		this.h = h;
	}
	public Number getL() {
		return l;
	}
	public void setL(Number l) {
		this.l = l;
	}
	public Number getN() {
		return n;
	}
	public void setN(Number n) {
		this.n = n;
	}
	public Number getO() {
		return o;
	}
	public void setO(Number o) {
		this.o = o;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public Number getV() {
		return v;
	}
	public void setV(Number v) {
		this.v = v;
	}
	public Number getVw() {
		return vw;
	}
	public void setVw(Number vw) {
		this.vw = vw;
	}
}
