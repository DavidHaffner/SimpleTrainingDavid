package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// algorithm for returning of the proper Kpis info data
@Service
public class KpisInfoService {

    @Autowired
    private KpisInfoData kpisInfoData;

    public KpisInfoData processKpisInfoData() {
        return kpisInfoData;
    }
}
