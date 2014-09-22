import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Christos Sotirelis
 */
public class NetInfo {
    
    static int done = 0, total = 0; // TBD: Count stats
    
    public static void main(String[] args) throws IOException {
        final int N = 255; // Number of IPs (range: 1-255)
        
        final ArrayList<ThreadNet> TL = new ArrayList<ThreadNet>();
        
        // Initiate a thread for each IP and add it to TL list
        for (int i=1; i<N; i++) {
            TL.add(new ThreadNet("T"+i++, i));
        }
        
        /*
        new Thread() {
            
            @Override
            public void run() {
                while (done != N) {
                    for (ThreadNet TL1 : TL) {
                        if (TL1.isDone()) {
                            done++;
                            if (TL1.hasFound())
                                total++;
                        }
                    }
                }
                System.out.println("Found " + total + " out of " + N);
            }
            
        }.start();
        */
        
        // Start each thread in list
        for (ThreadNet TL1 : TL) {
            TL1.start();
        }
        
    }
    
}