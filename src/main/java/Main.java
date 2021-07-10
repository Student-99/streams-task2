import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Количество несовершеннолетних
        long persons1 = persons.stream()
                .filter((Person p) -> p.getAge() < 18)
                .count();

        //список фамилий призывников
        List<Person> persons2 = persons.stream()
                .filter((Person p) -> p.getSex()==Sex.MAN)
                .filter((Person p) -> p.getAge() >= 18 && p.getAge() <= 27)
                .collect(Collectors.toList());

        //отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        List<Person> person3 = persons.stream()
                .filter((Person p) -> p.getEducation() == Education.HIGHER)
                .filter((Person p) -> {
                    if (p.getSex() == Sex.MAN) {
                        return (p.getAge() >= 18) & (p.getAge() <= 65);
                    }
                    return (p.getAge() >= 18) & (p.getAge() <= 60);
                })
                .sorted((Person p1, Person p2)-> p1.getFamily().compareTo(p2.getFamily()))
                .collect(Collectors.toList());

    }
}
