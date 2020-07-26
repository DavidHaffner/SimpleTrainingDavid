package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoDataSet;

// algorithm for returning of the proper KpisInfo set
public class KpisInfoService {

    public KpisInfoService() {

    }

    public KpisInfoDataSet proceedKpisInfoSet() {
        return new KpisInfoDataSet(10,9,8,7,6,5,4);
    }
}
