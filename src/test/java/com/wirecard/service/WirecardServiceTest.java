package com.wirecard.service;

import com.example.WirecardSeamlessExampleApplication;
import com.wirecard.resource.request.InitPaymentRequest;
import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.request.ReadStorageRequest;
import com.wirecard.resource.response.InitPaymentResponse;
import com.wirecard.resource.response.InitStorageResponse;
import com.wirecard.resource.response.ReadStorageResponse;
import com.wirecard.resource.type.LanguageType;
import com.wirecard.resource.type.PaymentType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WirecardSeamlessExampleApplication.class)
@WebAppConfiguration
public class WirecardServiceTest {

    private static final String SHOP_ID = "qmore";
    private static final String ORDER_IDENT = "12345";
    @Autowired
    private WirecardService wirecardService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    public InitStorageResponse doInitDataStorage() throws IOException {
        InitStorageRequest sir = new InitStorageRequest();
        {
            sir.setShopId(SHOP_ID);
            sir.setOrderIdent(ORDER_IDENT);
            sir.setReturnUrl(new URL("http://localhost:80/wirecard/index.php/frontend/fallback_return.php"));
            sir.setLanguage(LanguageType.EN);
        }
        return wirecardService.initDataStorage(sir);
    }

    @Test
    public void init_data_storage_should_return_result() throws IOException {
        InitStorageResponse response = doInitDataStorage();
        Assert.assertNotNull(response.getJavascriptUrl());
        Assert.assertNotNull(response.getStorageId());
    }

    @Test
    public void init_data_storage_for_iframe_should_return_result() throws IOException {
        InitStorageRequest sir = new InitStorageRequest();
        {
            sir.setIframe(true);

            sir.setShopId(SHOP_ID);
            sir.setOrderIdent(ORDER_IDENT);
            sir.setReturnUrl(new URL("http://localhost:80/wirecard/index.php/frontend/fallback_return.php"));
            sir.setLanguage(LanguageType.EN);
            //TODO check to see why it does not accept css Url
            //sir.setIframeCssUrl(new URL("http://www.google.com/cse/style/look/v2/default.css"));
            //sir.setIframeCssUrl(new URL("http://localhost.8080/css/pci3_iframe.css"));
        }
        InitStorageResponse response = wirecardService.initDataStorage(sir);
        Assert.assertNotNull(response.getJavascriptUrl());
        Assert.assertNotNull(response.getStorageId());
    }

    @Test
    public void read_data_storage_should_return_result() throws IOException {
        InitStorageResponse sirr = doInitDataStorage();
        ReadStorageRequest sr = new ReadStorageRequest();
        {
            sr.setShopId(SHOP_ID);
            sr.setStorageId(sirr.getStorageId());
        }
        ReadStorageResponse result = wirecardService.readDataStorage(sr);
        Assert.assertNotNull(result.getStorageId());
        //TODO check more from response
    }

    @Test
    public void init_payment_shoud_return_response() throws IOException {
        InitStorageResponse storage = doInitDataStorage();

        InitPaymentRequest pr = new InitPaymentRequest();
        {
            pr.setStorageId(storage.getStorageId());
            pr.setShopId(SHOP_ID);
            pr.setOrderIdent(ORDER_IDENT);
            pr.setPaymentType(PaymentType.CCARD);

            pr.setConsumerIpAddress("193.17.194.226");
            pr.setConsumerUserAgent("Safari/7046A194A");

            //does not accept other ports than 80 or 443 or localhost. Must fake localhost
            pr.setConfirmUrl(new URL("http://hybris.local/status"));

            pr.setPendingUrl(new URL("http://hybris.local/status"));
            pr.setSuccessUrl(new URL("http://hybris.local/status"));
            pr.setFailureURl(new URL("http://hybris.local/status"));
            pr.setCancelUrl(new URL("http://hybris.local/status"));
            pr.setServiceURL(new URL("http://hybris.local/status"));

            pr.setOrderDescription("Test Order");
            pr.setCurrency(Currency.getInstance(Locale.GERMANY));
            pr.setAmount(new BigDecimal("99.99"));
            pr.setLanguage(LanguageType.EN);
        }
        exception.expect(IOException.class);
        exception.expectMessage(new Contains("No+payment+information+available+for+this+payment+method"));
        InitPaymentResponse result = wirecardService.initPayment(pr);
    }
}
