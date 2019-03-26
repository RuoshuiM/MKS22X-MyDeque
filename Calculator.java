public class Calculator {
    /**
     * Evaluate a postfix expression stored in s. Assume valid postfix notation,
     * separated by spaces.
     */
    public static double eval(String s) {
        String[] elements = s.split(" ");

        MyDeque<Double> stack = new MyDeque<>();
        for (String e : elements) {
            if (e.matches("([0-9]*.)?[0-9]+")) {
                stack.push(Double.parseDouble(e));
            } else {
                if (e.equals("+")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (e.equals("-")) {
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a - b);
                } else if (e.equals("*")) {
                    stack.push(stack.pop() * stack.pop());
                } else if (e.equals("/")) {
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a / b);
                } else if (e.equals("%")) {
                    double b = stack.pop();
                    double a = stack.pop();
                    stack.push(a % b);
                } else {
                    throw new IllegalArgumentException("Invalid token: " + e);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String...args) {
        String[] cases = {"10 2.0 +", "11 3 - 4 + 2.5 *", "8 2 + 99 9 - * 2 + 9 -", "1 2 3 4 5 + * - -"};
        double[] expected = {12.0, 30.0, 893.0, 26.0};
        for (int i = 0; i < cases.length; i++) {
            test(cases[i], expected[i]);
        }
    }

    private static void test(String s, double expected) {
        System.out.format("Expected: %f, got: %f%n", expected, eval(s));
    }
}
