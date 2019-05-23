package com.im.zemel.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemotelyService {
    private RestTemplate restTemplate = new RestTemplate();

}
