import java.util.*;

public class Map<K,V> extends AbstractMap<K,V> {
    List<Entry<K, V>> list;

    public Map() {
        list = new ArrayList<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        Iterator<Entry<K, V>> iterator = list.iterator();
        while (iterator.hasNext()) set.add(iterator.next());
        return set;
    }

    public V put(K k, V v) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(k)) {
                V result = list.get(i).getValue();
                list.get(i).setValue(v);
                return result;
            }
        }
        list.add(new MyEntry(k, v));
        return null;
    }

    public V get(Object k) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(k)) return list.get(i).getValue();
        }
        return null;
    }

    public boolean containsValue(Object v){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getValue().equals(v))return true;
        }
        return false;
    }

    public boolean containsKey(Object k){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getKey().equals(k))return true;
        }
        return false;
    }

    public V putIfAbsent(K k, V v){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getKey().equals(k)){
               return null;
            }
        }
        list.add(new MyEntry(k,v));
        return null;
    }

    public V remove(Object key){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getKey().equals(key)){
                V value =list.get(i).getValue();
                list.remove(i);
                return value;
            }
        }
        return null;
    }

    public Collection<V> values(){
        List<V> list1=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list1.add(list.get(i).getValue());
        }
        return list1;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Iterator<Entry<K, V>> iterator = list.iterator();
        while (iterator.hasNext()) set.add(iterator.next().getKey());
        return set;
    }

    public boolean isEmpty(){
        if(list.size()==0)return true;
        return false;
    }

    public int size(){
        return list.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map)) return false;
        if (!super.equals(o)) return false;
        Map<?, ?> map = (Map<?, ?>) o;
        if(map.size()!=size())return false;
        for(Map.Entry<?,?> entry : list){
            if(!map.containsKey(entry.getKey()) || !map.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), list);
    }

    @Override
    public String toString() {
        return "Map{" +
                "list=" + list.toString() +
                '}';
    }
}
