package project2;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class NYCStreetTrees {

	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
		
		//Ask user for file name 
		String fileName = null;
				
		//Check user did input something 
		if (args.length==0){
			System.err.println("No input given");
			System.exit(0);
		}
		else{
			fileName = args[0];
		}
				
		//String to store all the data read 
				ArrayList<ArrayList<String>> dataTable = new ArrayList<ArrayList<String>>();
				
				//Use try catch block to open and make sure file exists 
				try{
		     
					Scanner fileScn = new Scanner(new File(fileName));
					
					while (fileScn.hasNextLine()){
						ArrayList<String> dataLine = splitCSVLine(fileScn.nextLine());
						dataTable.add(dataLine);
					}
					fileScn.close();
					
				}
				catch (FileNotFoundException e){
					System.err.println("Error: The file " + fileName + " cannot be opened.");
					System.exit(0);
				}
				
				
				//remove first line of 2D ArrayList because they are headers for the columns
				dataTable.remove(0);
				
				//create TreeList with each tree as an object stored inside 
				TreeList treeList = new TreeList();
				
				for (int i=0; i<dataTable.size();i++){
					try{
						int id = Integer.parseInt(dataTable.get(i).get(0));
						@SuppressWarnings("unused")
						String status = dataTable.get(i).get(6);
						@SuppressWarnings("unused")
						String health = dataTable.get(i).get(7);
						String spc_latin = dataTable.get(i).get(8);
						String spc_common = dataTable.get(i).get(9);
						@SuppressWarnings("unused")
						int zip = Integer.parseInt(dataTable.get(i).get(25));
						@SuppressWarnings("unused")
						String boro = dataTable.get(i).get(29);
						@SuppressWarnings("unused")
						double x = Double.parseDouble(dataTable.get(i).get(39));
						@SuppressWarnings("unused")
						double y = Double.parseDouble(dataTable.get(i).get(40));
						TreeSpecies toAddSpecies = new TreeSpecies(spc_common, spc_latin);
						Tree toAddTree = new Tree(id, toAddSpecies);
						treeList.add(toAddTree);
						} 
					catch (Exception e){
						continue;
					}
				}
				
				//Scanner for user input 
				Scanner input = new Scanner(System.in);
				
				while (true){
					
					//ask user for tree species 
					System.out.println("Enter the tree species to learn more about it (\"quit\" to stop): ");
					String userSpecies = input.nextLine().toLowerCase();
					
					//break out of loop if user inputs quit 
					if (userSpecies.equals("quit")){
						break;
					}
					
					
					//print out all matching species
					System.out.println("All matching species: \n");
					ArrayList<String> matchList = treeList.getMatchingSpecies(userSpecies);
					for(int i = 0; i<matchList.size(); i++){
						System.out.println(matchList.get(i));
					}
				
					//print out species popularity in the city 
					//nyc tree counts 
					int nycTotalSpecies = treeList.getCountByCommonName(userSpecies);
					int nycTotalTrees = treeList.getTotalNumberOfTrees();
					double nycPercent = ((double)nycTotalSpecies/(double)nycTotalTrees)*100;
					
					//Manhattan tree counts 
					int manTotalSpecies = treeList.getCountByCommonNameBorough(userSpecies, "manhattan");
					int manTotalTrees = treeList.getCountByBorough("Manhattan");
					double manPercent = ((double)manTotalSpecies/(double)manTotalTrees)*100;
					
					//Bronx tree counts
					int bronxTotalSpecies = treeList.getCountByCommonNameBorough(userSpecies, "bronx");
					int bronxTotalTrees = treeList.getCountByBorough("bronx");
					double bronxPercent = ((double)bronxTotalSpecies/(double)bronxTotalTrees)*100;
					
					//Brooklyn tree counts
					int brookTotalSpecies = treeList.getCountByCommonNameBorough(userSpecies, "Brooklyn");
					int brookTotalTrees = treeList.getCountByBorough("Brooklyn");
					double brookPercent = ((double)brookTotalSpecies/(double)brookTotalTrees)*100;
					
					//Queens tree counts
					int queensTotalSpecies = treeList.getCountByCommonNameBorough(userSpecies, "Queens");
					int queensTotalTrees = treeList.getCountByBorough("Queens");
					double queensPercent = ((double)queensTotalSpecies/(double)queensTotalTrees)*100;
					
					//Staten island tree counts 
					int statenTotalSpecies = treeList.getCountByCommonNameBorough(userSpecies, "Staten Island");
					int statenTotalTrees = treeList.getCountByBorough("Staten Island");
					double statenPercent = ((double)statenTotalSpecies/(double)statenTotalTrees)*100;

					//Print output to user 
					System.out.println("\nPopularity in the city: ");
					System.out.printf("%-15s %,-5d  %1s %,d %s %.2f %s", "NYC: ", nycTotalSpecies, "(", nycTotalTrees, ")", nycPercent, "%");
					System.out.println("");
					System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Manhattan: ", manTotalSpecies, "(", manTotalTrees, ")", manPercent, "%");
					System.out.println("");
					System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Bronx: ", bronxTotalSpecies, "(", bronxTotalTrees, ")", bronxPercent, "%");
					System.out.println("");
					System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Brooklyn: ", brookTotalSpecies, "(", brookTotalTrees, ")", brookPercent, "%");
					System.out.println("");
					System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Queens: ", queensTotalSpecies, "(", queensTotalTrees, ")", queensPercent, "%");
					System.out.println("");
					System.out.printf("%-15s %,-5d  %s %,d %s %.2f %s", "Staten Island: ", statenTotalSpecies, "(", statenTotalTrees, ")", statenPercent, "%");
					System.out.println("\n");
				}
				
				
				//close all scanner objects
				input.close();
				
					
			}
			
			
			/**
			 * Splits the given line of a CSV file according to commas and double quotes
			 * (double quotes are used to surround multi-word entries that may contain commas). 
			 * 
			 * @param textLine  line of text to be parsed
			 * @return an ArrayList object containing all individual entries/tokens
			 *         found on the line.
			 */
			public static ArrayList<String> splitCSVLine(String textLine) {
				ArrayList<String> entries = new ArrayList<String>();
				int lineLength = textLine.length();
				StringBuffer nextWord = new StringBuffer();
				char nextChar;
				boolean insideQuotes = false;
				boolean insideEntry= false;
				
				//iterate over all characters in the textLine
				for (int i = 0; i < lineLength; i++) {
					nextChar = textLine.charAt(i);
					
					//handle smart quotes as well as regular quotes 
					if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
						//change insideQuotes flag when nextChar is a quote
						if (insideQuotes) {
							insideQuotes = false;
							insideEntry = false; 
						}
						else {
							insideQuotes = true; 
							insideEntry = true; 
						}
					}
					else if (Character.isWhitespace(nextChar)) {
						if  ( insideQuotes || insideEntry ) {
							// add it to the current entry
							nextWord.append( nextChar );
						}
						else  { // skip all spaces between entries 
							continue;
						}
					}
					else if ( nextChar == ',') {
						if (insideQuotes) //comma inside an entry 
							nextWord.append(nextChar);
						else { //end of entry found 
							insideEntry = false; 
							entries.add(nextWord.toString());
							nextWord = new StringBuffer();
						}
					}
					else {
						//add all other characters to the nextWord 
						nextWord.append(nextChar);
						insideEntry = true; 
					}

				}
				// add the last word (assuming not empty)
				// trim the white space before adding to the list
				if (!nextWord.toString().equals("")) {
					entries.add(nextWord.toString().trim());
				}

				return entries;
			}

		
	}

