public class 지연 {
    int delay;
    public 지연(int delay){
        this.delay = delay;
    }
    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("딜레이 에러");
        }
    }
}
