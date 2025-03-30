/** This abstract super class manages the different types of competitors
 * Implements comparable interface to distinguish between different competitors
 * Provides abstract methods for subclasses
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

abstract public class Competitors implements Comparable<Competitors>{
	
	protected String id;
	protected CompetitorName compName;
	protected int age;
	protected String level;
	protected String country;
	protected String game;
	
	
	/**Constructor to create competitor object
	 * @param num : competitor number
	 * @param age : age of competitor
	 * @param name: name object
	 * @param level: level of competitor
	 * @param compCountry: country
	 */
	public Competitors(String id, int age, CompetitorName name, String level, String compCountry, String game) {
				
		this.id = id;
		compName = name;
		this.age = age;
		this.level = level;
		country = compCountry;
		this.game = game;		
	}
		
	/**Set competitor number 
	 * @param num: competitor number
	 * @return void
	 */
	public void setID(String str) { id = str;}
	
	/**Set competitor's game
	 * @param String: game
	 * @return void
	 */
	public void setGame(String game) { this.game = game;}
	
	/**Set age of competitor 
	 * @param num: competitor age
	 * @return void
	 */
	public void setAge(int num) { age = num;}
	
	
	/**Set country of competitor 
	 * @param num: competitor age
	 * @return void
	 */
	public void setCountry(String compCountry) {  country = compCountry;}
	
	
	/**Set level of competitor 
	 * @param lev: level
	 * @return void
	 */
	public void setLevel (String lev) { level = lev;}
	
	/** Set name of competitor 
	 * @param name: competitor name
	 * @return void
	 */
	public void setCompName (CompetitorName name) { compName = name;}
	
	/**
	 * Dereference competitor object
	 * @param c: competitor object
	 * @return void
	 */
	public void deleteComp(Competitors c) {c = null;}
	
	/**
	 * get competitor number of competitor
	 * @param no input parameters 
	 * @return id
	 */
	public String getID() {return id;}
	
	/**
	 * get competitor name
	 * @param no input parameters
	 * @return compName
	 */
	public CompetitorName getCompName() {return compName;}
	

	/**
	 * get age
	 * @param no input parameters
	 * @return age
	 */
	public int getage() {return age;}
	
	/**
	 * get game
	 * @param no input parameters
	 * @return game
	 */
	public String getGame() {return game;}
	
	/**
	 * get level of competitor
	 * @param no input parameters
	 * @return level
	 */
	public String getLevel() {return this.level;}
	
	/**
	 * get country
	 * @return country
	 */
	public String getCountry() {return this.country;}
	
	
	/**
	 * get full details of the competitor
	 * @param no input parameters
	 * @return tempDetails
	 */
	
	//public abstract String getFullDetails();
	public String getFullDetails() {
		
		String tempDetails = "Competitor Number " + id + ", Name " + compName.getFullName() +  
				", Country " + country + "\n" + compName.getFirstName() + " is a " + level 
				+ " player of " + this.game + " game with overall score of " + this.getOverallScore() + "." + "\n";
			return tempDetails;
		}

	/**
	 * get short details
	 * @param no input parameters
	 * @return tempString
	 */
	public String getShortDetails() {
		String tempString = "CN " + id + " (" + compName.getInititals() + ") has overall score " + this.getOverallScore() + "\n";
		return tempString;
	}
	
	/**
	 * Abstract method to get Overall Score of a competitor 
	 */
	public abstract double getOverallScore();
	
	/**
	 * Abstract method to get Scores of a competitor
	 */
	public abstract int[] getScoreArray();
	
	
	/**
	 * Abstract method to set scores of a competitor 
	 */
	public abstract void SetScoreArray(int scoreValue, int arrayNum);
	
	
	/**
	 * Comparable interface Override method to distinguish between 2 competitors
	 * @param otherDetails: competitor to compare
	 */
	@Override
	public int compareTo(Competitors otherDetails)
	{
		return id.compareTo(otherDetails.getID());
	}  
	
    /**
     * Override method to define toString value of competitors
     * @return A multi-line string containing the number, name, game and country.
     */
	@Override
    public String toString()
    {
        return String.format("%-10s", id ) + String.format("%-30s", compName.getLastCommaFirst()) + String.format("%-15s", game) + String.format("%-15s", country) ;}

}

