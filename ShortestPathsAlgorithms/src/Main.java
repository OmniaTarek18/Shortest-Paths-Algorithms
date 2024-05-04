import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		boolean exitMainMenu = false;

		CLI cli = new CLI();
		cli.initializeGraph(); // take file path from user and initialize the graph.

		while (!exitMainMenu) {
			int mainOption = cli.getMainMenuOption(); // print main menu options and get the option.
			int src = 0;

			if (mainOption == 4) {
				exitMainMenu = true;
				break;
			}
			if (mainOption == 1)
				src = cli.getSource();
				
			int algOption = cli.getAlgorithmOption(mainOption);

			if (mainOption == 1) {
				cli.findShortestPaths(algOption, src);

			} else if (mainOption == 2) {

				cli.findAllShortestPaths(algOption);

			} else if (mainOption == 3) {

				cli.checkNegativeCycle(algOption);

			}
		}

	}

}
