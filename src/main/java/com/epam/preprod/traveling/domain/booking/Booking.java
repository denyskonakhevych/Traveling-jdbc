package com.epam.preprod.traveling.domain.booking;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.domain.tour.Tour;
import com.epam.preprod.traveling.domain.user.User;

public class Booking {
	private Integer id;
	private Tour tour;
	private User orderedBy;
	private Analitic managedBy;
	private String status;
	private float totalPrice;

	public Booking() {
		super();
	}

	public Booking(Integer id, Tour tour, User orderedBy, Analitic managedBy,
			String status, float totalPrice) {
		super();
		this.id = id;
		this.tour = tour;
		this.orderedBy = orderedBy;
		this.managedBy = managedBy;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public User getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(User orderedBy) {
		this.orderedBy = orderedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Analitic getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(Analitic managedBy) {
		this.managedBy = managedBy;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", tour=" + tour + ", orderedBy="
				+ orderedBy + ", managedBy=" + managedBy + ", status=" + status
				+ ", totalPrice=" + totalPrice + "]";
	}
}
