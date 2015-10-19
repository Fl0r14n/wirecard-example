package com.wirecard.serializer;

import com.wirecard.resource.WirecardRequest;
import com.wirecard.resource.WirecardResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.InputStream;

public interface WirecardSerializer<S extends WirecardRequest, R extends WirecardResponse> {

    R from(InputStream content);

    UrlEncodedFormEntity to(S request);
}
