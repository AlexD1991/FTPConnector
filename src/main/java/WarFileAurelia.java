import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * Created by aleksey.dobrovolsky on 7/7/2019.
 */
class WarFileAurelia extends WarFile{

    private String regexpCertificateId = "(?<=certificateId\": \")[a-zA-Z0-9]*(?=\",)";
    private String regexpLoginForm = "(?<=restricted_features\": ).*(login)";
    private String entryName = "dist/common/config/app.json";

    WarFileAurelia(String warFileDirectory) throws IOException {
        super(warFileDirectory);
        jsFileText = getString(warFile.getInputStream(getEntryByFileName(entryName)));
        certificateId = findTextByRegexp(regexpCertificateId);
        devLogin = devLoginConvert(findTextByRegexp(regexpLoginForm));
    }
}
