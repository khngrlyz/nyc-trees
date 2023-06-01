package project2;

public class Tree implements Comparable<Tree> {

	
	private TreeSpecies species;
	private int tree_id;
	private String status;
	private String health;
	private String spc_latin;
	private String spc_common;
	private int zipcode;
	private String boroname;
	private double x_sp;
	private double y_sp;
	
	public Tree(int treeID, TreeSpecies species) throws IllegalArgumentException {
		this.setTree_id(treeID);
		this.setSpecies(species);
	}

	
	/**
	 * @return the species
	 */
	public TreeSpecies getSpecies() {
		return species;
	}
	
	/**
	 * @param species the species to set
	 */
	public void setSpecies(TreeSpecies species) throws IllegalArgumentException {
		if (species == null) {
			throw new IllegalArgumentException("Error. The species cannot be null.");
		}
		else{
			this.species = species;
		}
	}
	/**
	 * @return the tree_id
	 */
	public int getTree_id() {
		return tree_id;
	}
	/**
	 * 
	 * @param tree_id
	 * @throws IllegalArgumentException
	 */
	public void setTree_id(int tree_id) throws IllegalArgumentException {
		if (tree_id<0) {
			throw new IllegalArgumentException("Invalid Tree ID.");
	
		}else {
			this.tree_id = tree_id;
		}
				
	}
	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 * @throws IllegalArgumentException
	 */
	public void setStatus(String status) throws IllegalArgumentException{
		if ( ! (status.equalsIgnoreCase("Alive") || status.equalsIgnoreCase("Dead") || status.equalsIgnoreCase("Stump")
				||status.equalsIgnoreCase("") || status == null)) {
			throw new IllegalArgumentException("Invalid Status.");
		}else{
			this.status = status;
		}
	}

	/**
	 * 
	 * @return health of the tree
	 */
	public String getHealth() {
		return health;
	}

	/**
	 * 
	 * @param health
	 * @throws IllegalArgumentException
	 */
	public void setHealth(String health) throws IllegalArgumentException{
		if ( ! (health.equalsIgnoreCase("Good") || health.equalsIgnoreCase("Fair") || health.equalsIgnoreCase("Poor")
				||health.equalsIgnoreCase("") || health == null)) {
			throw new IllegalArgumentException("Invalid Health.");
			}
		else{
			this.health = health;
			}
	}

	/**
	 * 
	 * @return
	 */
	public String getSpc_latin() {
		return spc_latin;
	}

	/**
	 * 
	 * @param spc_latin
	 * @throws IllegalArgumentException
	 */
	public void setSpc_latin(String spc_latin) throws IllegalArgumentException{
		if (spc_latin == null) {
			throw new IllegalArgumentException("Invalid Latin Name.");
		}else{
			this.spc_latin = spc_latin;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getSpc_common() {
		return spc_common;
	}

	/**
	 * 
	 * @param spc_common
	 * @throws IllegalArgumentException
	 */
	public void setSpc_common(String spc_common) throws IllegalArgumentException{
		if(spc_common == null) {
			throw new IllegalArgumentException("Invalid Common Name.");
		}else{
			this.spc_common = spc_common;
		}
	}

	/*
	 * 
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * 
	 * @param zipcode
	 * @throws IllegalArgumentException
	 */
	public void setZipcode(int zipcode) throws IllegalArgumentException{
		if (zipcode<0 ||zipcode>99999){
			throw new IllegalArgumentException("Invalid zipcode, tree not registered.");
		}else{
			this.zipcode = zipcode;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getBoroname() {
		return boroname;
	}

	/**
	 * 
	 * @param boroname
	 * @throws IllegalArgumentException
	 */
	public void setBoroname(String boroname) throws IllegalArgumentException{
		if (boroname == null || !(boroname.equalsIgnoreCase("Manhattan")||boroname.equalsIgnoreCase("Bronx")||
				boroname.equalsIgnoreCase("Brooklyn")||boroname.equalsIgnoreCase("Queens")||boroname.equalsIgnoreCase("Staten Island"))){
			throw new IllegalArgumentException("Invalid borough name");
		}else {
		this.boroname = boroname;
		}
	}

	/**
	 * @return x
	 */
	public double getX_sp() {
		return x_sp;
	}


	/**
	 * 
	 * @param x_sp
	 */
	public void setX_sp(double x_sp) {
		this.x_sp = x_sp;
	}

	/**
	 * 
	 * @return
	 */
	public double getY_sp() {
		return y_sp;
	}

	/**
	 * 
	 * @param y_sp
	 */
	public void setY_sp(double y_sp) {
		this.y_sp = y_sp;
	}

	
	/**
	 * Equal method to see if the 2 trees are the same 
	 * @param a tree object  
	 * @return boolean if the trees are the same/equal each other
	 * 
	 */
	@Override
	public boolean equals(Object t) throws IllegalArgumentException {
		if (t == null) {
			throw new IllegalArgumentException("Invalid object parameter.");
		}
		if(t == this) {
			return true;
		}
		if(!(t instanceof Tree)) {
			return false;
		}
		Tree tree = (Tree) t;
		return this.getTree_id()==tree.getTree_id()&&
				this.getSpecies().equals(tree.getSpecies());
	}
	
	
	/**
	 *Overridden compareTo that returns 1 for greater, 0 for equal, -1 for lesser
	 */
	@Override
	public int compareTo(Tree anotherTree) throws IllegalArgumentException  {
		if(anotherTree == null) {
			throw new IllegalArgumentException("Invalid Parameter passed by the compareTo method in Tree class.");
		}
		if (this.getSpecies().getCommonName().equals(anotherTree.getSpecies().getCommonName())){
				
			if (this.getTree_id() == anotherTree.getTree_id()){
				return 0;
			}
			else if (this.getTree_id() > anotherTree.getTree_id()){
				return 1;
			}
			else{
				return -1;
			}
		}
		else if(this.getSpecies().getCommonName().compareToIgnoreCase(anotherTree.getSpecies().getCommonName())<0){
			return -1;
		}
		else{
			return 1;
		}
		
	}
	
	
		
				
	
	@Override
	/**
	 * Overrides Java's toString method; representation of the object
	 * @return String object that represents the object 
	 */
	public String toString(){
		String treeObj = ("Tree ID: " + this.getTree_id() + "/nSpecies: " + this.getSpc_common() + "/nZipcode: "
							+ String.format("%05d", this.getZipcode()) + "/nBorough: " + this.getBoroname());
			return treeObj;
		}


	

	
	
}
