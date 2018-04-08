package hello;

import hello.wsdl.GetCountryRequest;
import hello.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.context.DefaultMessageContext;
import org.springframework.ws.support.MarshallingUtils;

import java.io.IOException;

//extend the WebServiceGatewaySupport class to create a web service client
public class CountryClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    //GetCountryResponse and GetCountryResponse are derived from the WSDL and were generated in the JAXB generation process
    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting country info for " + country);

        //log SOAP message
        System.out.println("-----SOAP Envelope-----");
        Marshaller marshaller = getWebServiceTemplate().getMarshaller();
        DefaultMessageContext ex = new DefaultMessageContext(this.getMessageFactory());
        WebServiceMessage requestMsg = ex.getRequest();
        try {
            MarshallingUtils.marshal(marshaller, request, requestMsg);
            requestMsg.writeTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        //send SOAP  web service request and receive response
        //uses the WebServiceTemplate supplied by the WebServiceGatewaySupport base class to do the actual SOAP exchange
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request);

        return response;
    }
}
