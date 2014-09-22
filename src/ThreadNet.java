
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christos Sotirelis
 */
public class ThreadNet implements Runnable {
    
    private Thread t;
    private final String name;
    private final int pos;
    private boolean found, done;
    
    public boolean hasFound() { return this.found; }
    public boolean isDone() { return this.done; }
    
    public ThreadNet(String name, int pos) {
        this.name = name;
        this.pos = pos;
        this.found = false;
        this.done = false;
    }
    
    public void start() {
        if (this.t == null) {
            this.t = new Thread(this, this.name);
            t.start();
        }
    }
    
    @Override
    public void run() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            byte[] ip = localhost.getAddress();
            try {
                ip[3] = (byte) this.pos;
                InetAddress address = InetAddress.getByAddress(ip);
                if (address.isReachable(500)) {
                    System.out.println("[" + this.name + "]: " + address + " is on (pingable).");
                    this.found = true;
                }
                else if (!address.getHostAddress().equals(address.getHostName())) {
                    System.out.println("[" + this.name + "]: " + address + " is known in DNS.");
                    this.found = true;
                }
                this.done = true;
                // System.out.println("[" + this.name + "]: Exiting.");
            } catch (IOException ex) {
                Logger.getLogger(ThreadNet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ThreadNet.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
