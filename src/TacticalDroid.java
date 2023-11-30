import java.util.List;
import java.util.Scanner;

class TacticalDroid extends Droid {
    public TacticalDroid(String name) {
        super(name, 120, 32, 5, 90);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть ворожого дроїда для забрання в нього 10% урону");
        var anotherTeam = Main.droidsOfTeam(droids, 1 - team.ordinal());
        Main.showDroids(anotherTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        damage += (int) (anotherTeam.get(index).damage * 0.1);
        anotherTeam.get(index).damage *= 0.9;
        PrintClass.print("Був вибраний "+  anotherTeam.get(index).showInformation());

    }
}
