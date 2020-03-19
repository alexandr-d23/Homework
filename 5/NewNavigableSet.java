import java.util.*;

public class NewNavigableSet <T> extends AbstractSet<T> implements NavigableSet<T> {
    private List<T> list;
    private Comparator<T> comparator;
    private boolean desc;
    /**
     * Constructs a new empty NewNavigableSet, sorted according to the natural ordering of its elements.
     * All elements inserted into the set must implement the Comparable interface.
     */
    public NewNavigableSet(){
        list=new ArrayList<>();
        comparator=null;
    }

    /**
     * Constructs a new, empty NewNavigableSet, sorted according to the specified comparator.
     * All elements inserted into the set must be mutually comparable by the specified comparator.
     * @param comparator
     */
    public NewNavigableSet(Comparator<T> comparator){
        list=new ArrayList<>();
        this.comparator=comparator;
    }
    /**
     * Constructs a new NewNavigableSet containing the elements in the specified collection,
     * sorted according to the specified comparator...
     * All elements inserted into the set must be mutually comparable by the specified comparator
     */
    public NewNavigableSet(Comparator<T> comparator,Collection<T> array){
        list=new ArrayList<>();
        this.comparator=comparator;
        addAll(array);
    }

    /**
     * Constructs a new NewNavigableSet containing the elements in the specified collection,
     * sorted according to the natural ordering of its elements. All elements inserted
     * into the set must implement the Comparable interface.
     * @param array
     */
    public NewNavigableSet(Collection<? extends Comparable<T>> array){
        list=new ArrayList<>();
        comparator=null;
        addAll((Collection<T>)array);
    }


