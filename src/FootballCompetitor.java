
/** The FootballCompetitor class specifies characterstics of Football competitors
 * and provide methods to set and access required competitor's values
 * Subclass of Competitors class
 * Overall score is calculated as the average of scores
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class FootballCompetitor extends Competitors{
	
	private int[] scores;
	private String team;
	public double overallScore = 0;
	
	/**Constructor to create competitor object
	 * @param num : competitor number
	 * @param age : age of competitor
	 * @param name: name object
	 * @param level: level of competitor
	 * @param team: name of team
	 * @param compCountry: country
	 * @param scores: int array containing scores
	 */
	public FootballCompetitor(String id, int age, CompetitorName name, String level, String compCountry, int[] scores, String game, String team) {
		
		super(id, age, name, level, compCountry, game);
		this.scores = scores;
		this.team = team;
		overallScore = this.calcOverallScore();
		}
	
	/**
	 * get overall score
	 * @param no input parameters
	 * @return overallScore
	 */
	public double getOverallScore() {return overallScore;}
	
	/**
	 * get team
	 * @param no input parameters
	 * @return this.team
	 */
	public String getTeam() {return this.team;}
	
	/**
	 * Set team
	 * @param team
	 * @return void
	 */
	public void setTeam(String team) {this.team = team;}
	
	/**
	 * get scores in an int array
	 * @param no input parameters
	 * @return scores
	 */
	public int[] getScoreArray() {return scores;}
	
	/**
	 * calculate overall score as the average of scores
	 * round the score to 2 decimal places
	 * @param no input parameters
	 * @return overallScore
	 */
	private double calcOverallScore() {
		overallScore = 0;
		for (int i=0; i<scores.length; i++ ) {
			overallScore += scores[i];
		}
		// Calculate overall score and round it to 2 decimal places
		overallScore = (Math.round((overallScore/this.scores.length) * 100)/100.0);
		return overallScore;
		
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
