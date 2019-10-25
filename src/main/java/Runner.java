import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.UIManager.getString;

/**
 * Created by aleksey.dobrovolsky on 7/5/2019.
 */
public class Runner {



    public static void main(String args[]) throws Exception {

        String[] aureliaWidgets = {"HTMLDocumentExplorer", "HTMLThreadApp", "HTMLDocumentSubmissionWidget", "HTMLConditionsWidget"};

        Properties properties = new Properties();
        ArrayList<WidgetProperty> widgets = properties.getWidgetsList();


        for (WidgetProperty widget1 : widgets) {
            new WarFileCopy(widget1.getWidgetName(), widget1.getWidgetVersion(), true);
            new WarFileCopy(widget1.getWidgetName(), widget1.getWidgetVersion(), false);
        }

        Directories directories = new Directories();

        for (Widget widget : directories.getDirectories()) {
            if (widget.getWidgetName().equals("HTMLDocumentReader")) {
                WarFIleDocReader warFileProd = new WarFIleDocReader(widget.getWidgetDirectoryProd());
                WarFIleDocReader warFileDev = new WarFIleDocReader(widget.getWidgetDirectoryDev());
                System.out.println(widget.getWidgetName());
                System.out.println("dev - " + warFileDev.getDevLogin() + " " + warFileDev.getCertificateId());
                System.out.println("prod - " + warFileProd.getDevLogin() + " " + warFileProd.getCertificateId());
                System.out.println();
            } else if (widget.getWidgetName().equals("HTMLDocumentSubmissionWidget")){
                WarFileSubmissionWidget warFileProd = new WarFileSubmissionWidget(widget.getWidgetDirectoryProd());
                WarFileSubmissionWidget warFileDev = new WarFileSubmissionWidget(widget.getWidgetDirectoryDev());
                System.out.println(widget.getWidgetName());
                System.out.println("dev - " + warFileDev.getDevLogin() + " " + warFileDev.getCertificateId());
                System.out.println("prod - " + warFileProd.getDevLogin() + " " + warFileProd.getCertificateId());
                System.out.println();
            }   else if (Arrays.toString(aureliaWidgets).contains(widget.getWidgetName())){
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
    }
}









