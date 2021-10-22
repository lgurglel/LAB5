package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class ReorderCommand extends Command {
    private CollectionManager collectionManager;

    public ReorderCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ReorderCommand() {
    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.reorder();
    }

    @Override
    public String toString() {
        return "reorder";
    }
}
