package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoSet;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.MetricsInfoSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTrainingDavidController {

        /*
        @GetMapping("/{requestedDate}")            // supposed date format: YYYYMMDD
        public String getParticularDate(@PathVariable long requestedDate) {
            return "Here is your date: " + String.valueOf(requestedDate);
        }
        */

        @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
        public MetricsInfoSet getMetrics(@PathVariable long requestedDate) {
            MetricsInfoService metricsInfoService = new MetricsInfoService();
            return metricsInfoService.proceedMetricsInfoSet();
        }

        @GetMapping("/kpis")
        public KpisInfoSet getKpis() {
            KpisInfoService kpisInfoService = new KpisInfoService();
            return kpisInfoService.proceedKpisInfoSet();
        }
}
