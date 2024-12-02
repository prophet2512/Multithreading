package synchronization;

public class TVSet {
    private static volatile  TVSet instance = null;

    private TVSet() {
        System.out.println("TVSet instantiated");
    }

    public static TVSet getInstance() {
        if(instance==null) {
            synchronized (TVSet.class) {
                if (instance == null)
                    instance = new TVSet();
            }
        }
        return instance;
    }
}
