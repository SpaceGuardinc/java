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

    public int getPublications() {
        return publications;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Место работы: " + workplace + ", Публикаций: " + publications;
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

        // Ищем минимальное количество публикаций
        int minPublications = researchers[0].getPublications();
        boolean allSame = true;

        // Проверка на наличие одинакового количества публикаций у всех сотрудников
        for (int i = 1; i < researchers.length; i++) {
            if (researchers[i].getPublications() != minPublications) {
                allSame = false;
            }
            if (researchers[i].getPublications() < minPublications) {
                minPublications = researchers[i].getPublications();
            }
        }

        if (allSame) {
            System.out.println("Минимальное количество публикаций не найдено, так как все имеют одинаковое количество.");
        } else {
            // Если все не одинаковые, найти первого с минимальным количеством публикаций
            for (int i = 0; i < researchers.length; i++) {
                if (researchers[i].getPublications() == minPublications) {
                    System.out.println("Сотрудник с минимальным количеством публикаций: " + researchers[i]);
                    System.out.println("Индекс: " + i);
                    break;
                }
            }
        }
    }
}
