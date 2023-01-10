package UserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    private void printAction(){
        System.out.println("1 - Посчитать стоимость заказа");
    }

    private int userInputNumber(){
        System.out.println("Введите номер действия");
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
        switch (userInputNumber()){
            case 1 :

        }
    }
}
