package homework;

import java.util.Map;

record CopiedEntry(Customer key, String value) implements Map.Entry<Customer, String> {

    @Override
    public Customer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        throw new UnsupportedOperationException("not supported");
    }
}
