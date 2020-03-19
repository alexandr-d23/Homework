import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UMCollection<T> extends AbstractCollection<T> implements Iterable<T>{
    private Object[] array;
    private int size;
    public UMCollection(Collection<? extends T> col){
        Iterator it=col.iterator();
        size=col.size();
        for(int i=0;i<size;i++){
            array[i]=it.next();
        }

    }
    public UMCollection(){
        array=new Object[0];
        size=0;
    }
    @Override
    public Iterator<T> iterator() {
        return new UMIterator<T>(array,size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UMCollection)) return false;
        UMCollection<T> that = (UMCollection<T>) o;
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

    public class UMIterator<T> implements Iterator<T> {
        private Object[] array;
        private int length;
        private int cursor;
        public UMIterator(Object[] array, int length){
            this.array=array;
            this.length=length;
            cursor=0;
        }

        @Override
        public boolean hasNext() {
            return length>cursor;
        }

        @Override
        public T next() {
            try{cursor++;
                return (T)array[cursor-1];
            }
            catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
        }
    }


}
