import io.appium.java_client.windows.WindowsDriver;

import org.xml.sax.InputSource;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CheckAccessability {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse( new InputSource( new StringReader(driver.getPageSource()) ) );
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(new StringWriter()));
        FileOutputStream outStream = new FileOutputStream(new File("Page.xml"));
        transformer.transform(new DOMSource(doc), new StreamResult(outStream));

    }
}
