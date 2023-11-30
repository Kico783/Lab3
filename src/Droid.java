import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract class Droid {
    protected Team team;
    protected String name;
    protected  int maxHealth;
    protected int health;
    protected int damage;
    protected int speed;

    protected int accuracy;

    protected boolean isUltimateReady;
    protected boolean isDead;
    public Droid(String name, int maxHealth, int damage, int speed, int accuracy) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
        this.speed = speed;
        this.accuracy = accuracy;
        this.isUltimateReady = true;
        this.isDead = false;
    }

    public String getName() {
        return name;
    }
    public void restart(){
        health=maxHealth;
        isUltimateReady = true;
    }
    public String showInformation(){
       return String.format("%s - %s(%d HP;%d DMG;%d SPD;%d AIM)",name,getClass().getSimpleName(),health,damage,speed,accuracy);
    }
    public boolean takeDamage(int damage) {
        health -= damage;
        if (health <= 0) isDead = true;
        return isDead;
    }

    public void makeMove(List<Droid> droids){
        if(isDead) return;
        PrintClass.print(showInformation()+" робить хід.");
        if(isUltimateReady){
            var scanner = new Scanner(System.in);
            System.out.println("0)Звичайна атака");
            System.out.println("1)Використати ульту");
            if(scanner.nextInt()==0) attack(droids);
            else {
                isUltimateReady=false;
                UniqueAbility(droids);
            }
        }
        else attack(droids);
    }
    public void attack(List<Droid> droids) {
        PrintClass.print(name+" атакує:");
        var anotherTeam = Main.droidsOfTeam(droids,1-team.ordinal());
        Main.showDroids(anotherTeam);
        var scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        Random rand = new Random();
        if (rand.nextInt(100) + 1 < accuracy){
            PrintClass.print("І наносить "+damage + " одиниці урону "+anotherTeam.get(index).showInformation());
            anotherTeam.get(index).takeDamage(damage);
        }
        else PrintClass.print("Але промахується:(");
    }

    abstract public void UniqueAbility(List<Droid> droids);

}

