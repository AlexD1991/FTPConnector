import com.jcraft.jsch.*;


import static javax.swing.UIManager.getString;

/**
 * Created by aleksey.dobrovolsky on 7/8/2019.
 */
public class FTPConnector {

    private JSch jsch = new JSch();
    private Session session = null;
    private ChannelSftp sftpChannel = null;

    FTPConnector() throws JSchException {
        prepareSftpChannel();
    }

    ChannelSftp getSftpChannel() {
        return sftpChannel;
    }

    private void prepareSftpChannel() {

        try {
            session = jsch.getSession("adobrovolsky", "10.12.10.60", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("@l3kd0123!");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();

        }
    }

    void closeSftpChannel() {
        sftpChannel.exit();
        session.disconnect();
    }


}
