import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class 배경음 extends Thread {
    public static boolean stop;
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    String strFilename;

    public 배경음() {
        this.strFilename = "D:\\teamnova\\5주 - java 스레드, 해시 테이블\\게임1_Seoul_Marble\\소리\\배경음.wav";
    }
    public static void setStop(boolean newvalue){
        stop = newvalue;
    }

    public void run() {
        while(!stop){
            try {
                soundFile = new File(strFilename);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try {
                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            sourceLine.start();
            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1 && !stop) {
                try {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0) {
                    @SuppressWarnings("unused")
                    int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                }
            }
            sourceLine.drain();
            sourceLine.close();
        }

    }
}

