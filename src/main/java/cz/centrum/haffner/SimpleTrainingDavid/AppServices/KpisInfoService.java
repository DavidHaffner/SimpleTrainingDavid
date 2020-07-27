package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// algorithm for returning of the proper KpisInfo data
@Service
public class KpisInfoService {

    public KpisInfoData processKpisInfoData() {
        return new KpisInfoData(10,9,8,7,6,5,4);
    }
}
