package com.example.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


    @Entity
	@Table(name="users")
	public class EntityModel {

	    public EntityModel() {
			super();
		}

		public EntityModel(long userid, String emailId, String password, String firstName, String lastName,
				boolean isEnabled) {
			super();
			this.userid = userid;
			this.emailId = emailId;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.isEnabled = isEnabled;
		}

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="user_id")
	    private long userid;

		@Column(name="email")
	    private String emailId;

		@Column(name="password")
	    private String password;

	    @Column(name="first_name")
	    private String firstName;

	    @Column(name="last_name")
	    private String lastName;

	    private boolean isEnabled;

		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public boolean isEnabled() {
			return isEnabled;
		}

		public void setEnabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}

		// getters and setters methods
	}

