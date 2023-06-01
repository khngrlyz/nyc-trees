package project2;

import java.util.ArrayList;

/**
 * This class is used to store all the Tree objects
 * A simple linked list class. 
 */
public class TreeList {

	private static int counter;
    private Node head;
    
    // Default constructor
    public TreeList() {}
    
    private static int getCounter() {
        return counter;
    }
    private static void incrementCounter() {
        counter++;
    }
    
    // returns the number of elements in this list.
    public int counter() {
        return getCounter();
    }
    private class Node {
        // reference to the next node in the chain, or null if there isn't one.
        Node next;
        // data carried by this node. could be of any type you need.
        Tree data;
        // Node constructor
        public Node(Tree dataValue) {
            next = null;
            data = dataValue;
        }
        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(Tree dataValue, Node nextValue) {
            next = nextValue;
            data = dataValue;
        }
        // these methods should be self-explanatory
        public Tree getData() {
            return data;
        }
        @SuppressWarnings("unused")
        public void setData(Tree dataValue) {
            data = dataValue;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node nextValue) {
            next = nextValue;
        }
    }
    
    // appends the specified element to the end of this list.
    public void add(Tree data) throws IllegalArgumentException {
        if (data == null) {
        	throw new IllegalArgumentException("Invalid Argument.");
        }
    	// Initialize Node only in case of 1st element
        if (head == null) {
            head = new Node(data);
        }
        Node treeListTemp = new Node(data);
        Node treeListCurrent = head;
        // Let's check for NPE before iterate over treeListCurrent
        if (treeListCurrent != null) {
            // starting at the head node, go to the end of the list and then add element after last node
            while (treeListCurrent.getNext() != null) {
                treeListCurrent = treeListCurrent.getNext();
            }
            // the last node's "next" reference set to our new node
            treeListCurrent.setNext(treeListTemp);
        }
        // increment the number of elements variable
        incrementCounter();
    }
 
    
    
    public Tree get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        Node treeListCurrent = null;
        if (head != null) {
        	treeListCurrent = head.getNext();
            for (int i = 0; i < index; i++) {
                if (treeListCurrent.getNext() == null)
                    return null;
                treeListCurrent = treeListCurrent.getNext();
            }
            return treeListCurrent.getData();
        }
		return null;
       
    }
   
    public ArrayList<String> getMatchingSpecies(String speciesName) {
		// TODO Auto-generated method stub
    	
    	ArrayList<String> matched = new ArrayList<>();
    	matched.add(0,"All matching species:");
		for(int i = 0; i< this.counter(); i++) {
			if(this.get(i).getSpc_common().toLowerCase().contains(speciesName.toLowerCase())) {
				matched.add(this.get(i).getSpc_common());
				
			}
			
		}// if arraylist only has "All matching species:" in array list
		if(matched.size() == 1) {
			matched.remove(0);
			matched.add("There is no record of " + speciesName + " on NYC streets \n");
		}
		return matched;	
		
	}
    
    /**
     * 
     * @return integer, total number of trees in the list
     */
    public int getTotalNumberOfTrees() {
    	int totalNumTree = this.counter();
		return totalNumTree;
		
	}
	/**
	 * 
	 * @param commonName
	 * @return integer, total number of Tree objects in the list whose common name is the same as the given speciesName
	 */
	public int getCountByCommonName ( String commonName ) throws IllegalArgumentException{
		if(commonName == null) {
			throw new IllegalArgumentException("Invalid common name input.");
		}
		int speciesCount = 0;
		for (int i=0;i<this.counter();i++){
			if (this.get(i).getSpecies().getCommonName().toLowerCase().contains(commonName.toLowerCase())){
				speciesCount+=1;
			}
		}
		return speciesCount;
	} 
	
	


	/**
	 * 
	 * @param latinName
	 * @return integer, total number of Tree objects in the list whose Latin name is the same as speciesName
	 */
	public int getCountByLatinName ( String latinName) throws IllegalArgumentException {
		if(latinName == null) {
			throw new IllegalArgumentException("Invalid latin name input");
		}
		int speciesCount = 0;
		for (int i=0;i<this.counter();i++){
			if (this.get(i).getSpecies().getLatinName().toLowerCase().contains(latinName.toLowerCase())){
				speciesCount+=1;
			}
		}
		return speciesCount;
	}
	
	/**
	 * 
	 * @param boroName
	 * @return integer, total number of trees in that borough
	 */
	public int getCountByBorough ( String boroName ) throws IllegalArgumentException {
		if(boroName == null) {
			throw new IllegalArgumentException("Invalid borough name.");
		}
		int boroCount = 0;
		for (int i=0; i<this.counter();i++){
			if(this.get(i).getBoroname().equalsIgnoreCase(boroName)){
				boroCount+=1;
			}
		}
		return boroCount;
	}
	/**
	 * 
	 * @param commonName
	 * @param boroName
	 * @return
	 * @throws IllegalArgumentException
	 */
	public int getCountByCommonNameBorough ( String commonName, String boroName ) throws IllegalArgumentException {
		if(commonName == null || boroName == null) {
			throw new IllegalArgumentException("Invalid parameters");
		}
		int spcBoroCount = 0; 
		for (int i =0; i<this.counter();i++){
			if(this.get(i).getSpecies().getCommonName().toLowerCase().contains(commonName.toLowerCase())&&this.get(i).getBoroname().equalsIgnoreCase(boroName)){
				spcBoroCount+=1;
			}
		}
		return spcBoroCount;
	}
	/**
	 * 
	 * @param speciesName
	 * @param boroName
	 * @return integer, number of trees with Latin name speciesName that are located in boroName
	 */
	public int getCountByLatinNameBorough ( String latinName, String boroName ) throws IllegalArgumentException {
		if(latinName == null || boroName == null) {
			throw new IllegalArgumentException("Invalid parameters.");
		}
		int spcBoroCount = 0; 
		for (int i =0; i<this.counter();i++){
			if(this.get(i).getSpecies().getLatinName().toLowerCase().contains(latinName.toLowerCase())&&this.get(i).getBoroname().equalsIgnoreCase(boroName)){
				spcBoroCount+=1;
			}
		}
		return spcBoroCount;
	}
	
	 @Override 
		/**
		 * Overrides toString method  
		 * @return String, object representation 
		 */
	    public String toString() {
	        String output = "";
	        if (head != null) {
	            Node treeListCurrent = head.getNext();
	            while (treeListCurrent != null) {
	                output += "[" + treeListCurrent.getData().toString() + "]";
	                treeListCurrent = treeListCurrent.getNext();
	            }
	        }
	        return output;
	    }
}
