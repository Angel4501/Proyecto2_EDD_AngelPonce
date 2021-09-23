package proyecto2_edd_angelponce;

public class Time extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(120000);
        } catch (Exception e) {
        }
    }
}
