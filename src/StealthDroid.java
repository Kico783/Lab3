import java.util.List;
import java.util.Scanner;

class StealthDroid extends Droid {
    public StealthDroid(String name) {
        super(name, 90, 25, 6, 88);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть дрона-союзника для збільшення його скорості на 3 одиниці:");
        var thisTeam = Main.droidsOfTeam(droids, team.ordinal());
        Main.showDroids(thisTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        thisTeam.get(index).speed += 3;
        PrintClass.print("Був вибраний "+  thisTeam.get(index).showInformation());
    }
}
