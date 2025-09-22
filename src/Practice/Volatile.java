package Practice;

class SharedResource {
    private boolean flag = false;

    public void setFlag(boolean flag){
        synchronized (this) {
            this.flag = flag;
        }
    }

    public  boolean getFlag(){
        synchronized (this) {
            return this.flag;
        }
    }
}

public class Volatile {

    public static void main(String[] args) throws Exception{
        SharedResource sharedResource = new SharedResource();

        new Thread(() ->{
            System.out.println("Thread1 started!");
            try {
                System.out.println("Thread1 logic started!");
                Thread.sleep(1000);
                System.out.println("Thread1 logic completed!");
                sharedResource.setFlag(true);
                System.out.println("Flag set by thread1");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            System.out.println("Thread2 started!");
            while(!sharedResource.getFlag()){
                //Do something
            };
            System.out.println("Thread2 logic completed!");
        }).start();

    }

}
