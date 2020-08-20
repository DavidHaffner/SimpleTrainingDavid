package cz.centrum.haffner.SimpleTrainingDavid.SOAP;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
import haffner.centrum.cz.simple_training_david.GetMetricsResponse;
import org.springframework.stereotype.Component;

@Component
public class GetMetricsResponseMapper {

    // mapping of dataObject into soapResponse object
    public GetMetricsResponse process(MetricsInfoData responseData){
        GetMetricsResponse response = new GetMetricsResponse();

        response.setMissingFieldsRowsNumber( responseData.getMissingFieldsRowsCounter() );
        response.setBlankContentMessagesNumber( responseData.getBlankContentMessagesCounter() );
        response.setFieldsErrorsRowsNumber( responseData.getFieldsErrorsRowsCounter() );

        /* TODO: map these attributes to xml
        response.setGroupedCallsOriginNumber();
        response.setGroupedCallsDestinationNumber();
        response.setKoToOkRatio();
        response.setGroupedAverageCallDuration();
        response.setGivenWordsRanking();

         */

        return response;
    }
}
