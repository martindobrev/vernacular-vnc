package msopentech.thali.toronionproxy;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class OnionProxyThread extends Thread {
  public void run() {
    String fileStorageLocation = "torfiles";
    OnionProxyManager onionProxyManager = null;
    try {
      onionProxyManager = new JavaOnionProxyManager(
          new JavaOnionProxyContext(
              Files.createTempDirectory(fileStorageLocation).toFile()));
    } catch (IOException ex) {
      //Logger.getLogger(disabled_gui.class.getName()).log(Level.SEVERE, null, ex);
    }
    int totalSecondsPerTorStartup = 10;
    int totalTriesPerTorStartup = 2;


    // Start the Tor Onion Proxy
    try {
      onionProxyManager.startWithRepeat(totalSecondsPerTorStartup, totalTriesPerTorStartup);
    } catch (InterruptedException e) {
      log.error("INTERRUPTED EXCEPTION {}", e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      log.error("IOException {}", e.getMessage());
      e.printStackTrace();
    }
  }

}
