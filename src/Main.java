import com.core.Cleaner;
import com.core.WikiActions;
import com.dto.IConfigurations;
import com.util.CleanerUtility;

public class Main {

	public static void main(String[] args) throws Exception {

		IConfigurations config = CleanerUtility.getConfig(args);

		WikiActions actions = new WikiActions(config);
		// Login based on properties given by user
		actions.login();

		Cleaner cleaner = new Cleaner(config, actions);
		cleaner.process();
		// Logout user
		actions.logout();
	}
}
