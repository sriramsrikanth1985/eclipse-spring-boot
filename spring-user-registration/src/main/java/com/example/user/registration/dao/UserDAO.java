package com.example.user.registration.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="users")
public class UserDAO {

//@Length(max = 80, message = "error.uname.length")
	
	public enum Gender {
	    M,F;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Email(message = "error.email.notvalid")
	@NotEmpty(message = "error.email.empty")
	@Column(name="email")
	private String emailAddress;
	
	@Column(name="username")
	@NotEmpty(message = "error.username.empty")
	@Length(min = 2, message = "error.username.lengthMin")
	@Length(max = 20, message = "error.username.lengthMax")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="fname")
	private String firstname;
	
	@Column(name="lname")
	private String lastname;
	
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="phone")
	private String phoneNum;
	
	@Column(name="address")
	private String address;
	
	@Column(name="pincode")
	private long pincode;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Long getPincode() {
		return pincode;
	}
	
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id:"+this.id+" Email:"+this.emailAddress+" Username:"+this.username+" First Name:"+this.firstname+" Last Name:"+this.lastname+" Age:"+this.age
				+" Gender:"+this.gender+" Phone:"+this.phoneNum+" Address:"+this.address+" Pincode:"+this.pincode;
	}
	
	
}
