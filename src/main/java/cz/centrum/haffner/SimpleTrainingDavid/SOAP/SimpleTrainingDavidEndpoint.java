package cz.centrum.haffner.SimpleTrainingDavid.SOAP;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.Kafka.KafkaSimpleProducer;
import haffner.centrum.cz.simple_training_david.GetKpisResponse;
import haffner.centrum.cz.simple_training_david.GetMetricsRequest;
import haffner.centrum.cz.simple_training_david.GetMetricsResponse;
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
    @Autowired
    KafkaSimpleProducer kafkaSimpleProducer;
    @Autowired
    GetMetricsResponseMapper getMetricsResponseMapper;
    @Autowired
    GetKpisResponseMapper getKpisResponseMapper;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMetricsRequest")
    @ResponsePayload
    public GetMetricsResponse getMetrics(@RequestPayload GetMetricsRequest request) {
        logger.debug("Receiving metrics SOAP request.");

        GetMetricsResponse soapResponse = new GetMetricsResponse();
        try {
            MetricsInfoData responseData = metricsInfoService.processData( Long.parseLong( request.getDate() ));
            soapResponse = getMetricsResponseMapper.process(responseData);

            logger.debug("Returning metrics SOAP response.");
        } catch (Exception e) {
            logger.debug("Returning metrics SOAP response with error.");
            logger.error(e.getMessage(), e);
        }

        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getKpisRequest")
    @ResponsePayload
    public GetKpisResponse getKpis() {
        logger.debug("Receiving kpis SOAP request.");

        KpisInfoData responseData = kpisInfoService.processData();
        GetKpisResponse soapResponse = getKpisResponseMapper.process(responseData);

        logger.debug("Returning kpis SOAP response.");

        return soapResponse;
    }
}
