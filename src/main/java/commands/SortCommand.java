package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class SortCommand extends Command {
    private CollectionManager collectionManager;

    public SortCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public SortCommand() {
    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.sort();
    }

    @Override
    public String toString() {
        return "sort";
    }
}
