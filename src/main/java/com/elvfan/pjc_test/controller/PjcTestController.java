package com.elvfan.pjc_test.controller;

import com.elvfan.pjc_test.service.PjcTestService;
import commutative.EcCommutativeCipher;
import commutative.SupportedCurve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/test")
public class PjcTestController {

    public static final EcCommutativeCipher cipher = EcCommutativeCipher.createWithNewKey(SupportedCurve.SECP256R1);

    @Autowired
    private PjcTestService service;

    @GetMapping("/{input}")
    public String saveTestId(@PathVariable String input) {
        byte[] encrypted = cipher.encrypt(input.getBytes(StandardCharsets.UTF_8));
//        encrypted = cipher.reEncrypt(encrypted);

        String result = Base64.getEncoder().encodeToString(encrypted);
        service.addIdToBatch(input, result);
        return result;
    }
}
