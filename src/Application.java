//lambda expressions allow you to use less code to 
//pass methods to other methods.
//it could be done before java 8, but it took more code
//you (usually? always?) need interfaces
//teacher says they are good for threads, which I haven't studied yet.

interface Executable {

	void execute();
}

class Runner {

	public void run(Executable e) {
		System.out.println("Executing running code ...");
		e.execute();
	}
}

public class Application {

	public static void main(String[] args) {

		Runner runner1 = new Runner();

		// before java 8, it took all this code to do this:
		runner1.run(new Executable() {
			public void execute() {
				System.out.println("Hello, Kitty.");
			}
		});

		System.out.println();

		// java 8 brought lambda expressions:
		runner1.run(() -> System.out.println("Hello, Lambda Kitty."));
		// much shorter.

		// what if want a whole block of code to execute?
		//just put the part after -> into brackets
		//and type in complete statements in the block
		runner1.run(() -> {
			System.out.println();
			System.out.println("This is a bigger lambda expression.");
			System.out.println("Hello, Lambda Kitty.");
		});

		//lambda expressions still need interfaces
		
		//what if we want to return values using lambda expressions?
		//see example below, with a new interface and class
		
		Walker walker1 = new Walker();
		walker1.run2(() -> {
			System.out.println("This is code for the walker.");
			return 7;
			//that returns 7 to the class Walker, method run2
			//method run2 calls an Executable2 argument
			//I don't know what line "int execute2();" does
		});
		
		//if the only thing you want to do is return a value
		//you don't even need the brackets and "return.
		//just type it like this:
		walker1.run2(() -> 8);
		
		//what if the block of code you pass to the lambda exp
		//contains parameters?
		//we don't want to pass literal values to lambda exprs,
		//but sometimes the method that gets passed already has
		//arguments passed to it.
		//check out the Stroller class, etc below.
		
		Stroller stroller1 = new Stroller();
		
		//you can use variables within the lambda expressions.
		int mult = 2;
		
		//I think you can also define new variables within the lambda expr
		//but you can't re-define variables declared outside the scope of the lambda expr
		
		stroller1.run3((int pace) -> {
			return pace*mult; //returns 3*2 = 6.
		});
		//see how you included the same parameter here
		//as you did in the execute3 method below?
		//that's all it is.
		//the value passed to the method gets done elsewhere
		//but the syntax is mimiced here.
		
		//you can also have multiple parameters
		//and you don't necessarily have to declare the variable type
		//java infers them from the methods that are passed.
		//if there might be confusion, though, like one method takes
		//a string and another takes an int, you'll have to declare
	}

}

interface Executable2{
	int execute2();
}

class Walker {
	public void run2(Executable2 e) {
		System.out.println();
		System.out.println("Executing walking code...");
		int value = e.execute2();
		System.out.println("Return value is: " + value);
		
	}
}

interface Executable3{
	int execute3(int pace);
}

class Stroller {
	public void run3(Executable3 e) {
		System.out.println();
		System.out.println("Executing strolling code...");
		int value = e.execute3(3); //3 like 3mph, you just need some int here to pass.
		System.out.println("Return value is: " + value);
		
	}
}
