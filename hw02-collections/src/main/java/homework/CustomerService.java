package homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private static final Comparator<Customer> DEFAULT_COMPARING_LOGIC = Comparator.comparingLong(Customer::getScores);

    private final NavigableMap<Customer, String> customersMap;

    public CustomerService() {
        this.customersMap = new TreeMap<>(DEFAULT_COMPARING_LOGIC);
    }

    public Map.Entry<Customer, String> getSmallest() {
        var value = customersMap.firstEntry();
        return Objects.nonNull(value) ? deepCopy(value) : null;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var value = customersMap.higherEntry(customer);
        return Objects.nonNull(value) ? deepCopy(value) : null;
    }

    public void add(Customer customer, String data) {
        customersMap.put(customer, data);
    }

    private Map.Entry<Customer, String> deepCopy(Map.Entry<Customer, String> original) {
        try {
            var copiedCustomer = original.getKey().clone();
            return new CopiedEntry(copiedCustomer, original.getValue());
        } catch (CloneNotSupportedException e) {
            LOGGER.error("Error occurred while copying customer..", e.getMessage());
            throw new RuntimeException(e);
            //вообще такого кейса быть не должно, но всё же тут лучше падать чем ломать инвариант
        }
    }
}
