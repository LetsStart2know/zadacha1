package Zadacha1;

import java.util.Scanner;

public class CalculatorArabianRoman {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение в римских числах от I до X или в арабских числах от 1 до 10: ");
        System.out.println(calc(scn.nextLine()));

    }
    public static String calc(String input) {
        Convert converter = new Convert();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"}; //экранируем + и *
        int actionCount = 0;
        int actionIndex = -1;
        for (int i = 0; i < input.length(); i++) {// цикл по строке exp
            for (int j = 0; j < actions.length; j++) {
                if (input.substring(i, i + 1).equals(actions[j])) { //цикл по строке actions
                    actionCount++;
                    actionIndex = j; // запоминаем знак действия
                }
            }
        }
        if (actionCount > 1 || actionIndex == -1) {// проверка на наличие и количество знаков действия
            throw new IllegalArgumentException("Не корректное выражение");
        }


        String[] data = input.split(regexActions[actionIndex]);//делим строчку по символу раздеителю
        String result;
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) { //проверяем находятся ли числа в одном формате исчисления
            int a, b; // вводим переменные
            boolean isRoman = converter.isRoman(data[0]); // проверяем римские ли цифры
            if (isRoman) {
                a = converter.romanToInt(data[0]); // если римские то переводим их в инт
                b = converter.romanToInt(data[1]);

            } else {
                a = Integer.parseInt(data[0]); // конвертируем числа в инт
                b = Integer.parseInt(data[1]);
            }
            if ((a <= 10 && a > 0) && (b <= 10 && b > 0)) {//ограничивам калькулятор диапозоном от 1 до 10
                int calcResult = switch (actions[actionIndex]) {   //вводим результат и проводим математические операции
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };

                if (isRoman) {
                   result = converter.intToRoman(calcResult); //Выводим результат для римских чисел на консоль
                } else {
                   result = String.valueOf(calcResult); // выводим результат для арабских чисел на консоль
                }
            } else {
                throw new IllegalArgumentException("Числа не находяться в диапазоне от 1 до 10");
            }
        }else {
            throw new IllegalArgumentException("Числа в разных системах исчесления");
        }

        return result;
    }

}

