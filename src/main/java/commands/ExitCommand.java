package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class ExitCommand extends Command {
    private CollectionManager collectionManager;
    public ExitCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ExitCommand() {

    }

    @Override
    public String toString() {
        return "exit";
    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.exitCommand(scanner);
    }
}
