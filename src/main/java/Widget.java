/**
 * Created by aleksey.dobrovolsky on 7/7/2019.
 */
public class Widget {

    private String widgetName;
    private String widgetVersion;
    private String widgetDirectoryProd;
    private String widgetDirectoryDev;

    public String getWidgetName() {
        return widgetName;
    }

    public Widget setWidgetName(String widgetName) {
        this.widgetName = widgetName;
        return this;
    }

    public String getWidgetVersion() {
        return widgetVersion;
    }

    public Widget setWidgetVersion(String widgetVersion) {
        this.widgetVersion = widgetVersion;
        return this;
    }

    public String getWidgetDirectoryProd() {
        return widgetDirectoryProd;
    }

    public Widget setWidgetDirectoryProd(String widgetDirectoryProd) {
        this.widgetDirectoryProd = widgetDirectoryProd;
        return this;
    }

    public String getWidgetDirectoryDev() {
        return widgetDirectoryDev;
    }

    public Widget setWidgetDirectoryDev(String widgetDirectoryDev) {
        this.widgetDirectoryDev = widgetDirectoryDev;
        return this;
    }
}
