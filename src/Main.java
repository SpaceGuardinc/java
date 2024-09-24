import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSchools = 0;

        while (true) {
            try {
                System.out.println("Введите количество школ");
                numSchools = scanner.nextInt();
                if (numSchools == 0){
                    System.out.println("Число должно быть больше 0");
                }
                else break;
            } catch (Exception e) {
                System.out.println("Вы ввели не число");
            }
            scanner.nextLine();
        }

        List<School> schoolList = new ArrayList<>();

        for (int iterator = 0; iterator < numSchools; iterator++) {
            System.out.println("Информация о школе № " + (1 + iterator) + ":");
            int id;
            String name;
            int count;

            System.out.println("Введите ID:");
            id = scanner.nextInt();

            // Очищаем буфер перед вводом строки
            scanner.nextLine();

            System.out.println("Введите название:");
            name = scanner.nextLine();

            System.out.println("Введите количество студентов:");
            count = scanner.nextInt();

            schoolList.add(new School(id, name, count));
        }

        for (School school : schoolList) {
            System.out.println(school);
        }

        int maxStudents = School.getMaxStudentCount(schoolList);

        System.out.println("Максимальное кол-во студентов " + maxStudents);

    }
}