package data;
public class Company {

		private String companyName;
		private Address companyAddress;
		
		
		public Company(){
			companyAddress = new Address();
		}
		
		public String getName() {
			return companyName;
		}
		
		public void setName(String name) {
			this.companyName = name;
		}
		
		public Address getCompanyAddress(){
			return companyAddress;
		}

		public boolean equals(Company com){
			if (!companyName.equals(com.getName())) return false;
			if(!companyAddress.equals(com.getCompanyAddress())) return false;
			return true;
		}
}
