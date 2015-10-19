package com.wirecard.controller;

import com.wirecard.resource.request.InitPaymentRequest;
import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.response.InitPaymentResponse;
import com.wirecard.resource.response.InitStorageResponse;
import com.wirecard.resource.response.ReadStorageResponse;
import com.wirecard.resource.type.LanguageType;
import com.wirecard.resource.type.PaymentType;
import com.wirecard.service.WirecardService;
import com.wirecard.util.SerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WirecardController {
    
    @Autowired
    private SerializerUtil serializerUtil;

    @Autowired
    private WirecardService wirecardService;

    private static final String SHOP_ID = "qmore";
    private static final String ORDER_IDENT = "12345";

    @RequestMapping(value = "/wirecard", method = RequestMethod.GET)
    public ModelAndView wirecard(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("wirecard");

        URL requestUrl = new URL(request.getRequestURL().toString());

        InitStorageRequest sir = new InitStorageRequest();
        {
            sir.setShopId(SHOP_ID);
            sir.setOrderIdent(ORDER_IDENT);
            sir.setReturnUrl(new URL(requestUrl.getProtocol(), requestUrl.getHost(), requestUrl.getPort(), "/fallback"));
            sir.setLanguage(LanguageType.EN);
            sir.setIframe(true);
//        URL cssUrl = new URL(requestUrl.getProtocol(), requestUrl.getHost(), requestUrl.getPort(), "/css/pci3_iframe_style2.css");
//        sir.setIframeCssUrl(cssUrl);
            //sir.setIframeCssUrl(new URL("http://www.google.com/cse/style/look/v2/default.css"));
        }

        InitStorageResponse response = wirecardService.initDataStorage(sir);
        mav.addObject("storage", response);
        return mav;
    }

    @RequestMapping(
            value = "/pay",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InitPaymentResponse pay(HttpServletRequest request, @RequestBody ReadStorageResponse storage) throws IOException {
        //wirecard accepts only 80 or 443
        URL requestUrl = new URL(request.getRequestURL().toString());
        URL statusUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http")?80:443, "/status");

        InitPaymentRequest pr = new InitPaymentRequest(); {
            pr.setStorageId(storage.getStorageId());
            pr.setShopId(SHOP_ID);
            pr.setOrderIdent(ORDER_IDENT);
            pr.setPaymentType(PaymentType.CCARD);

            //user
            pr.setConsumerIpAddress(request.getRemoteAddr());
            pr.setConsumerUserAgent(request.getHeader("User-Agent"));

            //callbacks
            pr.setConfirmUrl(statusUrl);
            pr.setPendingUrl(statusUrl);
            pr.setSuccessUrl(statusUrl);
            pr.setFailureURl(statusUrl);
            pr.setCancelUrl(statusUrl);
            pr.setServiceURL(statusUrl);

            //order
            pr.setOrderDescription("Test Order");
            pr.setCurrency(Currency.getInstance(Locale.GERMANY));
            pr.setAmount(new BigDecimal("99.99"));
            pr.setLanguage(LanguageType.EN);
        }
        InitPaymentResponse response =  wirecardService.initPayment(pr);
        return response;
    }

    @RequestMapping(value = "/status")
    public ModelAndView status(HttpServletRequest request) throws IOException {
        System.out.println("STATUS");
        
        serializerUtil.parseResponse(request.getInputStream());
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("status");
        //TODO
        return mav;
    }

    @RequestMapping(value = "/fallback")
    public void fallback(HttpServletRequest request, @RequestBody String body) {
        System.out.println("Fallback");
        System.out.println(body);
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/wirecard";
    }

}
