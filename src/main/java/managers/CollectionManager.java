package managers;

import consoleWork.ConsoleWorker;
import consoleWork.Scanner;
import fileWork.FileManager;
import tasck.Ticket;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager implements Executer {

    private ArrayList<Ticket> tickets = new ArrayList<>();
    private Date initDate = new Date();
    private ConsoleWorker consoleWorker;
    private TicketAdder ticketAdder;
    private FileManager fileManager;
    private LocalDate lastSaveTime;

    public CollectionManager(ConsoleWorker consoleWorker, FileManager fileManager) {
        this.consoleWorker = consoleWorker;
        this.fileManager = fileManager;
        ArrayList<Ticket> tickets = fileManager.readCollection();
        if (tickets != null) {
            this.tickets = tickets;
        }
        ticketAdder = new TicketAdder(tickets);

    }

    @Override
    public Response executeExecuteScriptCommand(FileManager fileManager, String parameter, Scanner scanner) {
        String message = "";
        try {
            message = fileManager.readFile(parameter);
        } catch (FileNotFoundException e) {
            message = "false";
        }
        return new Response(message);
    }

    @Override
    public Response clearCommand(Scanner scanner) {
        tickets = new ArrayList<>();
        return new Response("Коллекция пуста!");
    }

    @Override
    public Response exitCommand(Scanner scanner) {
        System.exit(0);
        return new Response("Досвидания!");
    }

    @Override
    public Response helpCommand(Scanner scanner) {
        return new Response("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                "sort : отсортировать коллекцию в естественном порядке\n" +
                "remove_all_by_price price : удалить из коллекции все элементы, значение поля price которого эквивалентно заданному\n" +
                "sum_of_discount : вывести сумму значений поля discount для всех элементов коллекции\n" +
                "filter_by_price price : вывести элементы, значение поля price которых равно заданному");
    }

    @Override
    public Response infoCommand(Scanner scanner) {
        return new Response("Тип коллекции: " + tickets.getClass() +
                "\nДата инициадизации: " + initDate +
                "\nКолличество элементов коллекции: " + tickets.size());
    }

    @Override
    public Response removeByIdCommand(Scanner scanner, String parameter) {
        long id = 0;
        try {
            id = Long.parseLong(parameter);
        } catch (Exception e) {
            return new Response("id должен быть числом!");
        }
        Iterator iterator = tickets.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = (Ticket) iterator.next();
            if (ticket.getId() == id) {
                iterator.remove();
                return new Response("Элемент успешно удалён");
            }
        }
        return new Response("Элемента с таким id не существует!");

    }

    @Override
    public Response saveCommand(Scanner scanner) {
            lastSaveTime = LocalDate.now();
           return new Response(fileManager.writeCollection(tickets));
    }

    @Override
    public Response showCommand(Scanner scanner) {
        String message = "";

        for (Ticket ticket : tickets) {
            message += ticket.show();
        }

        if (message.equals("")) {
            message = "Коллекция пустая!";
        }
        return new Response(message);
    }

    @Override
    public Response updateCommand(Scanner scanner, String parameter) {
        long id = 0;
        try {
            id = Long.parseLong(parameter);
        } catch (Exception e) {
            return new Response("id должен быть числом!");
        }
        try {
            removeByIdCommand(scanner, parameter);
            addCommand(scanner);
        } catch (Exception e) {
            return new Response("Проблема с обновлением объекта");
        }
        return new Response("Объект успешно обновлён, или добавлен новый!");
    }

    @Override
    public Response sumOfDiscountCommand(Scanner scanner) {
        long sumOfDiscount = 0;
        for (Ticket ticket : tickets) {
            sumOfDiscount += ticket.getDiscount() != null ? ticket.getDiscount().longValue():0;
        }
        return new Response("Сумма скидок всех эллементов: " + sumOfDiscount);
    }

    @Override
    public Response removeGreaterCommand(Scanner scanner) {
        Ticket newTicket = ticketAdder.createTicket(scanner);
        ArrayList<Ticket> arrayList = new ArrayList<>(tickets.stream().sorted().collect(Collectors.toList()));
        int num = 0;
        for (Ticket ticket : arrayList) {
            if (newTicket.compareTo(ticket) < 0) {
                removeByIdCommand(scanner, String.valueOf(ticket.getId()));
            }
        }
        return new Response("Если вы не увидите этой группы после команды 'show', то эта группа была слабым звеном!(");
    }

    @Override
    public Response addCommand(Scanner scanner) {
        tickets.add(ticketAdder.createTicket(scanner));
        return new Response("Билет добавлен в коллекцию!");
    }

    @Override
    public Response filterByPriceCommand(String parameter) {
        String info = "";
        try {
            Float price = Float.parseFloat(parameter);
            for (Ticket ticket : tickets) {
                if (ticket.getPrice().equals(price)) {
                    info += ticket.show() + "\n\n";
                }
            }
            return new Response(info.trim());
        } catch (Exception e) {
            return new Response("Неверно введен параметр!");
        }
    }

    @Override
    public Response removeAllByPriceCommand(String parameter) {
        try {
            Float price = Float.parseFloat(parameter);
            Iterator iterator = tickets.iterator();
            while (iterator.hasNext()) {
                Ticket ticket = (Ticket) iterator.next();
                if (ticket.getPrice().equals(price)) {
                    iterator.remove();
                    return new Response("Элементы удовлетворяющие усмловию удалены!");
                }
            }
        } catch (Exception e) {
            return new Response("Неверно введен параметр!");
        }
        return new Response("Не найдено цен равной заданной.");
    }

    @Override
    public Response reorder() {
        if (!tickets.isEmpty()) {
            tickets = tickets
                    .stream()
                    .sorted((x, y) -> x.compareTo(y) * -1)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new Response("Коллекция отсортированна в обратном порядке!");
        } else return new Response("Коллекция пуста!");
    }

    @Override
    public Response sort() {
        if (!tickets.isEmpty()) {
            tickets.sort(Ticket::compareTo);
            return new Response("Коллекция отсортированна!");
        } else return new Response("Коллекция пуста!");

    }

}
