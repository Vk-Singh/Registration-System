import java.util.Comparator;

/** This class implements Comparator interface to compare 2 competitors based on name
 * @author Vikram Singh - H00391053
 * @version 1.0
 */
public class NameComparator implements Comparator<Competitors>{

	@Override
	public int compare(Competitors o1, Competitors o2) {
		return o1.getCompName().compareTo(o2.getCompName());
	}

}