    /**
     * {@inheritDoc}
     *
     * <p>This implementation iterates over the specified collection, and adds
     * each object returned by the iterator to this collection, in turn.
     *
     * <p>Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> unless <tt>add</tt> is
     * overridden (assuming the specified collection is non-empty).
     *
     * @param array
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IllegalStateException         {@inheritDoc}
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends T> array) {
        Iterator it=array.iterator();
        while(it.hasNext())add((T)it.next());
        return super.addAll(array);
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * @param t
     */
    public boolean add(T t){
        if(t==null)throw new NullPointerException();
        if(comparator==null){
            try{
                Comparable t1=(Comparable)(t);
            }
            catch (Exception e){
                throw new ClassCastException();
            }
        }
        try {
            int index = BinarySearch(t);
            if(index>0)return false;
            list.add(index*-1,t);
            return true;
        }
        catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
    /**
     * Returns the greatest element in this set strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T lower(T t) {
        int index;
        try {
            index = BinarySearch(t);
        }
        catch (Exception e){
            throw new ClassCastException();
        }
        try {
            if (index > 1) return list.get(index - 1);
            if (index < 0) return list.get(index*-1-1);
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    /**
     * Returns the greatest element in this set less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the greatest element less than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T floor(T t) {
        int index;
        try {
            index = BinarySearch(t);
        }
        catch (Exception e){
            throw new ClassCastException();
        }
        try {
            if (index > 1) return (list.get(index));
            if (index < 0) return  (list.get(index*-1-1));
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    /**
     * Returns the least element in this set greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than or equal to {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T ceiling(T t) {
        int index;
        try {
            index = BinarySearch(t);
        }
        catch (Exception e){
            throw new ClassCastException();
        }
        try {
            if (index > 1) return list.get(index);
            if (index < 0) return list.get(index*-1);
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    /**
     * Returns the least element in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param t the value to match
     * @return the least element greater than {@code e},
     * or {@code null} if there is no such element
     * @throws ClassCastException   if the specified element cannot be
     *                              compared with the elements currently in the set
     * @throws NullPointerException if the specified element is null
     *                              and this set does not permit null elements
     */
    @Override
    public T higher(T t) {
        int index;
        try {
            index = BinarySearch(t);
        }
        catch (Exception e){
            throw new ClassCastException();
        }
        try {
            if (index > 1) return list.get(index + 1);
            if (index < 0) return list.get(index*-1);
        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the first element, or {@code null} if this set is empty
     */
    @Override
    public T pollFirst() {
        if(list.size()==0)return null;
        T t=list.get(0);
        list.remove(0);
        return t;
    }

    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this set is empty.
     *
     * @return the last element, or {@code null} if this set is empty
     */
    @Override
    public T pollLast() {
        if(list.size()==0)return null;
        T t=list.get(list.size()-1);
        list.remove(list.size()-1);
        return t;
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return  new NSIterator<>();
    }

    /**
     * Returns a reverse order view of the elements contained in this set.
     * The descending set is backed by this set, so changes to the set are
     * reflected in the descending set, and vice-versa.  If either set is
     * modified while an iteration over either set is in progress (except
     * through the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.
     *
     * <p>The returned set has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code s.descendingSet().descendingSet()} returns a
     * view of {@code s} essentially equivalent to {@code s}.
     *
     * @return a reverse order view of this set
     */
    @Override
    public NavigableSet<T> descendingSet() {
        NewNavigableSet<T> set;
        if(comparator==null)set = new NewNavigableSet<T>();
        else set=new NewNavigableSet<>(comparator);
        Iterator<T> iterator=descendingIterator();
        while(iterator.hasNext())set.list.add(iterator.next());
        set.desc=true;
        return set;
    }

    /**
     * Returns an iterator over the elements in this set, in descending order.
     * Equivalent in effect to {@code descendingSet().iterator()}.
     *
     * @return an iterator over the elements in this set, in descending order
     */
    @Override
    public Iterator<T> descendingIterator() {
        return new DesIterator<>();
    }

    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
     * {@code toElement} are equal, the returned set is empty unless {@code
     * fromInclusive} and {@code toInclusive} are both true.  The returned set
     * is backed by this set, so changes in the returned set are reflected in
     * this set, and vice-versa.  The returned set supports all optional set
     * operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement   low endpoint of the returned set
     * @param fromInclusive {@code true} if the low endpoint
     *                      is to be included in the returned view
     * @param toElement     high endpoint of the returned set
     * @param toInclusive   {@code true} if the high endpoint
     *                      is to be included in the returned view
     * @return a view of the portion of this set whose elements range from
     * {@code fromElement}, inclusive, to {@code toElement}, exclusive
     * @throws ClassCastException       if {@code fromElement} and
     *                                  {@code toElement} cannot be compared to one another using this
     *                                  set's comparator (or, if the set has no comparator, using
     *                                  natural ordering).  Implementations may, but are not required
     *                                  to, throw this exception if {@code fromElement} or
     *                                  {@code toElement} cannot be compared to elements currently in
     *                                  the set.
     * @throws NullPointerException     if {@code fromElement} or
     *                                  {@code toElement} is null and this set does
     *                                  not permit null elements
     * @throws IllegalArgumentException if {@code fromElement} is
     *                                  greater than {@code toElement}; or if this set itself
     *                                  has a restricted range, and {@code fromElement} or
     *                                  {@code toElement} lies outside the bounds of the range.
     */
    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        int a=0;
        if(toInclusive)a=-1;
        if(compare(fromElement,toElement)>0)throw new IllegalArgumentException();
        if(fromElement==null||toElement==null)throw new NullPointerException();
        try{
            int index=BinarySearch(fromElement);
            if(index<0)index*=-1;
            if(!fromInclusive)index+=1;
            while(compare(toElement, list.get(index)) > a && index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }
    /**
     * Returns a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}.  The
     * returned set is backed by this set, so changes in the returned set are
     * reflected in this set, and vice-versa.  The returned set supports all
     * optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint of the returned set
     * @param inclusive {@code true} if the high endpoint
     *                  is to be included in the returned view
     * @return a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}
     * @throws ClassCastException       if {@code toElement} is not compatible
     *                                  with this set's comparator (or, if the set has no comparator,
     *                                  if {@code toElement} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code toElement} cannot be compared to elements
     *                                  currently in the set.
     * @throws NullPointerException     if {@code toElement} is null and
     *                                  this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *                                  restricted range, and {@code toElement} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        int a=0;
        if(inclusive)a=-1;
        if(toElement==null)throw new NullPointerException();
        try{
            int index=0;
            while(compare(toElement, list.get(index)) > a && index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }

    /**
     * Returns a view of the portion of this set whose elements are greater
     * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
     * The returned set is backed by this set, so changes in the returned set
     * are reflected in this set, and vice-versa.  The returned set supports
     * all optional set operations that this set supports.
     *
     * <p>The returned set will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint of the returned set
     * @param inclusive   {@code true} if the low endpoint
     *                    is to be included in the returned view
     * @return a view of the portion of this set whose elements are greater
     * than or equal to {@code fromElement}
     * @throws ClassCastException       if {@code fromElement} is not compatible
     *                                  with this set's comparator (or, if the set has no comparator,
     *                                  if {@code fromElement} does not implement {@link Comparable}).
     *                                  Implementations may, but are not required to, throw this
     *                                  exception if {@code fromElement} cannot be compared to elements
     *                                  currently in the set.
     * @throws NullPointerException     if {@code fromElement} is null
     *                                  and this set does not permit null elements
     * @throws IllegalArgumentException if this set itself has a
     *                                  restricted range, and {@code fromElement} lies outside the
     *                                  bounds of the range
     */
    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        if(fromElement==null)throw new NullPointerException();
        try{
            int index=BinarySearch(fromElement);
            if(index<0)index*=-1;
            if(!inclusive)index+=1;
            while(index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }

    /**
     * Returns the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this set,
     * or <tt>null</tt> if this set uses the natural ordering
     * of its elements
     */
    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code subSet(fromElement, true, toElement, false)}.
     *
     * @param fromElement
     * @param toElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        if(compare(fromElement,toElement)>0)throw new IllegalArgumentException();
        if(fromElement==null||toElement==null)throw new NullPointerException();
        try{
            int index=BinarySearch(fromElement);
            if(index<0)index*=-1;
            while(compare(toElement, list.get(index)) > 0 && index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code headSet(toElement, false)}.
     *
     * @param toElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> headSet(T toElement) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        if(toElement==null)throw new NullPointerException();
        try{
            int index=0;
            while(compare(toElement, list.get(index)) > 0 && index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code tailSet(fromElement, true)}.
     *
     * @param fromElement
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        NewNavigableSet<T> set=new NewNavigableSet<>();
        if(fromElement==null)throw new NullPointerException();
        try{
            int index=BinarySearch(fromElement);
            if(index<0)index*=-1;
            while(index < list.size()){
                set.add(list.get(index));
                index++;
            }
            return set;
        }
        catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        catch (Exception e){
            throw new ClassCastException();
        }
    }

    /**
     * Returns the first (lowest) element currently in this set.
     *
     * @return the first (lowest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T first() {
        if(list.size()==0)return null;
        return list.get(0);
    }

    /**
     * Returns the last (highest) element currently in this set.
     *
     * @return the last (highest) element currently in this set
     * @throws NoSuchElementException if this set is empty
     */
    @Override
    public T last() {
        if(list.size()==0)return null;
        return list.get(list.size()-1);
    }

    /**
     *
     * @return the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return list.size();
    }

    private int compare(T o1,T o2){
        int a=1;
        if(desc)a=-1;
        if(comparator==null){
            Comparable t=(Comparable)(o1);
            Comparable t1=(Comparable)(o2);
            try {
                return a*t.compareTo(t1);
            }
            catch (Exception e){
                throw new ClassCastException();
            }
        }
        return a*comparator.compare(o1,o2);
    }

    private class NSIterator<T> implements Iterator<T> {

        private int cursor;
        private int last;
        private NSIterator(){
            last=-1;
        }

        @Override
        public boolean hasNext() {
            return list.size()>cursor;
        }

        @Override
        public T next() {
            if(list.size()<=cursor) throw new NoSuchElementException();
            last=cursor;
            cursor++;
            return (T)list.get(last);
        }

        @Override
        public void remove() {
            if(last==-1)throw new IllegalStateException();
            list.remove(last);
            cursor--;
            last=-1;
        }
    }

    private class DesIterator<T> implements Iterator<T> {

        private int cursor;
        private int last;

        private DesIterator() {
            cursor = list.size() - 1;
            last = -1;
        }
        @Override
        public boolean hasNext() {
            return list.size() > cursor && cursor >= 0;
        }

        @Override
        public T next() {
            if (cursor < 0) throw new NoSuchElementException();
            last = cursor;
            cursor--;
            return (T) list.get(last);
        }
        @Override
        public void remove() {
            if (last == -1) throw new IllegalStateException();
            list.remove(last);
            cursor--;
            last = -1;
        }
    }

    private int BinarySearch(T o){
        int firstIndex=0;
        int lastIndex=list.size()-1;
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (compare(list.get(middleIndex),o)==0) {
                return middleIndex;
            }
            else if (compare(list.get(middleIndex),o)<0)
                firstIndex = middleIndex + 1;
            else if (compare(list.get(middleIndex),o)>0)
                lastIndex = middleIndex - 1;

        }
        return firstIndex*-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewNavigableSet)) return false;
        if (!super.equals(o)) return false;
        NewNavigableSet<?> that = (NewNavigableSet<?>) o;
        return Objects.equals(list, that.list) &&
                Objects.equals(comparator, that.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), list, comparator);
    }
}