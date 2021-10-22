package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class ShowCommand extends Command {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public ShowCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.showCommand(scanner);
    }

    @Override
    public String toString() {
        return "show";
    }
}
