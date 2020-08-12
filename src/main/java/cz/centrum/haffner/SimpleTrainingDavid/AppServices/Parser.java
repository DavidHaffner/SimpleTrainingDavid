package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Parser {
    MetricsInfoData parse(URL url) throws IOException;
}
