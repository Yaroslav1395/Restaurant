package domain;

import util.NotImplementedException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order {
    private final Customer customer;
    private final List<Item> items;
    private final boolean homeDelivery;
    private transient double total = 0.0d;

    public Order(Customer customer, List<Item> orderedItems, boolean homeDelivery) {
        this.customer = customer;
        this.items = List.copyOf(orderedItems);
        this.homeDelivery = homeDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return homeDelivery == order.homeDelivery &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, homeDelivery);
    }

    @Override
    public String toString() {
        return "Заказчик: " + customer +
                "\nБлюда: " + listItemsToString() +
                "\nДоставка: " + homeDelivery +
                "\nСумма: " + total;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotal() {
        return total;
    }

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    public void calculateTotal() {
        total = items.stream().mapToDouble(Item::totalCost).sum();
    }

    private String listItemsToString(){
        return items.stream()
                .map(Item::toString)
                .collect(Collectors.joining(", ", " ", ""));
    }
    public String getCustomerName(){
        return customer.getFullName();
    }
    public String getCustomerEmail(){
        return customer.getEmail();
    }

}
