import com.jcraft.jsch.*;

import java.util.Enumeration;

/**
 * Created by aleksey.dobrovolsky on 7/8/2019.
 */
public class Test {


    public static void main(String args[]) {
        JSch jsch = new JSch();
        try {

            Session session = jsch.getSession("adobrovolsky", "10.12.10.60", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("@l3kd0123!");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            Enumeration elements = sftpChannel.ls("/builds/minsk").elements();
            while (elements.hasMoreElements()){
                System.out.println(elements.nextElement().toString());
            }

            System.out.println();

            sftpChannel.exit();
            session.disconnect();
            System.out.println("Closed");
        } catch (JSchException e) {
            e.printStackTrace();

        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}
