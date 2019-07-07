import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aleksey.dobrovolsky on 7/7/2019.
 */
public class Directories {

    private ArrayList<Widget> directories = new ArrayList<Widget>();

    Directories() throws IOException {
        initDirectories();
    }

    public ArrayList<Widget> getDirectories() {
        return directories;
    }


    private void initDirectories() throws IOException {

        File targetBuildsDirectory = new File("target/builds/");
        File[] widgetDirectories = targetBuildsDirectory.listFiles();

        assert widgetDirectories != null;
        if (widgetDirectories.length != 0) {
            for (File widgetDirectory : widgetDirectories) {
                Widget widget = new Widget();
                widget.setWidgetName(widgetDirectory.getName());
                File widgetVersionDirectory = widgetDirectory.listFiles()[0];
                widget.setWidgetVersion(widgetVersionDirectory.getName());
                File[] widgetDevProdList = widgetVersionDirectory.listFiles();
                assert widgetDevProdList != null;
                for (File widgetDevProd : widgetDevProdList) {
                    if (widgetDevProd.isDirectory()) {
                        widget.setWidgetDirectoryDev(widgetDevProd.listFiles()[0].getPath());
                    } else {
                        widget.setWidgetDirectoryProd(widgetDevProd.getPath());
                    }
                }
                directories.add(widget);
            }
        }
    }
}
