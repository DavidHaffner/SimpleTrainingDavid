package cz.centrum.haffner.SimpleTrainingDavid.SOAP;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import haffner.centrum.cz.simple_training_david.GetMetricsRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SimpleTrainingDavidEndpoint {
    private static final String NAMESPACE_URI = "http://cz.centrum.haffner/simple-training-david";
    private static final Logger logger = LogManager.getLogger(SimpleTrainingDavidEndpoint.class);

    @Autowired
    KpisInfoService kpisInfoService;
    @Autowired
    MetricsInfoService metricsInfoService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMetricsRequest")
    @ResponsePayload
    public MetricsInfoData getMetrics(@RequestPayload GetMetricsRequest request) {
        logger.debug("Receiving metrics SOAP request.");

        MetricsInfoData soapResponse = new MetricsInfoData();
        try {
            soapResponse = metricsInfoService.processData( Long.parseLong(request.getDate()) );
            logger.debug("Returning metrics SOAP response.");
        } catch (Exception e) {
            logger.debug("Returning metrics SOAP response with error.");
            logger.error(e.getMessage(), e);
        }

        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getKpisRequest")
    @ResponsePayload
    public KpisInfoData getKpis() {
        logger.debug("Receiving kpis SOAP request.");

        KpisInfoData soapResponse = kpisInfoService.processData();

        logger.debug("Returning kpis SOAP response.");

        return soapResponse;
    }
}
