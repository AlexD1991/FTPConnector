import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aleksey.dobrovolsky on 7/8/2019.
 */
public class WarFileCopy {

    private FTPConnector ftpConnector = new FTPConnector();
    private String widgetsRelativePath = "/builds/minsk/widgets/";
    private String relativePath;
    private String widgetName;
    private String widgetVersion;
    private String widgetDirectory;
    private String widgetWarName;
    private Boolean isDev;
    private String regexp = "[a-zA-Z0-9.-]*(?=.war).war";
    private String targetFullDirectory;
    private String targetRelativeDirectory = "target/builds/";


    WarFileCopy(String widgetName, String widgetVersion, Boolean isDev) throws JSchException, SftpException, IOException {
        this.widgetName = widgetName;
        this.widgetVersion = widgetVersion;
        this.isDev = isDev;
        copyWarFile();
        ftpConnector.closeSftpChannel();
    }


    private void copyWarFile() throws JSchException, SftpException, IOException {
        retrieveWarFileNameFromFtp();
        widgetDirectory = relativePath + widgetWarName;
        InputStream inputStream = ftpConnector.getSftpChannel().get(widgetDirectory);

        targetRelativeDirectory += (isDev) ? "/" + widgetName + "/" + widgetVersion + "/" + "dev/"
                : widgetName + "/" + widgetVersion + "/";

        Files.createDirectories(Paths.get(targetRelativeDirectory));

        targetFullDirectory = targetRelativeDirectory + widgetWarName;
        Files.copy(inputStream, Paths.get(targetFullDirectory), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("finished");
    }

    private void retrieveWarFileNameFromFtp() {
        relativePath = widgetsRelativePath + "/" + widgetName + "/" + widgetVersion + "/";
        try {
            if (isDev) relativePath += "dev/";
            getWidgetWarName(ftpConnector.getSftpChannel().ls(relativePath).elements());
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    private void getWidgetWarName(Enumeration elements) {
        Object element;
        while (elements.hasMoreElements()) {
            element = elements.nextElement();
            System.out.println(element.toString());
            if (isWarFile(element)) {
                widgetWarName = getWarFileName(element);
                break;
            }
        }
        System.out.println(widgetWarName);
    }

    private Boolean isWarFile(Object element) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(element.toString());
        return matcher.find();
    }

    private String getWarFileName(Object element) {
        String result = "";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(element.toString());
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
}
