package com.epam.preprod.traveling.domain.tour;

import java.util.Date;

import com.epam.preprod.traveling.domain.hottel.Hottel;

public class Tour {
	
	private Integer id;
	private int numberOfPeople;
	private Date from;
	private Date to;
	private Hottel hottel;
	private float price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeole(int numberOfPeoples) {
		this.numberOfPeople = numberOfPeoples;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public Hottel getHottel() {
		return hottel;
	}
	public void setHottel(Hottel hottel) {
		this.hottel = hottel;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
