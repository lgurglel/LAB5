package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class FilterByPriceCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;

    public FilterByPriceCommand(CollectionManager collectionManager, String parameter) {
        this.collectionManager = collectionManager;
        this.parameter = parameter;
    }

    public FilterByPriceCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
            return collectionManager.filterByPriceCommand(parameter);
    }

    @Override
    public String toString() {
        return "filter_by_price";
    }
}
