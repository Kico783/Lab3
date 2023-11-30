import java.util.List;
import java.util.Scanner;

class SniperDroid extends Droid {
    public SniperDroid(String name) {
        super(name, 95, 40, 3, 95);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть дроїда-союзника для покращення його точності на 10 одиниць");
        var thisTeam = Main.droidsOfTeam(droids, team.ordinal());
        Main.showDroids(thisTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        thisTeam.get(index).accuracy += 10;
        PrintClass.print("Був вибраний "+  thisTeam.get(index).showInformation());
    }
}
