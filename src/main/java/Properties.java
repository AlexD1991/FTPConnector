import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aleksey.dobrovolsky on 7/8/2019.
 */
class Properties {

    private ArrayList<WidgetProperty> widgetsList = new ArrayList<WidgetProperty>();
    private String widgetListFileName = "widgets_list";

    Properties() throws IOException, ParseException {
        initProperties();
    }

    private void initProperties() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new InputStreamReader(Properties.class
                .getResourceAsStream(widgetListFileName)));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray widgets = (JSONArray) jsonObject.get("widgets");

            for (Object widget1 : widgets) {
                JSONObject widget = (JSONObject) widget1;
                String widgetName = (String) widget.get("name");
                String widgetVersion = (String) widget.get("version");
                WidgetProperty widgetProperty = new WidgetProperty(widgetName, widgetVersion);
                widgetsList.add(widgetProperty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WidgetProperty> getWidgetsList() {
        return widgetsList;
    }
}
