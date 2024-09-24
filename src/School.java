import java.util.*;

public class School{
    int id;
    String name;
    int count;

    public School(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getID() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return count;
    }


    public static int getMaxStudentCount(List<School> schoolList){
        int maxCountSchool =  schoolList.stream()
                .mapToInt(School::getCount)
                .max()
                .orElse(0);

        return schoolList.stream()
                .reduce((first, second) -> second)
                .map(School::getCount)
                .orElse(0);
    }


    @Override
    public String toString() {
        return "Школы " +
                "ID: " + id + "\n" +
                "Название: " + name + "\n" +
                "Количество учащихся: " + count;
    }


}