/**
 * 
 */
package com.mahmud.contactlistapp1.classes;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class ContactDAO {
	
	private int contactID;
	private String name, email, phone, address;

	/**
	 * @param contactID
	 * @param name
	 * @param email
	 * @param phone
	 * @param address
	 */
	public ContactDAO(int contactID, String name, String email, String phone,
			String address) {
		this(name, email, phone, address);
		this.contactID = contactID;
	}

	/**
	 * @param name
	 * @param email
	 * @param phone
	 * @param address
	 */
	public ContactDAO(String name, String email, String phone, String address) {
		this();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * 
	 */
	public ContactDAO() {
		// TODO Auto-generated constructor stub
		this.contactID = 0;
	}

	/**
	 * @return the contactID
	 */
	public int getContactID() {
		return contactID;
	}

	/**
	 * @param contactID the contactID to set
	 */
	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + contactID + "] : " + name;
	}

}
