package io.christdoes.pojo.users;

public class UserItem{
	private int id;
	private String website;
	private String phone;
	private String name;
	private String email;
	private String username;

	private Address address;
	private Company company;

	public UserItem(){}

	public UserItem(int id, String website, String phone, String name, String email, String username, Address address, Company company) {
		this.id = id;
		this.website = website;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.username = username;
		this.address = address;
		this.company = company;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCompany(Company company){
		this.company = company;
	}

	public Company getCompany(){
		return company;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"website = '" + website + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",company = '" + company + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
