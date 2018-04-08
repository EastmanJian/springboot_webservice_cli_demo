package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.oxm.Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import hello.wsdl.GetCountryRequest;
import hello.wsdl.GetCountryResponse;
import org.springframework.ws.soap.axiom.AxiomSoapMessage;
import org.springframework.ws.support.MarshallingUtils;

import javax.xml.soap.SOAPFactory;
import java.io.IOException;


public class CountryClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting country info for " + country);

        //log SOAP message
//        Marshaller marshaller = getWebServiceTemplate().getMarshaller();
//        WebServiceMessage requestMsg = null;
//        try {
//            MarshallingUtils.marshal(marshaller, request, requestMsg);
//            requestMsg.writeTo(System.out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request);

        return response;
    }
}
