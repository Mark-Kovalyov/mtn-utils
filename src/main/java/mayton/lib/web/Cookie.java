package mayton.lib.web;

public final class Cookie {

    // example.com   TRUE   /path   FALSE   1672531199   SESSIONID   abc123
    public final String domain; // – The domain that set the cookie.
    public final boolean availableForSubDomains; // Flag – TRUE if the cookie is available to subdomains, FALSE otherwise.
    public final String path; // – The URL path for which the cookie is valid.
    public final boolean secure;// – TRUE if the cookie should only be sent over HTTPS, FALSE otherwise.
    public final long expiration; // – Unix timestamp (seconds since 1970) indicating when the cookie expires.
    public final String name;// – The name of the cookie.
    public final String value; // – The value of the cookie.

    public Cookie(String domain, boolean availableForSubDomains, String path, boolean secure, long expiration, String name, String value) {
        this.domain = domain;
        this.availableForSubDomains = availableForSubDomains;
        this.path = path;
        this.secure = secure;
        this.expiration = expiration;
        this.name = name;
        this.value = value;
    }

    public String toTsv() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s",
                domain,
                Boolean.valueOf(availableForSubDomains).toString(),
                path,
                Boolean.valueOf(secure).toString(),
                expiration,
                name,
                value);
    }
}
