package de.uniba.dsg.serverless.model;

public class Customer {

	private String name;
	private String surname;
	private String birthday;
	private String mail;

	public Customer() {

	}

	public Customer(String name, String surname, String birthday, String mail) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", mail=" + mail + "]";
	}

}
