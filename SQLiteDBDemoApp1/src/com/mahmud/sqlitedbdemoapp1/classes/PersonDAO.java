/**
 * 
 */
package com.mahmud.sqlitedbdemoapp1.classes;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class PersonDAO {
	
	String name;
	String email;

	public PersonDAO(String name, String email) {
		this();
		this.name = name;
		this.email = email;
	}

	/**
	 * 
	 */
	public PersonDAO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PersonDAO [name=" + name + ", email=" + email + "]";
	}

}
