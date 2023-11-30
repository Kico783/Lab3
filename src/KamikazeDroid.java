import java.util.List;
import java.util.Scanner;

class KamikazeDroid extends Droid {
    public KamikazeDroid(String name) {
        super(name, 70, 50, 7, 70);
    }

    @Override
    public void UniqueAbility(List<Droid> droids) {
        PrintClass.print("Виберіть ворожого дроїда ,заради знищення котрого дрон-камікадзе пожертвує своїм життям(дрон нанесе 1.3x свого життя");
        var anotherTeam = Main.droidsOfTeam(droids, 1 - team.ordinal());
        Main.showDroids(anotherTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        anotherTeam.get(index).takeDamage((int) (health * 1.3));
        takeDamage(health);
        PrintClass.print("Був вибраний "+  anotherTeam.get(index).showInformation());
    }
}
