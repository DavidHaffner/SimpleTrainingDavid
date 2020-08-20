package cz.centrum.haffner.SimpleTrainingDavid.SOAP;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import haffner.centrum.cz.simple_training_david.GetKpisResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetKpisResponseMapper {

    // mapping of dataObject into soapResponse object
    public GetKpisResponse process(KpisInfoData responseData){
        GetKpisResponse response = new GetKpisResponse();

        response.setProcessedFilesNumber( responseData.getProcessedFilesNumber() );
        response.setTotalCallsNumber( responseData.getTotalCallsNumber() );
        response.setTotalMessagesNumber( responseData.getTotalMessagesNumber() );
        response.setTotalRowsNumber( responseData.getTotalRowsNumber() );
        response.setDifferentOriginCodesNumber( responseData.getDifferentOriginCodesNumber() );
        response.setDifferentDestinationCodesNumber( responseData.getDifferentDestinationCodesNumber() );
        responseData.getLastTenProcessingDurations().stream()
                // list<Long> inserted as list<String> because xml doesn´t know Long data type
                .forEach( a -> { response.addJsonProcessingDurationList(String.valueOf(a)); });

        return response;
    }
}
