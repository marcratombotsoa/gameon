package mg.ratombotsoa.cardgame.app;

import java.util.Collection;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import mg.ratombotsoa.cardgame.comparator.ComparatorTypeEnum;
import mg.ratombotsoa.cardgame.model.Card;
import mg.ratombotsoa.cardgame.service.CardService;
import mg.ratombotsoa.cardgame.service.impl.CardServiceImpl;
import static mg.ratombotsoa.cardgame.util.CardUtil.printCards;

public class ApplicationRunner {

	private static final String EXIT_CMD = "exit";
	private static final String SHUFFLE_CMD = "shuffle";
	private static final String SORT_CMD = "sort";
	private static final String PICK_CMD = "pick";
	private static final String ASC = "asc";
	private static final String DESC = "desc";
	private static final int SORT_COMMAND_LENGTH = 3;

	private CardService cardService = new CardServiceImpl();

	public void run() {
		Scanner scanner = new Scanner(System.in);;
		String command = null;
		Collection<Card> cards = cardService.generateCardGame();
		printCards(cards);
		while (!EXIT_CMD.equalsIgnoreCase(command)) {
			System.out.println("Available commands: shuffle, sort, pick, exit");
			System.out.print("cardgame$ ");
			command = scanner.nextLine();

			parseAndRunCommand(command, cards);
		}

		scanner.close();
	}

	private void parseAndRunCommand(String command, Collection<Card> cards) {
		if (StringUtils.isEmpty(command)) {
			return;
		}

		if (SHUFFLE_CMD.equalsIgnoreCase(command)) {
			printCards(cardService.shuffleCards(cards));
			return;
		}

		if (PICK_CMD.equalsIgnoreCase(command)) {
			System.out.println(cardService.getRandomCard(cards));
			return;
		}

		if (command.startsWith(SORT_CMD)) {
			parseAndRunSort(command, cards);
			return;
		}

		if (!EXIT_CMD.equalsIgnoreCase(command)) {
			System.out.println("Invalid command");
		} else {
			System.out.println("Bye!");
		}
	}

	private void parseAndRunSort(String command, Collection<Card> cards) {
		String[] splitted = command.split("\\s+");
		if (splitted.length != SORT_COMMAND_LENGTH) {
			System.out.println(
					"Invalid sort command. Should include the sort criteria (color, symbol or number) and ascending parameter");
			System.out.println("E.g : sort color asc, sort symbol desc");
			return;
		}

		try {
			ComparatorTypeEnum.valueOf(splitted[1].toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid sort criteria. Should be one of the following: color, symbol or number");
			return;
		}

		if (!ASC.equalsIgnoreCase(splitted[2]) && !DESC.equalsIgnoreCase(splitted[2])) {
			System.out.println("Invalid sort direction. Should be one of the following: asc, desc");
			return;
		}

		printCards(cardService.sortCards(cards, splitted[1], ASC.equalsIgnoreCase(splitted[2])));
	}
}
