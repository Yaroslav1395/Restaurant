package UserInterface;

import Orders.RestaurantOrders;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    private void printAction(){
        System.out.println("1 - Показать все заказы");
        System.out.println("2 - Вывести 10 заказов с наибольшей суммой оплаты");
        System.out.println("3 - Вывести 10 заказов с наименьшей суммой оплаты");
        System.out.println("4 - Вывести заказы с доставкой на дом");
        System.out.println("5 - Наиболее и наименее прибыльные заказы");
        System.out.println("6 - Все заказы без наименее и наиболее прибыльного");
        System.out.println("7 - Стоимость всех заказов");
        System.out.println("8 - Список всех электронных адресов");
        System.out.println("9 - Список заказов сгруппированный по именам клиентов");
        System.out.println("10 - Список клиентов с суммой всех заказов");
        System.out.println("11 - Клиент с максимальной суммой заказов");
        System.out.println("12 - Клиент с минимальной суммой заказов");

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
        while (!isEnd){
            switch (userInputNumber()){
                case 1 :
                    RestaurantOrders.printOrders();
                    break;
                case 2 :
                    RestaurantOrders.tenOrdersWithHighestPaymentAmount();
                    break;
                case 3 :
                    RestaurantOrders.tenOrdersWithLowestPaymentAmount();
                    break;
                case 4 :
                    RestaurantOrders.homeDeliveryOrders();
                    break;
                case 5 :
                    RestaurantOrders.deliveryMostAndLeastProfitable();
                    break;
                case 6 :
                    RestaurantOrders.printOrdersWithoutMaxAndMinOrderTotal();
                    break;
                case 7 :
                    RestaurantOrders.totalPriceOfAllOrders();
                    break;
                case 8 :
                    RestaurantOrders.listOfAllCustomerEmail();
                    break;
                case 9 :
                    RestaurantOrders.groupingOrdersByCustomer();
                    break;
                case 10 :
                    RestaurantOrders.getCustomerAndSumOfAllHisOrders();
                    break;
                case 11 :
                    RestaurantOrders.clientWithMaxAmountAllOrders();
                    break;
                case 12 :
                    RestaurantOrders.clientWithMinAmountAllOrders();
                    break;
                case 0 :
                    isEnd = true;
                    break;
            }
        }
    }
}
