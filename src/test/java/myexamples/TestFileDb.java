package myexamples;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author olupas
 * @since 08.11.2013
 */
public class TestFileDb {
    public static void main(String[] args) {
        // configure and open database using builder pattern.
        // all options are available with code auto-completion.
        DB db = DBMaker.newFileDB(new File("testdb"))
                .closeOnJvmShutdown()
                .deleteFilesAfterClose()
                .encryptionEnable("password")

                .make();

        // open existing an collection (or create new)
        ConcurrentNavigableMap<Integer, String> map = db.getTreeMap("collectionName");
        map.put(1, "one");
        map.put(2, "two");
        System.out.println(map);

        db.commit();  //persist changes into disk

        map.put(3, "three");
        System.out.println(map);
        db.rollback(); //revert recent changes
        System.out.println(map);

        db.close();


    }
}
