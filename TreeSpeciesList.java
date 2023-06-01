package project2;

import java.util.ArrayList;

/**
 * This class is used to store all the unique TreeSpecies objects
 */

public class TreeSpeciesList extends ArrayList<TreeSpecies> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor 
	 * @param no parameter
	 * @return an empty list for TreeSpecies objects 
	 */
	public TreeSpeciesList() {
		//TreeSpeciesList treeSpeciesList = new TreeSpeciesList();
		//ArrayList<TreeSpecies> treeSpeciesList = new ArrayList<TreeSpecies>();
	}
	
	/**
	 *  returns a list of species whose common name contains keyword as a substring
	 */
	public TreeSpeciesList getByCommonName (String keyword) throws IllegalArgumentException {
		if(keyword == null) {
			throw new IllegalArgumentException("Invalid Common Name.");
		}
		
		ArrayList<TreeSpecies> matchList = new TreeSpeciesList();
		for (int i =0;i<this.size();i++){
			TreeSpecies thisSpc = this.get(i);
			String thisSpcLower = thisSpc.getCommonName().toLowerCase();
			if (thisSpcLower.contains(keyword.toLowerCase())){
				if (!(matchList.contains(thisSpc))){
					matchList.add(thisSpc);	
				}
			}
			else {
				continue;
			}
		}
		if (matchList.size() == 0) {
			return null;
		}
		else{
			return  (TreeSpeciesList) matchList;
		}
		
			
	}
	
	/**
	 *  returns a list of species whose Latin name contains keyword as a substring
	 */
	public TreeSpeciesList getByLatinName (String keyword) throws IllegalArgumentException{
		if(keyword == null) {
			throw new IllegalArgumentException("Invalid Latin Name.");
		}
		
		ArrayList<TreeSpecies> matchList = new TreeSpeciesList();
		for (int i =0;i<this.size();i++){
			TreeSpecies thisSpc = this.get(i);
			String thisSpcLower = thisSpc.getLatinName().toLowerCase();
			
			if (thisSpcLower.contains(keyword.toLowerCase())){
				if (!(matchList.contains(thisSpc))){
					matchList.add(thisSpc);
					
				}
			}
			else {
				continue;
			}
		}if (matchList.size() == 0) {
			return null;
		}
		else{
			return  (TreeSpeciesList) matchList;
		}
	}

}
	


