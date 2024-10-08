package lab7;

import java.util.Objects;
import java.util.Scanner;

class Grade{
    int grade;

    public Grade(int grade){
        this.grade = grade;
    }

    public int getter_grade(){
        return grade;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int counts = 0;
        String olimpiada = "";
        System.out.println("Введите сдал или не сдал");
        olimpiada = input.nextLine();
        if (Objects.equals(olimpiada, "Сдал")){
            System.out.println("Оценка за экзамен отлично");
        } else {
            while (true) {
                try {
                    System.out.println("Введите количество баллов");
                    counts = input.nextInt();
                    if (counts > 100) {
                        System.out.println("Максимальная оценка за экзамен 100 баллов!");
                    } else break;
                } catch (Exception e) {
                    System.out.println("Вы ввели не число");
                }
            }
            Grade grade = new Grade(counts);

            if (grade.getter_grade() >= 85 && grade.getter_grade() <= 100) {
                System.out.println("Оценка за экзамен: Отлично");
            } else if (grade.getter_grade() >= 70 && grade.getter_grade() <= 84) {
                System.out.println("Оценка за экзамен: Хорошо");
            } else if (grade.getter_grade() >= 55 && grade.getter_grade() <= 69) {
                System.out.println("Оценка за экзамен: Удовлетворительно");
            } else {
                System.out.println("Оценка за экзамен: Неудовлетворительно");
            }
        }
    }
}