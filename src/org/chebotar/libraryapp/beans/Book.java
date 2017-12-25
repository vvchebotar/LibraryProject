package org.chebotar.libraryapp.beans;

import java.time.LocalDate;

public class Book {
	 	   
	private String id;
	private String name;
	private String author;
	private int publicationDate;
	private LocalDate dateOfReceipt;
	private String shortDescription;
	private int numberOfPages;

	public Book() {	 
	}
	public Book(String id, String name, String author, int publicationDate, LocalDate dateOfReceipt, String shortDescription, int numberOfPages) {
		this.id = id;
		this.setName(name);
		this.setAuthor(author);
		this.setPublicationDate(publicationDate);
		this.setDateOfReceipt(dateOfReceipt);
		this.setShortDescription(shortDescription);
		this.setNumberOfPages(numberOfPages);	       
	}
	   
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(int publicationDate) {
		this.publicationDate = publicationDate;
	}

	public LocalDate getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(LocalDate dateOfReceipt) {		
		this.dateOfReceipt = dateOfReceipt;		
	}
	
	public void setDateOfReceipt(long dateOfReceipt) {		
		this.dateOfReceipt = LocalDate.ofEpochDay(dateOfReceipt);		
	}


	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}	 
}
