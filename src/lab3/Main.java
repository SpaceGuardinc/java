package lab3;

import java.util.Scanner;

class Engineer {
    private String fullName;
    private String department;
    private int experience;

    public Engineer(String fullName, String department, int experience) {
        this.fullName = fullName;
        this.department = department;
        this.experience = experience;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Инженер{" +
                "полное имя='" + fullName + '\'' +
                ", отдел='" + department + '\'' +
                ", опыт работы=" + experience +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numEngineers = 0;
        while (true) {
            try {
                System.out.print("Введите количество инженеров: ");
                numEngineers = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите корректное число.");
            }
        }

        Engineer[] engineers = new Engineer[numEngineers];
        for (int i = 0; i < numEngineers; i++) {
            System.out.println("Введите данные для инженера " + (i + 1) + ":");
            System.out.print("Полное имя: ");
            String fullName = scanner.nextLine();
            System.out.print("Отдел: ");
            String department = scanner.nextLine();
            int experience = 0;
            while (true) {
                try {
                    System.out.print("Опыт работы: ");
                    experience = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Пожалуйста, введите целое число для опыта работы.");
                }
            }
            engineers[i] = new Engineer(fullName, department, experience);
        }

        System.out.println("Информация об инженерах:");
        for (Engineer engineer : engineers) {
            System.out.println(engineer);
        }

        double totalExperience = 0;
        int count = 0;
        for (Engineer engineer : engineers) {
            if (engineer.getDepartment().length() >= 9) {
                totalExperience += engineer.getExperience();
                count++;
            }
        }

        try {
            if (count == 0) {
                throw new ArithmeticException("Инженеры в отделах с длиной названия не менее 9 символов не найдены.");
            }
            double averageExperience = totalExperience / count;
            System.out.println("Средний опыт работы инженеров в отделах с длиной названия не менее 9 символов: " + averageExperience);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}