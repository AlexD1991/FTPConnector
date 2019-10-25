import java.io.IOException;

public class WarFileSubmissionWidget extends WarFile{

    private String regexpCertificateId = "(?<=certificateId\": \")[a-zA-Z0-9]*";
    private String regexpLoginForm = "(?<=restricted_features\": ).*(login)";
    private String entryName = "public/dist/common/config/app.json";

    WarFileSubmissionWidget(String warFileDirectory) throws IOException {
        super(warFileDirectory);
        jsFileText = getString(warFile.getInputStream(getEntryByFileName(entryName)));
        certificateId = findTextByRegexp(regexpCertificateId);
        devLogin = devLoginConvert(findTextByRegexp(regexpLoginForm));
    }

    public Boolean devLoginConvert(String value){
        return !value.contains("login");
    }
}
