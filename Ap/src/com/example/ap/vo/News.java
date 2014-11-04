package com.example.ap.vo;

import java.io.Serializable;

public class News implements Serializable {

	private String image;
	private String name;
	private String author;
	private String price;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public News(String image, String name, String author, String price) {
		super();
		this.image = image;
		this.name = name;
		this.author = author;
		this.price = price;
	}
	@Override
	public String toString() {
		return "News [image=" + image + ", name=" + name + ", author=" + author
				+ ", price=" + price + "]";
	}
	public News() {
		super();
	}

}
