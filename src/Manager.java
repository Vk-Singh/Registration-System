import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/** This manager class manages the program workflow
 * Import Data from input files
 * Call GUI creation classes
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class Manager {

	private String [] inputFilePath = {"Dota2.csv", "Football.csv", "Boxing.csv"};
	private String reportFilePath = "Report.txt";
	private CompetitorList compList = new CompetitorList();
	
	// Constructor
	public Manager() {
			
		/* Reading input files and making competitor objects 
		 */
		for (int i=0; i<inputFilePath.length;i++) {
			this.populate(inputFilePath[i]);
		}
		
		// Validates if report file already exists and remove if present
		reportFileValidation();
		
		// Create GUI
		TableGUI gui = new TableGUI(compList);
        gui.setVisible(true);
        }
	
	
	
	/** Reads file with given path, extracting competitor's data
	 * Create competitor objects
	 * Add them to the list of competitors
	 * Blank lines are skipped
	 * @param filepath: complete path of the input file including file name and extension
	 * @return void
	 */
	private void populate(String filePath) {
		try {		
			File f = new File(filePath); 
			Scanner scanner = null;
			int count =1;
			
			scanner = new Scanner(f);
			 
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				/*Checking if line is not empty
				 * Calling processLine method if not empty
				 */
				if (inputLine.length() != 0 && count != 1){
					processLine(inputLine, filePath);					
				}
				count+=count;
				
			}
			scanner.close();
		}catch (FileNotFoundException e) {
			System.out.println( filePath + " not found ");
			System.exit(0);
			
		}
		
	} 	
	
	/** Processes the line string, extracts data, create competitor objects and add them to competitor list
	 * Splits the line by "," separator 
	 * Front and back white-spaces are removed while splitting
	 * Accepts only 5 scores
	 * Assumes headers are there in the file
	 * @param inputLine: A single string line
	 * @return void
	 */
	
	private void processLine(String inputLine, String filePath) {
		// Initializing local variables 
		String id = ""; 
		int age = 0;
		String name, level;
		String [] lineData;
		String country;
		int [] tempInt = new int[5];
		boolean validScores = true;
		int fileColumns = 0;
		String game;
		
		// Trim and exclude white spaces
		lineData = inputLine.trim().split("\\s*,\\s*"); // comma separated file
		//lineData = inputLine.trim().split("\\s*\\t\\s*"); //  in case of Tab separated input text file
		 
		// defining number of columns in each type of input file
		switch(filePath) {
		case "Dota2.csv":
			fileColumns = 11;
			break;
		case "Football.csv":
			fileColumns = 12;
			break;
		case "Boxing.csv":
			fileColumns = 13;
			break;
		}
		
		/*Checking Input File column count and if not equal to above mentioned length, making valid scores as false
		 * resulting in skipping of Competitor
		 */
		
		if (lineData.length !=fileColumns) {
			validScores = false;}
		
		// Parsing values into variables
		id = lineData[0];
		name = lineData[2];
		level = lineData[3];
		country = lineData[4];
		game = lineData[5];
		
		/* Parsing scores values from string to int, if input file not in proper format,
		 * the competitor validScores variable will be set to false and the competitor will be skipped
		 */
	
		try {
			age = Integer.parseInt(lineData[1]);
			// Creating scores array 
			int scoresLength = lineData.length -5 ;
			String scores[] = new String[5];
			// creating new array from lineData
			System.arraycopy(lineData, scoresLength, scores, 0, 5);
			
			// Converting String array to int
			for (int i=0; i < scores.length; i++) {
				tempInt[i] = Integer.parseInt(scores[i]);
				if (tempInt[i] > 5 || tempInt[i] <0 ) {
					validScores = false;
				}
			}
		}catch(NumberFormatException  nmf) { System.out.println("In error");
		validScores = false;}
		
		if (validScores) {
			CompetitorName tempName = new CompetitorName (name);
			/* Creating new competitor object based on value of game column in input file and add to CompetitorList
			 * Also checks if competitor is already present, by searching based on id
			 * Skips if already present
			 */
			switch(game) {
			case "Dota2":
				DotaCompetitor tempComp = new DotaCompetitor (id, age,tempName,  level, country, tempInt, game) ;
				if (compList.findByID(tempComp.getID()) == null) {
					compList.add_details(tempComp);	
				}					
				break;
			case "Football":
				fileColumns = 12;
				FootballCompetitor comp = new FootballCompetitor (id, age, tempName, level, country, tempInt, game, lineData[6] ) ;
				if (compList.findByID(comp.getID()) == null) {
					compList.add_details(comp);	
				}					
				break;
			case "Boxing":
				BoxingCompetitor tempComp1 = new BoxingCompetitor (id, age, tempName, level, country, tempInt, game, Float.parseFloat(lineData[6]), Float.parseFloat(lineData[7]) ) ;
				if (compList.findByID(tempComp1.getID()) == null) {
					compList.add_details(tempComp1);	
				}						
				break;
			}
		}else {System.out.print("Invalid input values for competitor number : " + lineData[0] + " Skipping competitor\n");
		}
	}
	
	
	
	/** Method to check if report file exists
	 * Delete if already exist so that program can overwrite
	 * @param no input parameters 
	 * @return void
	 */
	private void reportFileValidation() {
		File f= new File(reportFilePath);    
		boolean fileExists = f.isFile();
		if (fileExists) {
			try{         
				//returns Boolean value
				if(!f.delete()){System.out.println(" Report file already exist. Delete failed");  }  
			}catch(Exception e){e.printStackTrace();}  
			}
		}	
		

}