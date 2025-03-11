import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение:");
        String input = scanner.nextLine();
        scanner.close();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        Pattern pattern = Pattern.compile("^(\\d{1,2})\\s*([+\\-*/])\\s*(\\d{1,2})$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат выражения. Используйте формат 'a + b', где a и b - числа до 10.");
        }

        int num1 = Integer.parseInt(matcher.group(1));
        char operation = matcher.group(2).charAt(0);
        int num2 = Integer.parseInt(matcher.group(3));

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Деление на ноль недопустимо!");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор.");
        }

        return Integer.toString(result);
    }
}




