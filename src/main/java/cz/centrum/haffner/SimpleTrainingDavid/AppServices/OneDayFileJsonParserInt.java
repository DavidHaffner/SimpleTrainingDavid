package cz.centrum.haffner.SimpleTrainingDavid.AppServices;

import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;

import java.io.File;

public interface OneDayFileJsonParserInt {
    MetricsInfoData process(File inputFile);
}
