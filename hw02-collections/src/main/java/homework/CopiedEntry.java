package homework;

import java.util.Map;

class CopiedEntry implements Map.Entry<Customer, String> {
    private final Customer key;
    private final String value;

    CopiedEntry(Customer key, String value) {
        this.key = key;
        this.value = value;
    }

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
