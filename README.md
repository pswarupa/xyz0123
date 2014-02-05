xyz0123
=======
Created a web app with register.jsp to take in first anme, last name, email and password. Created a LocalServlet which would communicate between the jsp and webservice.

I Used the WSDL from : http://myopenissues.com/magento/api/v2_soap?wsdl=1 Got the Session Id from the port http://myopenissues.com/magento/index.php/api/v2_soap/index/ (Howver whenever I tested it on SOAP UI it would show me session expired. I went ahead anyway)

I tried to use CustomerCustomerEntityToCreate fillig in the session Id and the credentials user entered.

Created SOAP data in XML string to sent SOAP Request.

Didnt get through the response though
