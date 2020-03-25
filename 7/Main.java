import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        /*
        Компаратор в виде лямбда-выражения
         */
        Comparator<String> com = (s1,s2)->s1.compareTo(s2);
        /*
        Вывести все элементы листа, которые больше самого большого элемента второго листа
         */
        List<Integer> list = new ArrayList<>();
        Random r=new Random();
        List<Integer> list2=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(r.nextInt(100));
            list2.add(r.nextInt(40));
        }
        System.out.println(list.toString());
        System.out.println(list2.toString());
        list.stream()
                    .filter(a->a.compareTo(list2.stream().max((f,s)->f.compareTo(s)).get())>0)
                    .forEach(System.out::println);
        /*
        Посчитать количество строк в set, в которых количество гласных больше трёх.
         */
        List<Character> l=Arrays.asList('a','e','y','u','o','i');
        Set<String> set=new HashSet<>();
        set.add("asdaasdsa");
        set.add("aaaaa");
        set.add("asd");
        System.out.println(set.stream()
                                .filter(s->s.chars()
                                        .filter(a->l.contains((char)a))
                                        .count()>3)
                                .count());

        /*
        Посчитать количество строк в set, в которых количество гласных больше трёх.
         */
        TreeMap<String,String> map=new TreeMap<>();
        map.put("a","b");
        map.put("g","a");
        String result = map.keySet().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
        System.out.println(result);
        /*
        Получить сумму длин строк коллекции, которые длиннее 5-ти символов
         */
        List<String> listS =new ArrayList<>();
        listS.add("aasdsfsddasd");
        listS.add("a");
        int c=listS.stream()
                .mapToInt(String::length)
                .filter(i->i>5)
                .sum();
        System.out.println(c);
    }
}

