package com.global.challenge.adapters.http.rest.v1;

import com.global.challenge.services.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/assets")
public class AssetController {

    Logger logger = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    private AssetService assetService;

    @GetMapping
    public String findBitcoin() {
        try {
            return assetService.getAssetInfo();
        } catch (IOException | InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
