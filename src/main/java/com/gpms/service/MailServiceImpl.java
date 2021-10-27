package com.gpms.service;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public String sendMail() {
        System.out.println("this is sendMail.");
        return "sendMail";
    }

    @Override
    public String testMail() {
        System.out.println("this is testMail.");
        return "testMail";
    }
}
