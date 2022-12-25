package Arabian;

import java.util.regex.Pattern;

public class Calculate {
    private Calculate() {
    }

    public static void calc(String line) {
        line = line.toUpperCase();
        boolean isArabic = match(line, "^\\d{1,2}[\\s-+/*]\\d{1,2}$");
        boolean isRoman = match(line, "^[VIX]{1,4}[\\s-+/*][VIX]{1,4}$");
        if (!isArabic && !isRoman) {
            throw new IllegalArgumentException("Неверный формат входных данных");
        }
        String[] numbers = line.split("\\W");
        String operation =getOperation(line);
        var result = 0;
        if (isArabic) {
            if(Integer.parseInt(numbers[0]) < 11 && Integer.parseInt(numbers[1]) < 11){
                result = calculate(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), operation);
                System.out.println(line + "=" + result);
            }else {
                throw new IllegalArgumentException("Неверные входные параметры!!!Введены слишком большие числа");
            }
        }
        if (isRoman) {
            if(Converter.fromRomanToArabic(numbers[0]) < 11 && Converter.fromRomanToArabic(numbers[1]) < 11){
                result = calculate(Converter.fromRomanToArabic(numbers[0]), Converter.fromRomanToArabic(numbers[1]), operation);
            }else {
                throw new IllegalArgumentException("Неверные входные параметры!!!Введены слишком большие числа");
            }
            if (result > 1) {
                System.out.println(line + "=" + Converter.fromArabicToRoman(result));
            } else {
                throw new IllegalArgumentException("Результат вычисления в римских числах не может быть меньше 1");
            }
        }
    }

    private static Boolean match(String line, String mask) {
        var pattern = Pattern.compile(mask);
        var matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static int calculate(Integer operand1, Integer operand2, String operation) {
        switch (operation) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default: return 0;
        }
    }

    private static String getOperation(String line){
        var pattern = Pattern.compile("\\b[\\s-+/*]");
        var matcher = pattern.matcher(line);
        matcher.find();
        return line.substring(matcher.start(), matcher.end());
    }
}
