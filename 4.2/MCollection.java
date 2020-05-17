import java.util.*;

public class MCollection<T> extends AbstractCollection<T> implements Iterable<T> {
    private Object[] array;
    private int size;
    public MCollection(Collection<? extends T> col){
        Iterator it=col.iterator();
        size=col.size();
        for(int i=0;i<size;i++){
            array[i]=it.next();
        }
    }

    public MCollection(){
        array=new Object[0];
        size=0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MIterator<T>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        if(size==array.length){
            array= Arrays.copyOf(array,(array.length+1)*2);
        }
        array[size]=t;
        size++;
        return true;
    }

    @Override
    public String toString() {
        return "MCollection{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MCollection)) return false;
        MCollection<T> that = (MCollection<T>) o;
        if(size!=that.size)return false;
        boolean[] arr=new boolean[size];
        boolean fl;
        for(int i=0;i<size;i++){
            fl=true;
            for(int j=0;j<size;j++){
                if(array[i].equals(that.array[j]) && !arr[j]){
                    fl=false;
                    arr[j]=true;
                }
            }
            if(fl)return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    private class MIterator<T> implements Iterator<T> {
        private int cursor;
        private int last;
        private MIterator(){
            last=-1;
        }

        @Override
        public boolean hasNext() {
            return size>cursor;
        }

        @Override
        public T next() {
            if(size<=cursor) throw new NoSuchElementException();
            last=cursor;
            cursor++;
            return (T)array[last];
        }

        @Override
        public void remove() {
            if(last==-1)throw new IllegalStateException();
            for(int i=last;i<size-1;i++){
                array[i]=array[i+1];
            }
            cursor--;
            size--;
            last=-1;
        }
    }
}
