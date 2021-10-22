package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class InfoCommand extends Command {
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

    }

    public InfoCommand() {
    }

    @Override
    public Response execute(Scanner scanner) {
       return collectionManager.infoCommand(scanner);
    }

    @Override
    public String toString() {
        return "info";
    }
}
