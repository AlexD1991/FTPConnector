import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import com.jcraft.jsch.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by aleksey.dobrovolsky on 7/5/2019.
 */
public class Runner {

    public static void main(String args[]) throws IOException {
        JSch jsch = new JSch();
        Session session = null;

        ZipFile warFile = new ZipFile( "D:\\test.war" );
        for(Enumeration e = warFile.entries(); e.hasMoreElements(); )
        {
            ZipEntry entry = (ZipEntry) e.nextElement();
            if (entry.getName().contains("index.html")) {
                System.out.println(entry.getName());
                InputStream is = warFile.getInputStream(entry);
                Files.copy(is, Paths.get("D:\\index.html"));
                File newFile = new File("D:\\index.html");
                BufferedReader br = new BufferedReader(new FileReader(newFile));

                String st;

                while ((st = br.readLine()) != null)
                    System.out.println(st);
            }
        }

//            if( entry.getName().contains( "index.html" ) )
//            {
//                //read your xml file
//                File fXmlFile = new File( entry );
//                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//                Document doc = dBuilder.parse(fXmlFile);
//
//
//                /**Now write your xml file to another file and save it say, createXMLFile **/
//
//                //Appending the newly created xml file and
//                //deleting the old one.
//                Map<String, String> zip_properties = new HashMap<>();
//                zip_properties.put("create", "false");
//                zip_properties.put("encoding", "UTF-8");
//
//                URI uri = URI.create( "jar:" + warFile.toUri() );
//
//                try( FileSystem zipfs = FileSystems.newFileSystem(uri, zip_properties) ) {
//
//                    Path yourXMLFile = zipfs.getPath( yourXMLFile );
//                    Path tempyourXMLFile = yourXMLFile;
//                    Files.delete( propertyFilePathInWar );
//
//                    //Path where the file to be added resides
//                    Path addNewFile = Paths.get( createXMLFile );
//
//                    //Append file to war File
//                    Files.copy(addNewFile, tempyourXMLFile);
//                    zipfs.close();
//
//                }
//            }
        }



//        try {
//            session = jsch.getSession("adobrovolsky", "10.12.10.60", 22);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.setPassword("xxx");
//            session.connect();
//
//            Channel channel = session.openChannel("sftp");
//            channel.connect();
//            ChannelSftp sftpChannel = (ChannelSftp) channel;
//            sftpChannel.cd("/builds/minsk/widgets/HTMLSigningTableApp/1.0.0.10/");
//            InputStream inputStream = sftpChannel.get("signing-table-app-1.0.0.10.war");
//
//            String fileDir = "D:\\test.war";
//            Files.copy(inputStream, Paths.get(fileDir));
//            File file = new File(fileDir);
//
//
//
//
//
//            System.out.println("finished");
//
//            sftpChannel.exit();
//            session.disconnect();
//        } catch (JSchException e) {
//            e.printStackTrace();
//        } catch (SftpException e) {
//            e.printStackTrace();
//        }
    }

