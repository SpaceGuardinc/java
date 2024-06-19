import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Workers {
    String FIO;
    String placeWork;

    public Workers(String FIO, String placeWork) {
        this.FIO = FIO;
        this.placeWork = placeWork;
    }

    @Override
    public String toString() {
        return "Workers{" +
                "FIO='" + FIO + '\'' +
                ", place work='" + placeWork + '\'' +
                '}';
    }
}

class Worker extends Workers {
    int rank;

    public Worker(String FIO, String placeWork, int rank) {
        super(FIO, placeWork);
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rank=" + rank +
                '}';
    }
}

class ScienceWorker extends Worker {
    int numberPublic;

    public ScienceWorker(String FIO, String placeWork, int rank, int numberPublic) {
        super(FIO, placeWork, rank);
        this.numberPublic = numberPublic;
    }

    @Override
    public String toString() {
        return "ScienceWorker{" +
                "FIO='" + FIO + '\'' +
                ", place work='" + placeWork + '\'' +
                ", rank=" + rank +
                ", numberPublic=" + numberPublic +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите количество сотрудников");
        int countObjects = input.nextInt();
        List<Workers> allWorkers = new ArrayList<>();
        input.nextLine();
        for (int iterator = 0; iterator < countObjects; iterator++) {
            System.out.println("Введите ФИО");
            String FIO = input.next();
            System.out.println("Введите место работы");
            String placeWork = input.next();
            System.out.println("Введите степень сотрудника");
            int rank = input.nextInt();
            System.out.println("Введите кол-во публикаций (для научного работника)");
            int countPublics = input.nextInt();

            ScienceWorker scienceWorker = new ScienceWorker(FIO, placeWork, rank, countPublics);
            allWorkers.add(scienceWorker);
        }

        System.out.println("Перечень всех сотрудников: ");
        for (Workers worker : allWorkers) {
            System.out.println(worker);
        }
    }
}
