package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoSet;

// algorithm for returning of the proper MetricsInfo set
public class MetricsInfoService {

    public MetricsInfoService() {

    }

    public MetricsInfoSet proceedMetricsInfoSet() {
        return new MetricsInfoSet (1,2,3,4,5,6,7);
    }
}
