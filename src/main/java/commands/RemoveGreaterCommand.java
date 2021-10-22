package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class RemoveGreaterCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public RemoveGreaterCommand() {

    }


    @Override
    public Response execute(Scanner scanner) {
            return collectionManager.removeGreaterCommand(scanner);
    }

    @Override
    public String toString() {
        return "remove_greater";
    }
}
