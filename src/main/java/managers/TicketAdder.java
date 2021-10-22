package managers;

import consoleWork.Scanner;
import tasck.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public class TicketAdder {
    private ArrayList<Ticket> tickets;

    public TicketAdder(ArrayList<Ticket> tickets) {
        this.tickets = new ArrayList<>();
    }

    public Ticket createTicket(Scanner scanner) {
        long id = createNewId();
        String name = createNewName(scanner);
        Coordinates coordinates = createNewCoordinates(scanner);
        ZonedDateTime creationDate = createNewDate();
        Float price = createNewPrice(scanner);
        Long discount = createNewDiscount(scanner);
        String comment = createNewComment(scanner);
        TicketType type = createNewType(scanner);
        Venue venue = createNewVenue(scanner);
        return new Ticket(id, name, coordinates, creationDate, price, discount, comment, type, venue);
    }

    private long createNewId() {
        boolean repeatedId = true;
        long id = 0;
        while (repeatedId) {
            id = new Random().nextInt();
            repeatedId = false;
            Iterator iterator = tickets.iterator();
            while (iterator.hasNext()) {
                Ticket ticket = (Ticket) iterator.next();
                if (ticket.getId() == id) {
                    repeatedId = true;
                }
            }
        }
        return id;
    }
    private ZonedDateTime createNewDate() {
        return ZonedDateTime.now();
    }

    private String createNewName(Scanner scanner) {
        scanner.write("Введите название билета: ");
        String name = scanner.read();
        if (name != null && !name.equals("")) {
            return name;
        } else
            scanner.write("Название билета введено неверно!");
        return createNewName(scanner);
    }

    private Long readX(Scanner scanner) {
        try {
            scanner.write("Введите координату X:");
            String strX = scanner.read();
            Long x = Long.parseLong(strX);
            if (x > -17) {
                return Long.parseLong(strX);
            } else {
                scanner.write("X должен быть целым числом, больше -17.");
                return readX(scanner);
            }
        } catch (Exception e) {
            scanner.write("Ошибка. Некорректная координата X.");
            return readX(scanner);
        }
    }

    private Double readY(Scanner scanner) {
        try {
            scanner.write("Введите координату Y:");
            String strY = scanner.read();
            Double yVal = Double.parseDouble(strY);
            if (strY != null)
                return yVal;
            else
                throw new Exception();
        } catch (Exception e) {
            scanner.write("Ошибка. Некорректная координата Y. Y должен быть целым числом и не равен null.");
            return readY(scanner);
        }
    }

    private Coordinates createNewCoordinates(Scanner scanner) {
        return new Coordinates(readX(scanner), readY(scanner));
    }

    private Float createNewPrice(Scanner scanner) {
        try {
            scanner.write("Введите цену:");
            String priceStr = scanner.read();
            Float price;
            if (priceStr != null && !priceStr.equals("")) {
                price = Float.parseFloat(priceStr);
                if (price > 0) {
                    return price;
                } else
                    scanner.write("Цена должна быть больше 0!");
                return createNewPrice(scanner);
            } else
                throw new Exception();
        } catch (Exception e) {
            scanner.write("Ошибка. Неверно указена цена.");
            return createNewPrice(scanner);
        }
    }

    private Long createNewDiscount(Scanner scanner) {
        try {
            scanner.write("Введите скидку:");
            String discountStr = scanner.read();
            Long discount;
            if (discountStr != null && !discountStr.equals("")) {
                discount = Long.parseLong(discountStr);
                if (discount > 0 && discount <= 100) {
                    return discount;
                } else {
                    scanner.write("Ошибка. Скидка должна быть больше 0, а максимальное значение 100");
                    return createNewDiscount(scanner);
                }
            } else
                scanner.write("Скидка не установлена.");
            discount = 0L;
            return discount;
        } catch (Exception e) {
            scanner.write("Неверный формат скидки, если нет скидки, то просто нажмите 'Enter'");
            return createNewDiscount(scanner);
        }
    }

    private String createNewComment(Scanner scanner) {
        //сделать проверку на null
        scanner.write("Введите комментарий: ");
        String comment = scanner.read();
        if (comment != null && !comment.equals("")) {
            return comment;
        } else
            scanner.write("Комментарий не установлен.");
        return "";
    }

    // TODO: 21.10.2021
    private TicketType createNewType(Scanner scanner) {
        try {
            scanner.write("Выберете и введите из списка тип билета: " + TicketType.nameList());
            String typeStr = scanner.read();
            if (typeStr != null) {
                return TicketType.valueOf(typeStr.toUpperCase(Locale.ROOT));
            } else
                scanner.write("Тип билета не установлен.");
            return null;
        } catch (Exception e) {
            scanner.write("Ошибка. Некорректный тип. Введите тип из перечисленных.");
            return createNewType(scanner);
        }
    }

    private Venue createNewVenue(Scanner scanner) {
        return new Venue(createNewIdVenue(), createNewVenueName(scanner), createNewCapacity(scanner), createNewAddress(scanner));
    }

    private String createNewVenueName(Scanner scanner) {
        scanner.write("Введите название места проведения:");
        String name = scanner.read();
        if (name != null && !name.equals("")) {
            return name;
        } else
            scanner.write("Название места проведения введено неверно!");
        return createNewVenueName(scanner);
    }

    private int createNewIdVenue() {
        boolean repeatedId = true;
        int id = 0;
        while (repeatedId) {
            id = new Random().nextInt();
            repeatedId = false;
            Iterator iterator = tickets.iterator();
            while (iterator.hasNext()) {
                Venue venue = (Venue) iterator.next();
                if (venue.getId() == id) {
                    repeatedId = true;
                }
            }
        }
        return id;
    }

    private int createNewCapacity(Scanner scanner) {
        try {
            scanner.write("Введите колличество мест:");
            String capacityStr = scanner.read();
            int capacity;
            if (capacityStr != null && !capacityStr.equals("")) {
                capacity = Integer.parseInt(capacityStr);
                if (capacity > 0) {
                    return capacity;
                } else
                    scanner.write("Колличество месть должно быть больше 0.");
                return createNewCapacity(scanner);
            } else
                throw new Exception();
        } catch (Exception e) {
            scanner.write("Ошибка. Неверный формат.");
            return createNewCapacity(scanner);
        }
    }

    private String createNewStreet(Scanner scanner) {
        scanner.write("Введите название улицы:");
        String name = scanner.read();
        if (name != null && !name.equals("")) {
            return name;
        } else
            scanner.write("Название улицы не установленно.");
        return null;
    }

    private String createNewZipCode(Scanner scanner) {
        scanner.write("Введите ZipCode (длинна строки должна быть не меньше 10):");
        String name = scanner.read();
        if (name != null && !name.equals("") && name.length() > 9) {
            return name;
        } else
            scanner.write("ZipCode не установлен.");
        return "";
    }

    private Address createNewAddress(Scanner scanner) {
        return new Address(createNewStreet(scanner), createNewZipCode(scanner));
    }

}
