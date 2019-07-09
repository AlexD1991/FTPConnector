/**
 * Created by aleksey.dobrovolsky on 7/8/2019.
 */
public class WidgetProperty {

    private String widgetName;
    private String widgetVersion;

    WidgetProperty(String widgetName, String widgetVersion){
        this.widgetName = widgetName;
        this.widgetVersion = widgetVersion;
    }

    public String getWidgetName() {
        return widgetName;
    }

    public String getWidgetVersion() {
        return widgetVersion;
    }
}
