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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;

@Controller
public class WirecardController {

    private static final String SHOP_ID = "qmore";
    private static final String ORDER_IDENT = "12345";
    @Autowired
    private SerializerUtil serializerUtil;
    @Autowired
    private WirecardService wirecardService;

    @RequestMapping(value = "/wirecard", method = RequestMethod.GET)
    public ModelAndView wirecard(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("wirecard");

        URL requestUrl = new URL(request.getRequestURL().toString());
        URL cssUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/css/pci3_iframe_style2.css");
        System.out.println(cssUrl);

        InitStorageRequest sir = new InitStorageRequest();
        {
            sir.setShopId(SHOP_ID);
            sir.setOrderIdent(ORDER_IDENT);
            sir.setReturnUrl(new URL(requestUrl.getProtocol(), requestUrl.getHost(), requestUrl.getPort(), "/fallback"));
            sir.setLanguage(LanguageType.EN);
            sir.setIframe(true);
            //sir.setIframeCssUrl(cssUrl);
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
        URL confirmUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/confirm");
        URL pendingUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/pending");
        URL successUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/success");
        URL failureUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/failure");
        URL cancelUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/cancel");
        URL serviceUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/service");

        System.out.println(confirmUrl.toString());

        InitPaymentRequest pr = new InitPaymentRequest();
        {
            pr.setStorageId(storage.getStorageId());
            pr.setShopId(SHOP_ID);
            pr.setOrderIdent(ORDER_IDENT);
            pr.setPaymentType(PaymentType.CCARD);

            //user
            pr.setConsumerIpAddress(request.getRemoteAddr());
            pr.setConsumerUserAgent(request.getHeader("User-Agent"));

            //callbacks
            pr.setConfirmUrl(confirmUrl);
            pr.setPendingUrl(pendingUrl);
            pr.setSuccessUrl(successUrl);
            pr.setFailureURl(failureUrl);
            pr.setCancelUrl(cancelUrl);
            pr.setServiceURL(serviceUrl);

            //order
            pr.setOrderDescription("Test Order");
            pr.setCurrency(Currency.getInstance(Locale.GERMANY));
            pr.setAmount(new BigDecimal("99.99"));
            pr.setLanguage(LanguageType.EN);
        }
        InitPaymentResponse response = wirecardService.initPayment(pr);
        return response;
    }

    @RequestMapping(value = "/confirm")
    public void confirm(HttpServletRequest request) throws IOException {
        //TODO wirecard does not call confirm why?
        System.out.println("Confirm");
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getMethod());
        System.out.println(request.getQueryString());
        serializerUtil.parseResponse(request.getInputStream());
    }

    @RequestMapping(value = "/pending")
    public ModelAndView pending() throws IOException {
        return new ModelAndView("pending");
    }

    @RequestMapping(value = "/success")
    public ModelAndView success() throws IOException {
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/failure")
    public ModelAndView failure() throws IOException {
        return new ModelAndView("failure");
    }

    @RequestMapping(value = "/cancel")
    public ModelAndView cancel() throws IOException {
        return new ModelAndView("cancel");
    }

    @RequestMapping(value = "/service")
    public ModelAndView service() throws IOException {
        return new ModelAndView("service");
    }

    @RequestMapping(value = "/fallback")
    public void fallback() {
        //Dummy callback for wirecard
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/wirecard";
    }

}
