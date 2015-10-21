package com.wirecard.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SerializerUtil {
    
    private static final Logger L = LoggerFactory.getLogger(SerializerUtil.class);

    public Map<String, String> parseResponse(InputStream is) throws IOException {
        Map<String, String> payload = new HashMap<>();
        String line;
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        while((line = buf.readLine()) != null) {
            L.info(line);
            if(line.startsWith("error")) {
                throw new IOException(URLDecoder.decode(line));
            }
            String[] params =  line.split("&");
            for(String param: params) {
                String[] kv = param.split("=");
                payload.put(kv[0], URLDecoder.decode(kv[1]));
            }
        }
        return payload;
    }

    public String getRequestFingerprintOrder(List<NameValuePair> attributes) {
        StringBuilder fingerprint = new StringBuilder();
        for (NameValuePair pair : attributes) {
            fingerprint.append(pair.getName());
            fingerprint.append(',');
        }
        fingerprint.append("requestFingerprintOrder");
        fingerprint.append(",secret");
        return fingerprint.toString();
    }

    public String buildSHA512(List<NameValuePair> attributes, String secret) {
        StringBuilder buf = new StringBuilder();
        for (NameValuePair pair : attributes) {
            buf.append(pair.getValue());
        }
        if(secret!=null) {
            buf.append(secret);
        }
        return DigestUtils.sha512Hex(buf.toString());
    }

    public String buildSHA512(List<NameValuePair> attributes) {
        return buildSHA512(attributes, null);
    }
    
    public boolean checkFingerprint(Map<String, String> params, String secret) {
        String responseFingerprintOrder = params.get("responseFingerprintOrder");
        String[] keys = responseFingerprintOrder.split(",");

        List<NameValuePair> pairs = new ArrayList<>();
        for(String key: keys) {
            if(key.equals("secret")) {
                pairs.add(new BasicNameValuePair("secret", secret));
            } else {
                pairs.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        String calculatedFingerprint = buildSHA512(pairs);
        String expectedFingerprint = params.get("responseFingerprint");        
        return expectedFingerprint.equals(calculatedFingerprint);
    }
}
