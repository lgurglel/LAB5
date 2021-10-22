package commands;

import consoleWork.Scanner;
import managers.CollectionManager;
import managers.Command;
import managers.Response;

public class SumOfDiscountCommand extends Command {
    private CollectionManager collectionManager;

    public SumOfDiscountCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public SumOfDiscountCommand() {

    }

    @Override
    public Response execute(Scanner scanner) {
        return collectionManager.sumOfDiscountCommand(scanner);
    }

    @Override
    public String toString() {
        return "sum_of_discount";
    }
}
