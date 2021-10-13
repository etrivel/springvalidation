package com.example.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="TokenClass")
public class TokenClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    public TokenClass() {
		super();
	}

	public TokenClass(long tokenid, String confirmationToken, Date createdDate, EntityModel entityModel) {
		super();
		this.tokenid = tokenid;
		this.confirmationToken = confirmationToken;
		this.createdDate = createdDate;
		this.entityModel = entityModel;
	}

	@Column(name="date")
    private Date createdDate;

    @OneToOne(targetEntity = EntityModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private EntityModel entityModel;

 

    public TokenClass(EntityModel entityModel) {
        this.entityModel = entityModel;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public EntityModel getEntityModel() {
		return entityModel;
	}

	public void setEntityModel(EntityModel entityModel) {
		this.entityModel = entityModel;
	}

    // getters and setters
}