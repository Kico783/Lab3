import java.util.List;
import java.util.Scanner;

class MultiAttackDroid extends Droid {
    public MultiAttackDroid(String name) {
        super(name, 110, 35, 4, 82);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть ворожих дронів для нанесення їм 60 одиниць урону");
        var anotherTeam = Main.droidsOfTeam(droids, 1 - team.ordinal());
        Main.showDroids(anotherTeam);
        var scanner = new Scanner(System.in);
        String[] indexesStr = scanner.nextLine().split(" ");
        int[] indexes = new int[indexesStr.length];
        for (int i = 0; i < indexesStr.length; i++) {
            indexes[i] = Integer.parseInt(indexesStr[i]);
        }
        PrintClass.print("Були вибрані: ");
        for (var index : indexes) {
            anotherTeam.get(index).takeDamage(60 / indexes.length);
            PrintClass.print(anotherTeam.get(index).showInformation());
        }

    }
}
