import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import static javax.swing.UIManager.getString;

/**
 * Created by aleksey.dobrovolsky on 7/5/2019.
 */
public class Runner {

    public static void main(String args[]) throws Exception {

        Directories directories = new Directories();

        for (Widget widget : directories.getDirectories()) {
            if (widget.getWidgetName().equals("DR")) {
                WarFIleDocReader warFileProd = new WarFIleDocReader(widget.getWidgetDirectoryProd());
                WarFIleDocReader warFileDev = new WarFIleDocReader(widget.getWidgetDirectoryDev());
                System.out.println(widget.getWidgetName());
                System.out.println("dev - " + warFileDev.getDevLogin() + " " + warFileDev.getCertificateId());
                System.out.println("prod - " + warFileProd.getDevLogin() + " " + warFileProd.getCertificateId());
                System.out.println();
            } else if (widget.getWidgetName().equals("DocAudit")){
                WarFileAurelia warFileProd = new WarFileAurelia(widget.getWidgetDirectoryProd());
                WarFileAurelia warFileDev = new WarFileAurelia(widget.getWidgetDirectoryDev());
                System.out.println(widget.getWidgetName());
                System.out.println("dev - " + warFileDev.getDevLogin() + " " + warFileDev.getCertificateId());
                System.out.println("prod - " + warFileProd.getDevLogin() + " " + warFileProd.getCertificateId());
                System.out.println();
            } else {
                WarFile warFileProd = new WarFile(widget.getWidgetDirectoryProd());
                WarFile warFileDev = new WarFile(widget.getWidgetDirectoryDev());
                System.out.println(widget.getWidgetName());
                System.out.println("dev - " + warFileDev.getDevLogin() + " " + warFileDev.getCertificateId());
                System.out.println("prod - " + warFileProd.getDevLogin() + " " + warFileProd.getCertificateId());
                System.out.println();
            }

        }

//        WarFile warFileProd = new WarFile(warFileProdDirectory, entryName);
//        WarFile warFileDev = new WarFile(warFileDevDirectory, entryName);
//        System.out.println("dev - " + warFileDev.getDevLogin() + " "+warFileDev.getCertificateId());
//        System.out.println("prod - " + warFileProd.getDevLogin() + " "+warFileProd.getCertificateId());
    }

//    public void copyWarFile() {
//        JSch jsch = new JSch();
//        Session session = null;
//        try {
//            session = jsch.getSession("adobrovolsky", "10.12.10.60", 22);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.setPassword("xxx");
//            session.connect();
//
//            Channel channel = session.openChannel("sftp");
//            channel.connect();
//            ChannelSftp sftpChannel = (ChannelSftp) channel;
//            sftpChannel.cd("/builds/minsk/widgets/HTMLSigningTableApp/1.0.0.10/");
//            InputStream inputStream = sftpChannel.get("signing-table-app-1.0.0.10.war");
//
//            String fileDir = "D:\\test.war";
//            Files.copy(inputStream, Paths.get(fileDir));
//            File file = new File(fileDir);
//
//            System.out.println("finished");
//
//            sftpChannel.exit();
//            session.disconnect();
//        } catch (JSchException e) {
//            e.printStackTrace();
//        } catch (SftpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}









