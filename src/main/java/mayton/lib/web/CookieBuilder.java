package mayton.lib.web;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class CookieBuilder {

    private String domain;
    private String name;
    private String value;
    private String path = "/";

    public boolean availableForSubDomains;
    public boolean secure;
    public long expiration;

    public CookieBuilder(String domain, String name, String value) {
        this.domain = domain;
        this.name = name;
        this.value = value;
        this.expiration = Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli();
    }

    public CookieBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public CookieBuilder withNameAndValue(String name, String value) {
        this.name = name;
        this.value = value;
        return this;
    }

    public Cookie build() {
        return new Cookie(domain, availableForSubDomains, path, secure, expiration, name, value);
    }
}
