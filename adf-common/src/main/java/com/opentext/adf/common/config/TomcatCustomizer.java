package com.opentext.adf.common.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Custom configuration for the embedded tomcat, as properties other than spring boot.
 */
@Configuration
class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
  private final Logger logger;
  @Value("${adf.common.tomcat.max-keep-alive-requests}")
  private int maxKeepAliveRequests;
  @Value("${adf.common.tomcat.max-header-counts}")
  private int maxHeaderCounts;

  @Autowired
  TomcatCustomizer(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void customize(TomcatServletWebServerFactory factory) {
    factory.addConnectorCustomizers(connector -> {
      AbstractHttp11Protocol protocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
      protocol.setMaxHeaderCount(maxHeaderCounts);
      protocol.setMaxKeepAliveRequests(maxKeepAliveRequests);

      if (logger.isInfoEnabled()) {
        logger.info(String.format("ADF custom tomcat properties >>>"
                                  + "Keep alive requests: %s, "
                                  + "Keep alive timeout: %s, "
                                  + "Header counts: %s",
            protocol.getMaxKeepAliveRequests(),
            protocol.getKeepAliveTimeout(),
            protocol.getMaxHeaderCount()));
      }
    });
  }
}