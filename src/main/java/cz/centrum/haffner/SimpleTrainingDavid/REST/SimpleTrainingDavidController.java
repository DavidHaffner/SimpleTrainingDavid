package cz.centrum.haffner.SimpleTrainingDavid.REST;

import cz.centrum.haffner.SimpleTrainingDavid.AppServices.KpisInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.AppServices.MetricsInfoService;
import cz.centrum.haffner.SimpleTrainingDavid.DataTemplates.KpisInfoSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTrainingDavidController {

        @GetMapping("/{requestedDate}/metrics")   // supposed date format: YYYYMMDD
        public MetricsInfoService getMetrics(@PathVariable long requestedDate) {
            MetricsInfoService metricsInfoService = new MetricsInfoService();
            metricsInfoService.proceedMetricsInfoSet(requestedDate);
            return metricsInfoService;
        }

        @GetMapping("/kpis")
        public KpisInfoSet getKpis() {
            KpisInfoService kpisInfoService = new KpisInfoService();
            return kpisInfoService.proceedKpisInfoSet();
        }
}
