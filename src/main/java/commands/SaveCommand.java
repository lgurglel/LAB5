package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class SaveCommand extends Command {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public SaveCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {

        return collectionManager.saveCommand(scanner);
    }

    @Override
    public String toString() {
        return "save";
    }
}
