package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class ClearCommand extends Command {
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ClearCommand() {
    }

    @Override
    public String toString() {
        return "clear";
    }

    @Override
    public Response execute(Scanner scanner) {
       return collectionManager.clearCommand(scanner);
    }
}
