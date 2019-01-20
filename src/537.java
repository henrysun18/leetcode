/**
 * Created by Henry in 2017.
 * Problem 537
 */
class ComplexNumberMultiplication {
	public String complexNumberMultiply(String num1, String num2) {

		int num1PlusIndex = num1.indexOf('+');
		int num2PlusIndex = num2.indexOf('+');

		int num1a = Integer.parseInt(num1.substring(0, num1PlusIndex));
		int num1b = Integer.parseInt(num1.substring(num1PlusIndex + 1, num1.length() - 1));
		int num2a = Integer.parseInt(num2.substring(0, num2PlusIndex));
		int num2b = Integer.parseInt(num2.substring(num2PlusIndex + 1, num2.length() - 1));

		int real = (num1a * num2a) - (num1b * num2b);
		int complex = (num1a * num2b) + (num1b * num2a);

		return real + "+" + complex + "i";
	}
}