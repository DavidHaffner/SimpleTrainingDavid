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

        // private static final String TEMPLATE = "Hello, %s!";


        /* David: just for first testing of REST
        @GetMapping("/greeting")
        public String responseGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return String.format(TEMPLATE, name);
        }
        */

        @GetMapping("/{requestedDate}")            // supposed date format: YYYYMMDD
        public String getParticularDate(@PathVariable long requestedDate) {
            return "Here is your date: " + String.valueOf(requestedDate);
        }

        @GetMapping("/metrics")
        public MetricsInfoSet getMetrics() {
            MetricsInfoService metricsInfoService = new MetricsInfoService();
            return metricsInfoService.proceedMetricsInfoSet();
        }

        @GetMapping("/kpis")
        public KpisInfoSet getKpis() {
            KpisInfoService kpisInfoService = new KpisInfoService();
            return kpisInfoService.proceedKpisInfoSet();
        }
}
