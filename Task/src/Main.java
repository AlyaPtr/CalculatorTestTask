import java.util.Scanner;
public class Main {
    // There are two possible number types
    enum Type {
        ROMAN,
        ARABIAN
    }
    static String operations = "+-*/"; // Possible operations
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println(calc(in.nextLine()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // There are the calculation algorithm
    public static String calc(String input) throws Exception {
        if (!checkCorrect(input)) {
            throw new Exception("The string is incorrect");
        }

        Type type = getType(input.charAt(0));
        if (type == Type.ROMAN) {
            input = toArabian(input);
        }

        String num1 = "";
        String num2 = "";

        int k = 0;
        while (operations.indexOf(input.charAt(k)) == -1) {
            num1 += input.charAt(k++);
        }
        char op = input.charAt(k++);
        while (k < input.length() && operations.indexOf(input.charAt(k)) == -1) {
            num2 += input.charAt(k++);
        }

        if (k < input.length()) {
            throw new Exception("Too many operands");
        }


        int n1 = stringToInt(num1);
        int n2 = stringToInt(num2);

        if (n1 < 1 || n1 > 10 || n2 < 1 || n2 > 10) {
            throw new Exception("The number in more than 10 or less than 1");
        }

        int result;

        if (op == '+') {
            result = n1 + n2;
        } else if (op == '-') {
            result = n1 - n2;
        } else if (op == '*') {
            result = n1 * n2;
        } else {
            result = n1 / n2;
        }

        if (type == Type.ROMAN) {
            return toRoman(result);
        }

        return Integer.toString(result);
    }

    // Converting a variable from string type to int type
    static int stringToInt(String input) {
        int res = 0;
        for (int i = 0; i < input.length(); i++) {
            res+= (input.charAt(i) - 48) * Math.pow(10, input.length() - i - 1);
        }
        return res;
    }

    static Type getType(char a) throws Exception {
        String roman = "IVXLC";
        if (a >= 48 && a <= 57) {
            return Type.ARABIAN;
        } else if (roman.indexOf(a) != -1) {
            return Type.ROMAN;
        } else {
            throw new Exception("Incorrect type of number");
        }
    }
    static boolean checkCorrect(String input) {
        try {
            Type currentType = getType(input.charAt(0));
            for (int i = 1; i < input.length(); i++) {
                if (operations.indexOf(input.charAt(i)) == -1 && getType(input.charAt(i)) != currentType) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    static String toRoman(int input) throws Exception {
        if (input <= 0) {
            throw new Exception("A Roman number cannot be less than or equal to zero");
        }
        String roman = "";
        while(input > 0) {
            for(int i = 0; i < input / 100; i++) {
                roman += "C";
            }
            input %= 100;
            for(int i = 0; i < input / 90; i++) {
                roman += "XC";
            }
            input %= 90;
            for(int i = 0; i < input / 50; i++) {
                roman += "L";
            }
            input %= 50;
            for(int i = 0; i < input / 40; i++) {
                roman += "XL";
            }
            input %= 40;
            for (int i = 0; i < input / 10; i++) {
                roman += "X";
            }
            input %= 10;
            for (int i = 0; i < input / 9; i++) {
                roman += "IX";
            }
            input %= 9;
            for (int i = 0; i < input / 5; i++) {
                roman += "V";
            }
            input %= 5;
            for (int i = 0; i < input / 4; i++) {
                roman += "IV";
            }
            input %= 4;
            for (int i = 0; i < input; i++) {
                roman += "I";
            }
            input %= 1;
        }
        return roman;
    }
    static String toArabian(String input) {
        String arabian = "";
        int res = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'C') {
                res += 100;
            } else if (input.charAt(i) == 'L') {
                res += 50;
            } else if (input.charAt(i) == 'X') {
                if (i < input.length() - 1 && input.charAt(i + 1) == 'L') {
                    res += 40;
                    i++;
                } else if (i < input.length() - 1 && input.charAt(i + 1) == 'C') {
                    res += 90;
                    i++;
                } else {
                    res += 10;
                }
            } else if (input.charAt(i) == 'V') {
                res += 5;
            } else if (input.charAt(i) == 'I') {
                if (i < input.length() - 1 && input.charAt(i + 1) == 'X') {
                    res += 9;
                    i++;
                } else if (i < input.length() - 1 && input.charAt(i + 1) == 'V') {
                    res += 4;
                    i++;
                } else {
                    res += 1;
                }
            } else {
                arabian += Integer.toString(res);
                arabian += input.charAt(i);
                res = 0;
            }
        }
        arabian += Integer.toString(res);
        return arabian;
    }
}