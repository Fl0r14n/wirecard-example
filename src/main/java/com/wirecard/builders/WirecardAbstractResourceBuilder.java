package com.wirecard.builders;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.util.List;

public abstract class WirecardAbstractResourceBuilder {

    public abstract UrlEncodedFormEntity build(String clientId, String clientSecret);

    protected String getRequestFingerprintOrder(List<NameValuePair> attributes) {
        StringBuilder fingerprint = new StringBuilder();
        for (NameValuePair pair : attributes) {
            fingerprint.append(pair.getName());
            fingerprint.append(',');
        }
        fingerprint.append("requestFingerprintOrder");
        fingerprint.append(",secret");
        return fingerprint.toString();
    }

    protected String buildSHA512(List<NameValuePair> attributes, String secret) {
        StringBuilder buf = new StringBuilder();
        for (NameValuePair pair : attributes) {
            buf.append(pair.getValue());
        }
        buf.append(secret);
        return new String(DigestUtils.sha512Hex(buf.toString()));
    }
}
