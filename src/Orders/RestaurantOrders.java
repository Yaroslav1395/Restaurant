package Orders;

import com.google.gson.Gson;
import domain.Item;
import domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

public class RestaurantOrders {
    private static List<Order> orders = RestaurantOrders.read();

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Order> getOrders() {
        return orders;
    }
    private static List<Order> read() {
        var ro = new RestaurantOrders("orders_100.json");
        ro.getOrders().forEach(Order::calculateTotal);
        return ro.getOrders();
    }

    public static void printOrders(){
        orders.forEach(order -> System.out.println(order.toString()));
    }
    public static void tenOrdersWithHighestPaymentAmount(){
        orders.stream()
                .sorted(comparing(Order::getTotal).reversed())
                .limit(10)
                .toList()
                .forEach(order -> System.out.println(order.toString()));
    }
    public static void tenOrdersWithLowestPaymentAmount(){
        orders.stream()
                .sorted(comparing(Order::getTotal))
                .limit(10)
                .toList()
                .forEach(order -> System.out.println(order.toString()));
    }

    public static void homeDeliveryOrders(){
        orders.stream()
                .filter(Order::isHomeDelivery)
                .toList()
                .forEach(orders -> System.out.println(orders.toString()));
    }
    public static void deliveryMostAndLeastProfitable(){
        List<Order> delivery = orders.stream()
                .filter(Order::isHomeDelivery)
                .toList();

        double middleProfit = delivery.stream().mapToDouble(Order::getTotal).sum() / delivery.size();

        Map<Boolean, List<Order>> mostAndLeast = delivery.stream()
                .collect(partitioningBy(order -> order.getTotal() >= middleProfit));

        System.out.println("-------------Самые прибыльные заказы-------------");
        mostAndLeast.get(Boolean.TRUE).stream()
                .sorted(comparing(Order::getTotal).reversed())
                .toList()
                .forEach(System.out::println);

        System.out.println("-------------Менее прибыльные заказы-------------");
        mostAndLeast.get(Boolean.FALSE).stream()
                .sorted(comparing(Order::getTotal).reversed())
                .toList()
                .forEach(System.out::println);
    }

    public static void printOrdersWithSkip(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите от какой суммы");
        int minSum = scanner.nextInt();
        System.out.println("Введите до какой суммы");
        int maxSum = scanner.nextInt();
        orders.stream()
                .sorted(comparing(Order::getTotal))
                .takeWhile(order -> order.getTotal() >= minSum)
                .dropWhile(order -> order.getTotal() > maxSum)
                .forEach(System.out::println);
    }

    public static void totalPriceOfAllOrders(){
        double total = orders.stream().mapToDouble(Order::getTotal).sum();
        System.out.println("Стоимость всех заказов: " + total);
    }

    public static void listOfAllCustomerEmail(){
        orders.stream()
                .map(order -> order.getCustomer().getEmail())
                .collect(toCollection(TreeSet::new))
                .forEach(System.out::println);
    }

    public static void groupingOrdersByCustomer(){
        Map<String, List<Order>> groupedOrdersByCustomer = orders.stream().
                collect(groupingBy(Order::getCustomerName));

        groupedOrdersByCustomer.forEach((k,v) -> {
            System.out.println("Заказчик: " + k);
            System.out.print("Заказ: ");
            v.forEach(System.out::println);
        });
    }

    public static void getCustomerAndSumOfAllHisOrders(){
        orders.stream()
                .collect(groupingBy(Order::getCustomerName, summingDouble(Order::getTotal)))
                .forEach((k,v) -> {
                    System.out.println("Заказчик: " + k);
                    System.out.printf("Сумма заказов: %.2f\n", v);
                });
    }

    public static void clientWithMaxAmountAllOrders(){
        Map<String, Double> map =  orders.stream()
                .collect(groupingBy(Order::getCustomerName, summingDouble(Order::getTotal)));

        Map.Entry<String, Double> maxEntry = map.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();

        System.out.println("Клиент: " + maxEntry.getKey());
        System.out.println("Сумма: " + maxEntry.getValue());

    }
    public static void clientWithMinAmountAllOrders(){
        Map<String, Double> map =  orders.stream()
                .collect(groupingBy(Order::getCustomerName, summingDouble(Order::getTotal)));

        Map.Entry<String, Double> maxEntry = map.entrySet().stream()
                .min(Map.Entry.comparingByValue()).get();

        System.out.println("Клиент: " + maxEntry.getKey());
        System.out.println("Сумма: " + maxEntry.getValue());

    }
    public static void groupingGoodsByNumberOfSales(){
        List<Item> allItemsInOrders = orders.stream().flatMap(order -> order.getItems().stream()).toList();

        Map<String, List<Integer>> itemListAmount = allItemsInOrders.stream()
                .collect(groupingBy(Item::getName, mapping(Item::getAmount, toList())));

        Map<String, Integer> itemAmount = new HashMap<>();

        for (var entry: itemListAmount.entrySet()) {
            int sumAmount = entry.getValue().stream().mapToInt(Integer::intValue).sum();
            itemAmount.put(entry.getKey(), sumAmount);
        }

        itemAmount.forEach((k,v) -> {
            System.out.print(k + "-");
            System.out.println(v);
        });
    }

    public static void groupingGoodsByEmail(){
        Map<String, List<String>> itemEmail = new HashMap<>();

        Map<String, List<Set<String>>> emailListsItems= orders.stream()
                .collect(groupingBy(Order::getCustomerEmail, mapping(order -> order.getItems().stream()
                                .map(Item::getName)
                                .collect(toSet()), toList()))
                );

        for (var entry: emailListsItems.entrySet()) {
            List<String> items = entry.getValue().stream().collect(ArrayList::new, List::addAll, List::addAll);
            itemEmail.put(entry.getKey(), items);
        }

        itemEmail.forEach((k,v) -> {
            System.out.println(k + " -> ");
            v.forEach(System.out::println);
        });
    }
}
