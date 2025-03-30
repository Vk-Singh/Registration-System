/** This class maintains the name objects of all competitors
 *  
 * @author Vikram Singh - H00391053
 * @version 1.0
 */
public class CompetitorName implements Comparable <CompetitorName>{
	  private String firstName;
	  private String middleName;
	  private String lastName;
	  
	 
	  /**Constructor to create competitor name if first and last name given
	   * 
	   * @param fName: first name
	   * @param lName: last name
	   */
	  public CompetitorName(String fName, String lName) {
			firstName  = fName;
			middleName = "";
			lastName   = lName;
	  }
	  
	  /** Constructor to create competitor name if first, middle and last name given
	   * 
	   * @param fName : first name
	   * @param mName : middle name
	   * @param lName : last name
	   * @return void
	   */
	  public CompetitorName(String fName, String mName, String lName) {
			firstName  = fName;
			middleName = mName;
			lastName   = lName;
	}
	  
	  /**Constructor to create competitor name if full name is given in a single string 
	   * Split full name in first, middle and last name
	   * @param fullName: single string of name
	   * @return void
	   */
	  public CompetitorName (String fullName) {
		  int spacePos1 = fullName.indexOf(' ');
		  // if only first name exists
		  if (spacePos1 == -1) {
			  firstName = fullName;
			  middleName = "";
			  lastName = "";
		  }
		  else {
			  firstName = fullName.substring(0, spacePos1);
			  int spacePos2 = fullName.lastIndexOf(' ');
			  if (spacePos1 == spacePos2)
				  middleName = "";
			  else 
				  middleName = fullName.substring(spacePos1+1, spacePos2);
			  lastName = fullName.substring(spacePos2 + 1);
		  }
		  }
	  
	  /**Returns the first name
	   * @param no input parameters
	   * @return firstName
	   */
	  public String getFirstName() {return firstName; }
	  
	  

	  /**Returns the last name
	   * @param no input parameters
	   * @return lastName
	   */
	  public String getLastName() {return lastName; }
	  
	  /**change the last name to the value provided in the parameter
	   * @param ln: last name to be set
	   * @return void
	   */
	  public void setLastName(String ln) {
		  lastName = ln;
	  }
	  
	  
	  /**returns the full name
	  	 Either first name then space then last name
	  	 or first name then space then middle name then space
	   	 and then last name
	   	 @param no input parameters
	   	 @return void
	   */
	  public String getFullName() {
		  String result = firstName + " ";
		  if (!middleName.equals("")) {
		    	result += middleName + " ";
		  }
		  result += lastName;
		  return result;
		 }
	  /**Returns initials of name
	   * If multiple first middle or last names, only provide first initial of every name
	   * @param no input parameters
	   * @return result
 	   */
	  public String getInititals() {
		  String result = String.valueOf(firstName.toUpperCase().charAt(0));
		  if (!middleName.equals("")) {
			  result += middleName.toUpperCase().charAt(0);
			  }
		  if (!lastName.equals("")) {
			  result += lastName.toUpperCase().charAt(0);	  
			  }
		  return result;
	  }
	  
	  /**Returns First and last name
	   * If Last name is not present for a competitor, return first name as last name
	   * and first name as FNU (First name not available)
	   * Used in toString override method of Competitors class
	   * @param no input parameters
	   * @return result
 	   */
	  
	  public String getFirstAndLastName() {
		  String tempFirstName = null;
		  String tempLastName = null;
		  String result;
		  if (lastName.equals("")){
			  tempFirstName = "FNU";
			  tempLastName = firstName;
		  }else{tempFirstName = firstName;}
		  result = tempFirstName + " " + tempLastName;
			return result;
	  }
	  
	  
	  /**Returns Last name, first name
	   * If Last name is not present for a competitor, return first name as last name
	   * and first name as FNU (First name not available)
	   * Used in toString override method of Competitors class
	   * @param no input parameters
	   * @return result
 	   */
	  public String getLastCommaFirst() {
		  String tempFirstName = null;
		  String tempLastName = null;
		  String result;
		  if (lastName.equals("")){
			  tempFirstName = "FNU";
			  tempLastName = firstName;
		  }else{tempFirstName = firstName;}
		  result = tempLastName + ", "+ tempFirstName;
			return result;
	  }

	  /**
		 * Comparable interface Override method to distinguish between 2 competitor names
		 * @param otherDetails: competitor to compare
		*/
	@Override
	public int compareTo(CompetitorName name) {
		  String thisName=lastName + " " + firstName + " " + middleName;
		  String othername = name.lastName+ " " + name.firstName + name.middleName;
		 return thisName.compareTo(othername);
	}
	  
	}
