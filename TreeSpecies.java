package project2;

public class TreeSpecies {
	private String commonName = "";
	private String latinName = "";
	
	public TreeSpecies(String commonName, String latinName) throws IllegalArgumentException {
		this.setCommonName(commonName);
		this.setLatinName(latinName);
		
	}
	
	
	public String getCommonName() {
		return this.commonName;
		
	}
	public void setCommonName( String commonName) throws IllegalArgumentException{
		if(commonName == null) {
			throw new IllegalArgumentException("Invalid Common Name.");
		}
		else{
			this.commonName = commonName;
		}
	}
	public String getLatinName() {
		return this.latinName;
	}
	public void setLatinName(String latinName) throws IllegalArgumentException {
		if(latinName == null) {
			throw new IllegalArgumentException("Invalid Latin Name.");
		}
		else{
			this.latinName = latinName;
		}
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof TreeSpecies)) {
			return false;
		}
		
		TreeSpecies c = (TreeSpecies) o;
		
		return ((this.getCommonName().toLowerCase().compareTo(c.getCommonName().toLowerCase()) == 0) && 
				(this.getLatinName().toLowerCase().compareTo(c.getLatinName().toLowerCase()) == 0)) ; 
		
	}
	
	

}
