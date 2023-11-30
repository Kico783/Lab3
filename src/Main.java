import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        var droids = new ArrayList<Droid>();
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 Створити дроїда");
            System.out.println("2 Показати список створених дроїдів");
            System.out.println("3 Запустити бій 1 на 1 (вибрати дроїдів, які будуть змагатися");
            System.out.println("4 Запустити бій команда на команду (сформувати команди суперників з дроїдів, яких ви створили у першому пункті)");
            System.out.println("5 Відтворити проведений бій зі збереженого файлу");
            System.out.println("6 Вийти з програми");
            switch (scanner.nextInt()) {
                case 1->droids.add(createDroid());
                case 2->showDroids(droids);
                case 3->battlePvP(droids,1);
                case 4->battlePvP(droids,3);
                case 5->watchBattle("battleLog.txt");
                case 6->System.exit(0);

            }
        }

    }

    public static void watchBattle(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        // Закриття файлу
        reader.close();
    }
    public static Droid createDroid() {
        var scanner = new Scanner(System.in);
        System.out.print("Введіть ім'я дрона:");
        String name = scanner.nextLine();
        System.out.println("""
                Виберіть вид дрона:
                1)CombatDroid
                2)HealingDroid
                3)StealthDroid
                4)MultiAttackDroid
                5)TacticalDroid
                6)SniperDroid
                7)TankDroid
                8)KamikazeDroid""");
        return switch (scanner.nextInt()) {
            case 1 -> new CombatDroid(name);
            case 2 -> new HealingDroid(name);
            case 3 -> new StealthDroid(name);
            case 4 -> new MultiAttackDroid(name);
            case 5 -> new TacticalDroid(name);
            case 6 -> new SniperDroid(name);
            case 8 -> new TankDroid(name);
            default -> new KamikazeDroid(name);
        };
    }

    public static void showDroids(List<Droid> droids) {
        System.out.println("Дроїди:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println(String.format("%d) %s)",i,droids.get(i).showInformation()));
        }
    }

    public static void battlePvP(List<Droid> droids,int peopleInSquad) {
        var chosenDroids = chooseDroids(droids,peopleInSquad);
        PrintClass.print(            "*******************Початок битви**********************");
        while(true) {
            sortBySpeed(chosenDroids);
            for (int i = 0; i < chosenDroids.size(); i++) {
                if (droidsOfTeam(chosenDroids, 1).isEmpty()) {
                    PrintClass.print("*****************Виграла перша команда****************");
                    restartDroids(chosenDroids);
                    return;
                } else if (droidsOfTeam(chosenDroids, 0).isEmpty()) {
                    PrintClass.print("*****************Виграла друга команда****************");
                    restartDroids(chosenDroids);
                    return;
                }
                if (chosenDroids.get(i).isDead) {
                    chosenDroids.remove(chosenDroids.get(i));
                    continue;
                }
                PrintClass.print("******************************************************");
                chosenDroids.get(i).makeMove(chosenDroids);
            }
        }
    }
    static public void restartDroids(List<Droid> lastDroids){
        for (var droid :lastDroids) {
            droid.restart();
        }
    }
    public static List<Droid> chooseDroids(List<Droid> createdDroids,int peopleInSquad) {
        var allDroids= new ArrayList<>(createdDroids);
        var chosenDroids = new ArrayList<Droid>();
        var scanner = new Scanner(System.in);
        for (int i = 0; i < peopleInSquad*2; i++) {
            PrintClass.print("Виберіть " + (i/2+1) + "-го дроїда для " + (i % 2 + 1) + "-ої команди");
            allDroids.removeAll(chosenDroids);
            showDroids(allDroids);
            int chosenDroidIndex=scanner.nextInt();
            chosenDroids.add(allDroids.get(chosenDroidIndex));
            chosenDroids.getLast().team=Team.values()[i%2];
        }
        return chosenDroids;
    }
    public static List<Droid> sortBySpeed(List<Droid> droids){
        for (int i =0;i<droids.size()-1;i++) {
            if (droids.get(i).speed<droids.get(i+1).speed) {
                var droid = droids.get(i + 1);
                for(;(i>=0)&&(droids.get(i).speed<droid.speed);i--)
                    droids.set(i+1, droids.get(i));
                droids.set(i+1,droid);
            }
        }
        return droids;
    }

    public static List<Droid> droidsOfTeam(List<Droid> allDroids, int team){
        var droidOfTeam = new ArrayList<Droid>();
        for (var droid: allDroids) {
            if(droid.team.ordinal()==team) droidOfTeam.add(droid);
        }
        return  droidOfTeam;
    }

}
