
/** The BoxingCompetitor class specifies characterstics of boxing competitors
 * and provide methods to set and access required competitor's values
 * Overall score is calculated by taking average skipping highest and lowest score
 * Sub class of Competitors class
 * @author Vikram Singh - H00391053
 * @version 1.0
 */

public class BoxingCompetitor extends Competitors{
	
	private float height;
	private float weight;
	private int[] scores;
	public double overallScore = 0;
	
	/**Constructor to create competitor object
	 * @param num : competitor number
	 * @param age : age of competitor
	 * @param name: name object
	 * @param level: level of competitor
	 * @param compCountry: country
	 * @param scores: int array containing scores
	 */
	public BoxingCompetitor(String id, int age, CompetitorName name, String level, String compCountry, int[] scores, String game, float height, float weight) {
		
		super(id, age, name, level, compCountry, game);
		this.scores = scores;
		this.height = height;
		this.weight = weight;
		overallScore = this.getOverallScore();
		}
	
	/**
	 * Calculate overall score
	 * Overall score is the average of scores leaving highest and lowest score of the competitor
	 * @param no input parameters
	 * @return overallScore
	 */
	public double getOverallScore() {
		double tempScore = 0;
		for (int i=0; i< this.scores.length; i++) {
			tempScore += scores[i];	
		}
		tempScore = tempScore - this.getHighestScore() - this.getLowestScore();
		// Calculate overall score and round it to 2 decimal places
		tempScore = (Math.round((tempScore/(this.scores.length-2)) * 100)/100.0);
		return tempScore;
	}

	
	/**
	 * get height
	 * @param no input parameters
	 * @return height
	 */
	public double getHeight() {return height;}
	
	/**
	 * get weight
	 * @param no input parameters
	 * @return weight
	 */
	public double getWeight() {return weight;}
	
	/**
	 * get scores in an int array
	 * @param no input parameters
	 * @return scores
	 */
	public int[] getScoreArray() {return scores;}
	
	
	
	/**
	 * Calculate and return highest score 
	 * @param no input parameters
	 * @return count
	 */
	public int getHighestScore() {
		int count = scores[0];
	
		for (int i=0; i< this.scores.length; i++) {
			if (count < scores[i]) {count = scores[i];
			}						
		}
		return count;
	}
	
	/**
	 * Calculate and return lowest score 
	 * @param no input parameters
	 * @return count
	 */
	public int getLowestScore() {
		int count = scores[0];
	
		for (int i=0; i< this.scores.length; i++) {
			if (count > scores[i]) {count = scores[i];
			}						
		}
		return count;
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
		getOverallScore();
	}
	
}
