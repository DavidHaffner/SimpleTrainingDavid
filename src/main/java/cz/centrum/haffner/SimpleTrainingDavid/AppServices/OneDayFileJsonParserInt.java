package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;

import java.io.File;
import java.net.URL;

public interface OneDayFileJsonParserInt {
    MetricsInfoData process(URL inputUrl);
}
