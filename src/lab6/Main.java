package lab6;

import java.util.*;

class School {
    private int id;
    private String name;
    private int count;

    public School(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Школы " + "\n" +
                "ID: " + id + "\n" +
                "Название: " + name + "\n" +
                "Количество учащихся: " + count;
    }
}



public class Main {

    public static int getMaxSchool(List<School> schoolList) {
        return schoolList.stream()
                //.mapToInt(school -> school.getCount())
                .mapToInt(School::getCount)
                .max()
                .orElse(-1);
    }


    public static School getMaxSchoolIfMoreOne(List<School> schoolList) {
        int maxStudentsCount = getMaxSchool(schoolList);
        return schoolList.stream()
                .filter(school -> school.getCount() == maxStudentsCount)
                .toList()
                .getLast();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numSchools = 0;

        while (true) {
            try {
                System.out.println("Введите количество школ");
                numSchools = scanner.nextInt();
                if (numSchools <= 0) {
                    System.out.println("Число должно быть больше 0");
                } else break;
            } catch (Exception e) {
                System.out.println("Вы ввели не число");
                scanner.nextLine(); // Очищаем буфер
            }
        }

        List<School> schoolList = new ArrayList<>();

        for (int iterator = 0; iterator < numSchools; iterator++) {
            System.out.println("Информация о школе № " + (1 + iterator) + ":");
            int id;
            String name;
            int count;

            System.out.println("Введите ID:");
            id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите название:");
            name = scanner.nextLine();

            System.out.println("Введите количество студентов:");
            count = scanner.nextInt();

            schoolList.add(new School(id, name, count));
        }

        schoolList.forEach(System.out::println);
//        schoolList.forEach(school -> System.out.println(school));


        if (getMaxSchoolIfMoreOne(schoolList) != null && schoolList.size() > 1) {
            int lastIndex = schoolList.lastIndexOf(getMaxSchoolIfMoreOne(schoolList));
            System.out.println("Школа с максимальным количеством учеников: " + getMaxSchool(schoolList));
            System.out.println("Индекс последней школы: " + lastIndex);
        } else if (schoolList.size() == 1){
            System.out.println("Массив единичный, в этой школе " + getMaxSchool(schoolList) + " учеников");
        } else {
            System.out.println("Школ с максимальным количеством учеников не найдено.");
        }
    }
}