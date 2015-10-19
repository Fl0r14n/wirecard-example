package com.wirecard.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SerializerUtil {

    public Map<String, String> parseResponse(InputStream is) throws IOException {
        Map<String, String> payload = new HashMap<>();
        String line;
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        while((line = buf.readLine()) != null) {
            System.out.println(line);
            if(line.startsWith("error")) {
                throw new IOException(line);
            }
            String[] params =  line.split("&");
            for(String param: params) {
                String[] kv = param.split("=");
                payload.put(kv[0], kv[1]);
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
        buf.append(secret);
        return DigestUtils.sha512Hex(buf.toString());
    }
}
