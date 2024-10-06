package lab6_2;

import java.util.Scanner;

class Researcher {
    private String name;
    private String workplace;
    private int publications;

    public Researcher(String name, String workplace, int publications) {
        this.name = name;
        this.workplace = workplace;
        this.publications = publications;
    }

    public String getName() {
        return name;
    }

    public String getWorkplace() {
        return workplace;
    }

    public int getPublications() {
        return publications;
    }

    public String toString() {
        return "Researcher {" + "name:" + name + ", workplace:" + workplace +
                ", publications:" + publications + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество научных работников: ");
        int numResearchers = sc.nextInt();
        sc.nextLine();

        if (numResearchers <= 0) {
            System.out.println("Научные работники отсутствуют.");
            return;
        }

        Researcher[] researchers = new Researcher[numResearchers];

        for (int i = 0; i < numResearchers; i++) {
            System.out.print("Введите ФИО научного работника: ");
            String name = sc.nextLine();

            System.out.print("Введите место работы научного работника: ");
            String workplace = sc.nextLine();

            System.out.print("Введите число публикаций научного работника: ");
            int publications = sc.nextInt();
            sc.nextLine();

            researchers[i] = new Researcher(name, workplace, publications);
        }

        if (researchers.length == 0) {
            System.out.println("Нет научных сотрудников для анализа.");
            return;
        }

        int minPublications = researchers[0].getPublications();
        int minIndex = 0;

        for (int i = 1; i < researchers.length; i++) {
            if (researchers[i].getPublications() < minPublications) {
                minPublications = researchers[i].getPublications();
                minIndex = i;
            }
        }

        System.out.println("Научный работник с минимальным количеством публикаций: " + researchers[minIndex]);
        System.out.println("Index: " + minIndex);
    }
}
