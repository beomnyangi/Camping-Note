public class 주사위던지는소리 extends Thread{
    Play play = new Play("D:\\teamnova\\5주 - java 스레드, 해시 테이블\\게임1_Seoul_Marble\\소리\\주사위던지는소리.wav");
    public void run(){
        play.play();
    }
}
