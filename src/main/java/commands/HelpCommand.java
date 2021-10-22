package commands;

import consoleWork.ConsoleWorker;
import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class HelpCommand extends Command {

    private ConsoleWorker consoleWorker;
    private CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public HelpCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.helpCommand(scanner);
    }

    @Override
    public String toString() {
        return "help";
    }
}
