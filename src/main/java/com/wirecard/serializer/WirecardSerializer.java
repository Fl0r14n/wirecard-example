package com.wirecard.serializer;

import com.wirecard.resource.WirecardRequest;
import com.wirecard.resource.WirecardResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.IOException;
import java.io.InputStream;

public interface WirecardSerializer<S extends WirecardRequest, R extends WirecardResponse> {

    R from(InputStream content) throws IOException;

    UrlEncodedFormEntity to(S request) throws IOException;
}
