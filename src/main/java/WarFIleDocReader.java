import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * Created by aleksey.dobrovolsky on 7/7/2019.
 */
public class WarFIleDocReader extends WarFile{

    private String regexpCertificateId = "(?<=certificateId\": \")[a-zA-Z0-9]*(?=\",)";
    private String regexpLoginForm = "(?<=restricted_features\": ).*(login)";
    private String entryName = "javascripts/src/config/app.json";

    WarFIleDocReader(String warFileDirectory) throws IOException {
        super(warFileDirectory);
        jsFileText = getString(warFile.getInputStream(getEntryByFileName(entryName)));
        certificateId = findTextByRegexp(regexpCertificateId);
        devLogin = devLoginConvert(findTextByRegexp(regexpLoginForm));
    }

    public Boolean devLoginConvert(String value){
        return !value.contains("login");
    }
}
