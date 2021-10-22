package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class UpdateCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;

    public UpdateCommand(CollectionManager collectionManager, String parameter) {
        this.collectionManager = collectionManager;
        this.parameter = parameter;
    }

    public UpdateCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
            return collectionManager.updateCommand(scanner, parameter);
    }

    @Override
    public String toString() {
        return "update";
    }
}
