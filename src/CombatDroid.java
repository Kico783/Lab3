import java.util.List;
import java.util.Scanner;

class CombatDroid extends Droid {
    public CombatDroid(String name) {
        super(name, 100, 30, 4, 90);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть ворожого дрона для нанесення йому подвійного урону:");
        var anotherTeam = Main.droidsOfTeam(droids, 1 - team.ordinal());
        Main.showDroids(anotherTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        anotherTeam.get(index).takeDamage(damage * 2);
        PrintClass.print("Був вибраний "+  anotherTeam.get(index).showInformation());

    }
}
