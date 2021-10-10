import java.util.Scanner;
import java.util.Random;

public class Dice {
    Scanner scan = new Scanner(System.in);
    Random Random = new Random();

    int choice;
    int random;

    int dice_throw() {
        System.out.println("주사위를 던지려면 0을 입력 하세요");
        choice = scan.nextInt();
        while (choice != 0) {
            System.out.println("잘못된 값입니다");
            System.out.println("주사위를 던지려면 0을 입력 하세요");
            choice = scan.nextInt();
        }
        random = Random.nextInt(13) + 1;
        //주사위던지는소리 주사위던지는소리 = new 주사위던지는소리();
        //주사위던지는소리.start();
        return random;
    }
}


