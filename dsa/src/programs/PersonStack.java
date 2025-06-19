package programs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonStack {
	private int stackPtr;
	List <Person> stack;
	
	{
		stackPtr = -1;
		stack = new ArrayList<>();
	}
	
	public void push(Person person) {
		stack.add(person);
		stackPtr++;
	}
	
	public Person pop() {
		if(stackPtr == -1) { // check if the stack is empty
			System.out.println("Stack is empty");
			return null; // to say that no element of the stack was popped
		}
		Person person = stack.get(stackPtr); // copy last ele in list
		stack.remove(stackPtr); // delete last ele from list
		stackPtr--;
		return person; // return the popped element from the stack
	}
	
	public void displayStack() {
		if(stack.isEmpty()) {
			System.out.println("Stack is empty");
			return;
		}
		System.out.printf("%-5s %s", "ID", "NAME");
		System.out.println("\n------------------");
		for(int i = stack.size()-1; i >= 0; i--) {
			Person person = stack.get(i);
			int id = person.getId();
			String name = person.getName();
			System.out.printf("%-5d %s\n", id, name);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PersonStack personStack = new PersonStack();
		int choice = 0;
		int numberOfOperations = 10;
		do {
			System.out.print("1:Push 2:Pop 3:DisplayStack 4:Exit. \t Your choice? ");
		choice = scanner.nextInt();
		switch(choice) {
		case 1 : 
			System.out.println("Enter Id and Name of the Person:");
			int id = scanner.nextInt();
			String name = scanner.next();
			personStack.push(new Person(id, name));
			break;
		case 2 :
			Person person = personStack.pop();
			if(person != null)
				System.out.println("Popped Person is " + person);
			break;
		case 3 :
			personStack.displayStack();
			break;
		case 4 :
			personStack.stack = null;
			numberOfOperations = 0;
			break;
		default:
			System.out.println("Invalid choice enetered");
		}
		numberOfOperations--;
		}while(numberOfOperations >= 0);
		System.out.println("End of Program");
	}
}
