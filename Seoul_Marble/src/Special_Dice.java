public class Special_Dice extends Dice {
    int special_dice_throw() {
        random = dice_throw();
        random += random * 100;
        return random;
    }
}