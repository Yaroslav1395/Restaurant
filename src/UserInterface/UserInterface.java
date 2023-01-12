package UserInterface;

import Orders.RestaurantOrders;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    private void printAction(){
        System.out.println("1 - Показать все заказы");
        System.out.println("2 - Вывести 10 заказов с наибольшей суммой оплаты");
        System.out.println("3 - Вывести 10 заказов с наименьшей суммой оплаты");
        System.out.println("4 - Вывести заказы с доставкой на дом");
        System.out.println("5 - Наиболее и наименее прибыльные заказы");
        System.out.println("6 - Вывести заказы с указанным периодом");
        System.out.println("7 - Стоимость всех заказов");
        System.out.println("8 - Список всех электронных адресов");
        System.out.println("9 - Список заказов сгруппированный по именам клиентов");
        System.out.println("10 - Список клиентов с суммой всех заказов");
        System.out.println("11 - Клиент с максимальной суммой заказов");
        System.out.println("12 - Клиент с минимальной суммой заказов");
        System.out.println("13 - Количество проданного товара");
        System.out.println("14 - Для рассылки");
    }

    public UserInterface() {
        actionDo();
    }

    private int userInputNumber(){
        System.out.print("Введите номер действия: ");
        int userNumber;
        try {
            return userNumber = scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Ввели стоку вместо цифры. Попробуйте снова");
            return userNumber = userInputNumber();
        }
    }

    private void actionDo(){
        printAction();
        boolean isEnd = false;
        Map<Integer, Supplier<Boolean>> interfaceMap = new HashMap<>();
        interfaceMap.put(1, () -> {RestaurantOrders.printOrders(); return Boolean.FALSE;});
        interfaceMap.put(2, () -> {RestaurantOrders.tenOrdersWithHighestPaymentAmount(); return Boolean.FALSE;});
        interfaceMap.put(3, () -> {RestaurantOrders.tenOrdersWithLowestPaymentAmount(); return Boolean.FALSE;});
        interfaceMap.put(4, () -> {RestaurantOrders.homeDeliveryOrders(); return Boolean.FALSE;});
        interfaceMap.put(5, () -> {RestaurantOrders.deliveryMostAndLeastProfitable(); return Boolean.FALSE;});
        interfaceMap.put(6, () -> {RestaurantOrders.printOrdersWithSkip(); return Boolean.FALSE;});
        interfaceMap.put(7, () -> {RestaurantOrders.totalPriceOfAllOrders(); return Boolean.FALSE;});
        interfaceMap.put(8, () -> {RestaurantOrders.listOfAllCustomerEmail(); return Boolean.FALSE;});
        interfaceMap.put(9, () -> {RestaurantOrders.groupingOrdersByCustomer(); return Boolean.FALSE;});
        interfaceMap.put(10, () -> {RestaurantOrders.getCustomerAndSumOfAllHisOrders(); return Boolean.FALSE;});
        interfaceMap.put(11, () -> {RestaurantOrders.clientWithMaxAmountAllOrders(); return Boolean.FALSE;});
        interfaceMap.put(12, () -> {RestaurantOrders.clientWithMinAmountAllOrders(); return Boolean.FALSE;});
        interfaceMap.put(13, () -> {RestaurantOrders.groupingGoodsByNumberOfSales(); return Boolean.FALSE;});
        interfaceMap.put(14, () -> {RestaurantOrders.groupingGoodsByEmail(); return Boolean.FALSE;});
        interfaceMap.put(15, () -> Boolean.FALSE);

        while (!isEnd) {
            int userInputNumber = userInputNumber();
            isEnd = Optional.ofNullable(interfaceMap.get(userInputNumber)).orElse(() -> false).get();
        }
    }
}
