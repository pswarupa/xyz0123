

import Magento.CustomerCustomerEntityToCreate;
import Magento.Mage_Api_Model_Server_V2_HandlerPortType;
import Magento.Mage_Api_Model_Server_V2_HandlerPortTypeProxy;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * Servlet implementation class LocalServlet
 */
public class LocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpS response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        try {
            Magento.MagentoServiceLocator serviceLocator = new Magento.MagentoServiceLocator();

            Mage_Api_Model_Server_V2_HandlerPortType port = serviceLocator.getMage_Api_Model_Server_V2_HandlerPort();
            String address = serviceLocator.getMage_Api_Model_Server_V2_HandlerPortAddress();
            Mage_Api_Model_Server_V2_HandlerPortTypeProxy proxy = new Mage_Api_Model_Server_V2_HandlerPortTypeProxy();
            String sessionid = port.startSession();
            CustomerCustomerEntityToCreate newCustomer = new CustomerCustomerEntityToCreate();
            newCustomer.setFirstname(fname);
            newCustomer.setLastname(lname);
            newCustomer.setEmail(email);
            newCustomer.setPassword(pass);

            URL url = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String data = "<soapenv:Envelope xmlns:xsi=" + "http://www.w3.org/2001/XMLSchema-instance" + " xmlns:xsd=" + "http://www.w3.org/2001/XMLSchema" + " xmlns:soapenv="
                    + "http://schemas.xmlsoap.org/soap/envelope/" + " xmlns:urn=" + "urn:Magento" + ">" + "<soapenv:Header/>" + "<soapenv:Body>"
                    + "<urn:customerCustomerCreate soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" + "<sessionId xsi:type=\"xsd:string\">" + sessionid + "</sessionId>"
                    + "<customerData xsi:type=\"urn:customerCustomerEntityToCreate\">" + "<email xsi:type=\"xsd:string\">" + email + "</email>" + "<firstname xsi:type=\"xsd:string\">" + fname
                    + "</firstname>" + "<lastname xsi:type=\"xsd:string\">" + lname + "</lastname>" + "<password xsi:type=\"xsd:string\">" + pass + "</password>" + "</customerData>"
                    + "</urn:customerCustomerCreate>" + "</soapenv:Body>" + "</soapenv:Envelope>";

            SOAPMessage soapMessage = getSoapMessageFromString(data);

            // connection.setRequestProperty("Content-Length", String.valueOf(soapMessage));
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("Connection", "Close");
            connection.setRequestProperty("SoapAction", "");
            connection.setDoOutput(true);

            PrintWriter pw = new PrintWriter(connection.getOutputStream());
            pw.write(soapMessage.toString());

            pw.flush();

            SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
            SOAPConnection connectionres = sfc.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage sm = mf.createMessage();
            QName bodyName = new QName("http://myopenissues.com/magento/index.php", "GetQuote", "d");
            URL endpoint = new URL("http://myopenissues.com/magento/index.php");
            SOAPMessage soapresponse = connectionres.call(sm, endpoint);
            printSOAPResponse(soapresponse);

        }
        catch (Exception ex) {
            // TODO handle custom exceptions here
        }

	}

    private SOAPMessage getSoapMessageFromString(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
        return message;
    }

    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        javax.xml.transform.Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}
