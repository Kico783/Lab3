import java.util.List;
import java.util.Scanner;

class HealingDroid extends Droid {
    public HealingDroid(String name) {
        super(name, 80, 20, 5, 85);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть дрона-союзника для збільшення його здоров'я на 20 одиниць:");
        var thisTeam = Main.droidsOfTeam(droids, team.ordinal());
        Main.showDroids(thisTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        thisTeam.get(index).health = Math.min(thisTeam.get(index).health + 20, thisTeam.get(index).maxHealth);
        PrintClass.print("Був вибраний "+  thisTeam.get(index).showInformation());

    }
}
