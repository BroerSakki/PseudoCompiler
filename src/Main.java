import classes.datatypes.Num;
import classes.datatypes.Statement;

public class Main {
	public static void main(String[] args) {
		String[] values = {"3.14", "2.71", "1.41"};
		Num numArray = new Num(values);

		Num numSingle = new Num("42");

		System.out.println("Single Num value: " + numSingle);
		System.out.println("Array Num values: " + numArray);

		Statement statement = new Statement("public static void main(String[] args)");
		System.out.println("Statement text: " + statement.toString());
	}
}
