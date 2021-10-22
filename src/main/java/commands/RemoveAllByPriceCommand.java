package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class RemoveAllByPriceCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;

    public RemoveAllByPriceCommand(CollectionManager collectionManager, String parameter) {
        this.collectionManager = collectionManager;
        this.parameter = parameter;
    }

    public RemoveAllByPriceCommand() {
    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.removeAllByPriceCommand(parameter);
    }

    @Override
    public String toString() {
        return "remove_all_by_price";
    }
}
