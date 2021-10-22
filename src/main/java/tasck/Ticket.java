package tasck;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Comparable<Ticket> {
    private long id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Float price;
    private Long discount;
    private String comment;
    private TicketType type;
    private Venue venue;

    public Ticket(long id, String name, Coordinates coordinates, ZonedDateTime creationDate, Float price, Long discount, String comment, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.comment = comment;
        this.type = type;
        this.venue = venue;
    }

    public String show() {
        return "\nTicket: " +
                "\n     Creation Date: " + creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                "\n     Id: " + id +
                "\n     Name: " + name +
                "\n     Coordinates: X:" + coordinates.getX() + " Y:" + coordinates.getY() +
                "\n     Price: " + price +
                "\n     Discount: " + discount +
                "\n     Comment: " + comment +
                "\n     Type: " + type +
                "\n     Venue Id: " + venue.getId() +
                "\n     Venue Name: " + venue.getName() +
                "\n     Venue Capacity: " + venue.getCapacity() +
                "\n     Address Street: " + venue.getAddress().getStreet() +
                "\n     Address ZipCode: " + venue.getAddress().getZipCode();
    }

    public long getId() {
        return id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Float getPrice() {
        return price;
    }

    public Long getDiscount() {
        return discount;
    }
    // TODO: 21.10.2021
    @Override
    public int compareTo(Ticket o) {
        return getCreationDate().compareTo(o.getCreationDate());
    }
}