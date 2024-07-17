package homework;

import java.util.Stack;

public class CustomerReverseOrder {
    private final Stack<Customer> stack;

    public CustomerReverseOrder() {
        this.stack = new Stack<>();
    }

    public void add(Customer customer) {
        stack.add(customer);
    }

    public Customer take() {
        return stack.pop();
    }
}
