package lab2;

import java.util.Scanner;

import java.util.Scanner;

// Абстрактный суперкласс
abstract class Employee {
    private String fullName;
    private String workplace;

    public Employee(String fullName, String workplace) {
        this.fullName = fullName;
        this.workplace = workplace;
    }

    public String getFullName() {
        return fullName;
    }

    public String getWorkplace() {
        return workplace;
    }

    @Override
    public String toString() {
        return "Full Name: " + fullName + ", Workplace: " + workplace;
    }
}

// Подкласс "Рабочий"
class Worker extends Employee {
    private int grade;

    public Worker(String fullName, String workplace, int grade) {
        super(fullName, workplace);
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return super.toString() + ", Grade: " + grade;
    }
}

// Подкласс "Научный работник"
class Researcher extends Employee {
    private int publications;

    public Researcher(String fullName, String workplace, int publications) {
        super(fullName, workplace);
        this.publications = publications;
    }

    public int getPublications() {
        return publications;
    }

    @Override
    public String toString() {
        return super.toString() + ", Publications: " + publications;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ввод данных о сотрудниках
        System.out.println("Enter the number of employees:");
        int numEmployees = scanner.nextInt();
        Employee[] employees = new Employee[numEmployees];
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Enter full name for employee " + (i + 1) + ":");
            scanner.nextLine(); // consume newline
            String fullName = scanner.nextLine();
            System.out.println("Enter workplace for employee " + (i + 1) + ":");
            String workplace = scanner.nextLine();
            System.out.println("Is this employee a worker or a researcher? Enter 'w' for worker or 'r' for researcher:");
            char type = scanner.next().charAt(0);
            if (type == 'w') {
                System.out.println("Enter grade for worker " + (i + 1) + ":");
                int grade = scanner.nextInt();
                employees[i] = new Worker(fullName, workplace, grade);
            } else if (type == 'r') {
                System.out.println("Enter number of publications for researcher " + (i + 1) + ":");
                int publications = scanner.nextInt();
                employees[i] = new Researcher(fullName, workplace, publications);
            }
        }

        // Вывод рабочих, разряд которых не равен 1 и не равен 3
        System.out.println("\nWorkers with grades not equal to 1 and 3:");
        for (Employee employee : employees) {
            if (employee instanceof Worker && (((Worker) employee).getGrade() != 1 && ((Worker) employee).getGrade() != 3)) {
                System.out.println(employee);
            }
        }

        // Вычисление среднего числа публикаций для научных работников
        double totalPublications = 0;
        int numResearchers = 0;
        for (Employee employee : employees) {
            if (employee instanceof Researcher) {
                totalPublications += ((Researcher) employee).getPublications();
                numResearchers++;
            }
        }
        double avgPublications = (numResearchers > 0) ? totalPublications / numResearchers : 0;
        // Вывод научных работников, число публикаций которых больше среднего
        System.out.println("\nResearchers with publications greater than average:");
        for (Employee employee : employees) {
            if (employee instanceof Researcher && ((Researcher) employee).getPublications() > avgPublications) {
                System.out.println(employee);
            }
        }
    }
}