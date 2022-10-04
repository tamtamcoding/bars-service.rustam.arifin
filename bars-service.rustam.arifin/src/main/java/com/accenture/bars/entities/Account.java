package com.accenture.bars.entities;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private int accountId;
	@Column(name= "account_name")
	private String accountName;

	@Column(name= "date_created")
	private LocalDateTime dateCreated;

	@Column(name= "is_active")
	private String isActive;

	@Column(name= "last_edited")
	private String lastEdited;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<Billing> billing;
    
    
    public Account() {
    	//default contructor
    }

    	//getter and setter

	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public LocalDateTime getDatecreated() {
		return dateCreated;
	}


	public void setDatecreated(LocalDateTime datecreated) {
		this.dateCreated = datecreated;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getLastEdited() {
		return lastEdited;
	}


	public void setLastEdited(String lastEdited) {
		this.lastEdited = lastEdited;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public Customer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}


	public Set<Billing> getBilling() {
		return billing;
	}


	public void setBilling(Set<Billing> billing) {
		this.billing = billing;
	}

}