package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class AddCommand extends Command {

    private CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {

        this.collectionManager = collectionManager;
    }

    public AddCommand() {
    }

    @Override
    public String toString() {
        return "add";
    }

    @Override
    public Response execute(Scanner scanner) {
      return collectionManager.addCommand(scanner);
    }

}
