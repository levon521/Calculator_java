import java.util.Scanner;

public class Version1
{
    public static void main(String[] args) throws Exception
    {
        Scanner s = new Scanner(System.in);
        String string = s.nextLine();
        System.out.println(calc(string));
    }

    public static String calc(String input) throws Exception
    {
        String parts[] = input.split(" +");
        if (parts.length > 3) {
            throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (parts.length < 3) {
            throw new Exception("throws Exception //т.к. строка не является математической операцией");
        }
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabic = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] operations = {"+", "-", "/", "*"};
        int[] operands = {0, 0};
        boolean[] are_roman = {false, false};
        int k = 0;

        for (int i = 0; i < parts.length; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (parts[i].equals(roman[j])) {
                    operands[k] = j + 1;
                    are_roman[k] = true;
                    ++k;
                } else if (parts[i].equals(arabic[j])) {
                    operands[k] = j + 1;
                    ++k;
                }
            }
        }
        if (are_roman[0] ^ are_roman[1]) {
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        if (parts[1].equals("-") && are_roman[0] && are_roman[1] && operands[0] - operands[1] < 1) {
            throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }
        String res = "";
        if(are_roman[0] && are_roman[1])
        {
            switch(parts[1])
            {
                case "+":
                    res = integer_to_roman(operands[0] + operands[1]);
                    break;

                case "-":
                    res = integer_to_roman(operands[0] - operands[1]);
                    break;

                case "*":
                    res = integer_to_roman(operands[0] * operands[1]);
                    break;

                case "/":
                    res = integer_to_roman(operands[0] / operands[1]);
            }
        }
        else
        {
            switch(parts[1]) // DRY omitted
            {
                case "+":
                    res = Integer.toString(operands[0] + operands[1]);
                    break;

                case "-":
                    res = Integer.toString(operands[0] - operands[1]);
                    break;

                case "*":
                    res = Integer.toString(operands[0] * operands[1]);
                    break;

                case "/":
                    res = Integer.toString(operands[0] / operands[1]);
            }
        }
        return res;
    }

    public static String integer_to_roman(int num)
    {
        String[] roman = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90};
        int i = values.length - 1;
        String ans = "";
        while(num > 0)
        {
            while(num >= values[i])
            {
                ans += roman[i];
                num -= values[i];
            }
            --i;
        }
        return ans;
    }
}