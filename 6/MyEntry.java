import java.util.Map;
import java.util.Objects;

public class MyEntry<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;

    public MyEntry (K key,V value){
        this.key=key;
        this.value=value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V val = this.value;
        this.value = value;
        return val;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyEntry)) return false;
        MyEntry<?, ?> myEntry = (MyEntry<?, ?>) o;
        return Objects.equals(getKey(), myEntry.getKey()) &&
                Objects.equals(getValue(), myEntry.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
