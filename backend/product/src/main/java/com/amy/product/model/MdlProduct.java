package com.amy.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "products")
public class MdlProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private float price;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date created;
	@Column
    private Byte status;
    
	public MdlProduct() {
		super();
    }
    
	public MdlProduct(String name, float price, Date created, Byte status) {
		super();
		this.name = name;
		this.price = price;
		this.created = created;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}


	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SmCProduct [id=" + id + ", name=" + name + ", price=" + price + ", created=" + created + ", status="
				+ status + "]";
	}
}
