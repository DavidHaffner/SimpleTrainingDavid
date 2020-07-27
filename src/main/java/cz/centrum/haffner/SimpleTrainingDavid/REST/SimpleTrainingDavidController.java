package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoData;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoData;
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
    @Autowired
    private KpisInfoService kpisInfoService;


    @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
    public @ResponseBody ResponseEntity<MetricsInfoData> getMetrics(@PathVariable long requestedDate) {
        MetricsInfoData response = metricsInfoService.processMetricsInfoData(requestedDate);
        return new ResponseEntity<MetricsInfoData>(response, HttpStatus.OK);
    }


    @GetMapping("/kpis")
    public @ResponseBody ResponseEntity<KpisInfoData> getKpis() {
        KpisInfoData response = kpisInfoService.processKpisInfoData();
        return new ResponseEntity<KpisInfoData>(response, HttpStatus.OK);
    }
}
