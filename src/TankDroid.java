import java.util.List;
import java.util.Scanner;

class TankDroid extends Droid {
    public TankDroid(String name) {
        super(name, 130, 20, 2, 80);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть дроїда-союзника для покращення його живучості(всі інші його характеристики зменшаться):");
        var thisTeam = Main.droidsOfTeam(droids, team.ordinal());
        Main.showDroids(thisTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        thisTeam.get(index).health *= 1.5;
        thisTeam.get(index).maxHealth *= 1.5;
        thisTeam.get(index).damage *= 0.8;
        thisTeam.get(index).speed -= 1;
        thisTeam.get(index).accuracy *= 0.8;
        PrintClass.print("Був вибраний "+  thisTeam.get(index).showInformation());
    }
}
