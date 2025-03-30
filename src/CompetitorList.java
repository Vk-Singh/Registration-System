import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;

/** The CompetitorList class implements a mechanism to store and manage competitors in a list 
 * 
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class CompetitorList {
	//private ArrayList <DotaCompetitor> compList;
	private ArrayList <Competitors> compList;
	/** Creates a competitor Array List
	 * @param no input parameters
	 * @return void
	 */
	public CompetitorList() {
		//compList = new ArrayList<DotaCompetitor> ();	
		compList = new ArrayList<Competitors> ();
	}
	
	/** Returns the competitor list instance object
	 * @param no input parameters
	 * @return compList : competitor Array list
	 */
	public ArrayList <Competitors> getCompList(){
		return compList;
	}
	
	
	/** Returns the size of competitor list object 
	 * @param no input parameters
	 * @return the size of the competitor list
	 */
	public int getNumOfComp() {
		return compList.size();
	}

	/**
	 * Write text to file
	 * @param filePath: Full path of the file including file name and extension
	 * Appends the file
	 * @param report : text to be written
	 * @return void
	 */
	public  void writeToFile(String filepath, String report) {
	
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filepath, true);
		    fw.write(report);
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filepath + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    //System.exit(1);
		 }
	}
	
	/** Create overall score's list of all competitors
	 * @param no input parameters
	 * @return overallScoresList: ArrayList <Double> of overall scores
	 */
	public ArrayList <Double> getOverallScoreList() {
		ArrayList <Double> overallScoresList;
		overallScoresList = new ArrayList<Double> ();		
		for (Competitors c: compList) {
			// Ignoring invalid input details
				if (c.getOverallScore() != 0) {overallScoresList.add(c.getOverallScore());}
		}
		return overallScoresList;
	}
			

/** Calculate the highest overall score's competitor from competitor list
 * If there are multiple competitors with same highest overall score, it will return all respective competitors
 * @param no input parameters  
 * @return highestScoreComp: ArrayList <Competitor> of all competitors who scored highest overall score
 */
	public  ArrayList <Competitors> getHighestOverallScoreCompetitor() {
		ArrayList <Double> a = this.getOverallScoreList();
		// calculate maximum value of ArrayList
		double b = Collections.max(a);
		ArrayList <Competitors> highestScoreComp = new ArrayList <Competitors> ();
		for (Competitors c: compList){	
				if (c.getOverallScore() == b) {				
					highestScoreComp.add(c);
				}
			}							
		return highestScoreComp;
	}
	
	
	/** Calculate highest overall score among all competitors
	 * @param no input parameters
	 * @return maxScore: Highest overall score
	 */
	public double getHighestOverallScore() {
		ArrayList <Double> a = this.getOverallScoreList();
		//Calculating maximum value of ArrayList
		double maxScore = Collections.max(a);		
		return maxScore;
		
	}
	
	/** Checks the competitor list to find the competitor object
	 * @param id : competitor number
	 * @return c: Competitor object if found, otherwise null
	 */
    public Competitors findByID(String  id)
    {		
    	for (Competitors c : compList){
    		if (c.getID().equals(id)){
    			return c;
    		}
    	}
    	return null;
    }
   
    /** Add competitors to list
     * @param tempComp: object of type Competitors or it's subclasses
     * @return null
     * */
	public void add_details(Competitors tempComp) {
		compList.add(tempComp);
	}
    
 
    public void generateReport() {
    	String reportFilePath = "Report.txt";
		// Making report file structure
		this.writeToFile(reportFilePath,String.format("The Report\n\n"));
		this.writeToFile(reportFilePath,String.format("%-10s %-10s %-30s %-20s %-15s %-10s%n","Number", "Age", "Name", "Level", "Game", "Short Details"));
    	
		
		for (Competitors c : compList ) {
			this.writeToFile(reportFilePath, String.format("%-10s %-10s %-30s %-20s %-15s %-10s", c.getID(),c.getage(),
					c.getCompName().getFullName(),c.getLevel(),c.getGame(),c.getShortDetails()));
		}
		this.writeToFile(reportFilePath, "\n\n" + "WINNER BASED ON OVERALL Scores accross all games\n");
		
		
		ArrayList <Competitors> comp = this.getHighestOverallScoreCompetitor();
		// Checking if multiple competitors have joint highest overall Scores
		if (comp.size() > 1) {
			this.writeToFile(reportFilePath, "There is a tie between " + comp.size() + " players. Below are the details of each competitor: \n\n");
		}
		for (Competitors c : comp ) {
			String tempReport = c.getFullDetails();
			this.writeToFile(reportFilePath, tempReport + "\n");
		}
    	
    }

}
  
    
    

