
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

public class Test {

    public static void main(String[] args) {
        String server = "ftp";
        int port = 21;
        String user = "asadwan";
        String pass = "12345";

        FTPClient ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

        try {
            ftpClient.connect("localhost", port);
            //ftpClient.enterLocalPassiveMode();
            showServerReply(ftpClient);

            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }

            boolean success = ftpClient.login(user, pass);
            showServerReply(ftpClient);

            if (!success) {
                System.out.println("Could not login to the server");
            } else {
                System.out.println("LOGGED IN SERVER");
            }

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("/home/aadwan/apps64/glassfish5/glassfish/domains/domain1/logs/server.log"));
            ftpClient.storeFile("file.txt", bis);

            ftpClient.makeDirectory("/content");

            ftpClient.disconnect();
            System.out.println("DISCONNECTED");
        } catch (IOException ex) {
            System.err.println("Oops! Something wrong happened");
            ex.printStackTrace();
        }
    }

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
