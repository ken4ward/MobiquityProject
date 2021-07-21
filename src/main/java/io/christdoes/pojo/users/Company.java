package io.christdoes.pojo.users;

public class Company{
	private String bs;
	private String catchPhrase;
	private String companyName;

	public Company(){}

	public Company(String bs, String catchPhrase, String companyName) {
		this.bs = bs;
		this.catchPhrase = catchPhrase;
		this.companyName = companyName;
	}

	public void setBs(String bs){
		this.bs = bs;
	}

	public String getBs(){
		return bs;
	}

	public void setCatchPhrase(String catchPhrase){
		this.catchPhrase = catchPhrase;
	}

	public String getCatchPhrase(){
		return catchPhrase;
	}

	public void setName(String name){
		this.companyName = companyName;
	}

	public String getName(){
		return companyName;
	}

	@Override
 	public String toString(){
		return 
			"Company{" + 
			"bs = '" + bs + '\'' + 
			",catchPhrase = '" + catchPhrase + '\'' + 
			",name = '" + companyName + '\'' +
			"}";
		}
}
