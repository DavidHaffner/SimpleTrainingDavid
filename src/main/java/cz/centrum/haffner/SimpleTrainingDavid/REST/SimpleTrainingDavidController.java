package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoDataSet;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTrainingDavidController {

    @Autowired
    private MetricsInfoService metricsInfoService;

    /*
    @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
    public MetricsInfoService getMetrics(@PathVariable long requestedDate) {
        metricsInfoService.proceedMetricsInfoSet(requestedDate);
        return metricsInfoService;
    } */

    @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
    public @ResponseBody ResponseEntity<MetricsInfoDataSet> getMetrics(@PathVariable long requestedDate) {
        MetricsInfoDataSet response = metricsInfoService.proceedMetricsInfoDataSet(requestedDate);
        return new ResponseEntity<MetricsInfoDataSet>(response, HttpStatus.OK);
    }


    @GetMapping("/kpis")
    public KpisInfoDataSet getKpis() {
        KpisInfoService kpisInfoService = new KpisInfoService();
        return kpisInfoService.proceedKpisInfoSet();
    }
}
