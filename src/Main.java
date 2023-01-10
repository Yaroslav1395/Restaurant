// import static java.util.stream.Collectors.*;
// import static java.util.Comparator.*;

// используя статические imports
// мы импортируем *всё* из Collectors и Comparator.
// теперь нам доступны такие операции как
// toList(), toSet() и прочие, без указания уточняющего слова Collectors. или Comparator.
// вот так было до импорта Collectors.toList(), теперь стало просто toList()


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        //var orders = Orders.RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = Orders.RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = Orders.RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
    }
}
