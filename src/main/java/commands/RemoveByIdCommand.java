package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class RemoveByIdCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;

    public RemoveByIdCommand(CollectionManager collectionManager, String parameter) {
        this.collectionManager = collectionManager;
        this.parameter = parameter;
    }

    public RemoveByIdCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
            return collectionManager.removeByIdCommand(scanner, parameter);
    }

    @Override
    public String toString() {
        return "remove_by_id";
    }
}
