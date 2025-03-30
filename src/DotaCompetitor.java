
/** The DotaCompetitor class specifies characteristics of Dota 2 competitors
 * and provide methods to set and access required competitor's values
 * Subclass of Competitors class
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class DotaCompetitor extends Competitors{
	
	
	private int[] scores;
	private double [] levelWeights = {0.88 , 0.82, 0.80, 0.77 };
	private String [] levelName = {"Trainee", "Junior", "Professional", "Master"};
	public double overallScore = 0;
	
	/**Constructor to create competitor object
	 * @param num : competitor number
	 * @param age : age of competitor
	 * @param name: name object
	 * @param level: level of competitor
	 * @param compCountry: country
	 * @param scores: int array containing scores
	 */
	public DotaCompetitor(String id, int age, CompetitorName name, String level, String compCountry, int[] scores, String game) {
		
		super(id, age, name, level, compCountry, game);
		this.scores = scores;
		overallScore = this.calcOverallScore();
		}
	
	/**
	 * get overall score
	 * @param no input parameters
	 * @return overallScore
	 */
	@Override
	public double getOverallScore() {return overallScore;}
	
	/**
	 * get scores in an int array
	 * @param no input parameters
	 * @return scores
	 */
	@Override
	public int[] getScoreArray() {return scores;}
		
	/**
	 * calculate overall score based on the level and weighted average of the scores
	 * round the score to 2 decimal places
	 * @param no input parameters
	 * @return overallScore
	 */
	private double calcOverallScore() {
		overallScore = 0;
		// get weight based on level
		double weight = this.getlevelWeight();
		if (weight == 0) {return 0;} 
		for (int i=0; i<scores.length; i++ ) {
			overallScore += scores[i]*weight;
		}
		// Calculate overall score and round it to 2 decimal places
		overallScore = (Math.round((overallScore/this.scores.length) * 100)/100.0);
		return overallScore;
		 } 
	
	/**
	 * Calculate weight based on competitor level
	 * If level is incorrect in input file, make the weight 0 and print an error message
	 * All competitors with 0 weight will not be used for statistics calculation of winner (will be used for frequency calculation)
	 * @param no input parameters
	 * @return levelWeights[count]
	 */
	private double getlevelWeight() {
		int count = 0;
		boolean complete = false;
		while (!complete && count <levelName.length) {
			
			if (levelName[count].equals(this.level)) {complete = true;}
			else {count ++;}
			}
		if (count >= levelName.length) {
		System.out.print("Level Mentioned in input file not valid for competitor number:  " + this.compName.getFullName() + "\n");
		return 0;
		}
		else {return levelWeights[count];}	
	}

	/**
	 * Method to set score of competitor
	 * Also calculates overall score
	 * @param scoreValue: score of competitor
	 * @param arrayNum: number of score (out of 5)
	 * @return void
	 */
	@Override
	public void SetScoreArray(int scoreValue, int arrayNum) {
		this.scores[arrayNum] = scoreValue;
		calcOverallScore();
				
	}
	
}
