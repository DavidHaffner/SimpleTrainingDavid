package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoSet;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoSet;

// algorithm for returning of the proper KpisInfo set
public class KpisInfoService {

    public KpisInfoService() {

    }

    public KpisInfoSet proceedKpisInfoSet() {
        return new KpisInfoSet (10,9,8,7,6,5,4);
    }
}
