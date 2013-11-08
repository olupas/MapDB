package myexamples;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.LongHashMap;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author olupas
 * @since 08.11.2013
 */
public class TestCache {

    public static final long MILLION = (long)Math.pow(10,6);
    public static final long KB = 1024; // 1KB
    public static final long MB = 1024 * KB; // 1MB
    public static final long GB = 1024 * KB; // 1MB
    public static void main(String[] args) throws InterruptedException {

       System.out.println("MaxMemory: " + Runtime.getRuntime().maxMemory()/ MB + "MB / TotalMemory :" + Runtime.getRuntime().totalMemory()/MB + "MB");
        TimeUnit.SECONDS.sleep(5);
       fillCache(new TreeMap());
       //fillCache(new LongHashMap()); //from MapDB
       // fillDirectMemory();
    }

    public static final void fillCache(Map m) {

        for (long counter = 0; ; counter++) {
            m.put(counter, "WEB");
            if (counter % MILLION == 0 && counter != 0) {
                System.out.println(counter);
            }
        }
    }

    public static final void fillCache(LongHashMap m) {

        for (long counter = 0; ; counter++) {
            m.put(counter, "WEB");

            if (counter % MILLION == 0 && counter != 0) {
                System.out.println(counter);
            }
        }
    }

    public static final void fillDirectMemory() {
        DB db = DBMaker.newDirectMemoryDB().transactionDisable().closeOnJvmShutdown().make();
        Map m = db.getTreeMap("test");
        fillCache(m);
    }





}
