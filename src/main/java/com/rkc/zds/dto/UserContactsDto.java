package com.rkc.zds.dto;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PCM_GROUP_MEMBERS database table.
 * 
 */
@Entity
@Table(name="PCM_USER_CONTACTS")
public class UserContactsDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="USER_ID")
	private int userId;

	@Column(name="CONTACT_ID")
	private int contactId;

    public UserContactsDto() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getContactId() {
		return this.contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

}
