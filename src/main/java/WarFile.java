import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by aleksey.dobrovolsky on 7/7/2019.
 */
class WarFile {

    private String regexpCertificateId = "(?<=certificateId:\")[a-zA-Z0-9]*(?=\",)";
    private String regexpLoginForm = "(?<=dev_login:!)[01]";
    private String entryName = "main";
    ZipFile warFile;
    String jsFileText;
    String certificateId;
    Boolean devLogin;


    WarFile(String warFileDirectory) throws IOException {
        warFile = new ZipFile(warFileDirectory);
        jsFileText = getString(warFile.getInputStream(getEntryByFileName(entryName)));
        certificateId = findTextByRegexp(regexpCertificateId);
        devLogin = devLoginConvert(findTextByRegexp(regexpLoginForm));
    }


    String getCertificateId() {
        return certificateId;
    }

    Boolean getDevLogin() {
        return devLogin;
    }

    String findTextByRegexp(String regexp) {
        String result = "";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(jsFileText);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    ZipEntry getEntryByFileName(String entryName) {
        ZipEntry entry = null;
        for (Enumeration e = warFile.entries(); e.hasMoreElements(); ) {
            entry = (ZipEntry) e.nextElement();
            if (entry.getName().contains(entryName)) {
                break;
            }
        }
        return entry;
    }

    static String getString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public Boolean devLoginConvert(String value){
        return value.equals("0") || value.equals("true");
    }

}
