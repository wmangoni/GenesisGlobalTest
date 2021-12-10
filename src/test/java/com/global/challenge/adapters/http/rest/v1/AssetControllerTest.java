package com.global.challenge.adapters.http.rest.v1;

import com.global.challenge.adapters.http.HttpCoincapRequestAdapter;
import com.global.challenge.domain.Coin;
import com.global.challenge.ports.outgoing.HttpCoincapPort;
import com.global.challenge.ports.outgoing.IoFileReaderPort;
import com.global.challenge.services.AssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest({
        AssetController.class,
        AssetService.class,
        IoFileReaderPort.class,
        HttpCoincapPort.class
})
@SpringJUnitConfig
class AssetControllerTest {

    @Autowired
    private AssetController assetController;

    @MockBean
    private HttpCoincapRequestAdapter port;

    private final String BASE_URI = "https://api.coincap.io/v2/assets/";

    @Test
    void generateReportTest() throws IOException, InterruptedException {

        when(port.getAssetId(anyString())).thenCallRealMethod();

        when(port.getAssetHistory(any(Coin.class))).thenCallRealMethod();

        when(port.getStringHttpResponse(eq(BASE_URI + "usd-coin")))
                .thenReturn("{\"data\":{\"id\":\"usd-coin\",\"rank\":\"7\",\"symbol\":\"USDC\",\"name\":\"USD Coin\",\"supply\":\"41038563797.5804300000000000\",\"maxSupply\":null,\"marketCapUsd\":\"41104341407.7246324287390994\",\"volumeUsd24Hr\":\"2735658747.3212806313381635\",\"priceUsd\":\"1.0016028243695039\",\"changePercent24Hr\":\"-0.1771837034493291\",\"vwap24Hr\":\"0.9997763717292620\",\"explorer\":\"https://etherscan.io/token/0xa0b86991c6218b36c1d19d4a2e9eb0ce3606eb48\"},\"timestamp\":1639099992012}");

        when(port.getStringHttpResponse(eq(getUri("usd-coin"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"1.0008931957673245\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639099992978}");

        when(port.getStringHttpResponse(eq(BASE_URI + "bitcoin")))
                .thenReturn("{\"data\":{\"id\":\"bitcoin\",\"rank\":\"1\",\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"supply\":\"18896556.0000000000000000\",\"maxSupply\":\"21000000.0000000000000000\",\"marketCapUsd\":\"915967122521.7401199863789832\",\"volumeUsd24Hr\":\"18394421259.4577511764456018\",\"priceUsd\":\"48472.7017199187047622\",\"changePercent24Hr\":\"-4.0256234144595147\",\"vwap24Hr\":\"48794.1413669249852247\",\"explorer\":\"https://blockchain.info/\"},\"timestamp\":1639099993902}");

        when(port.getStringHttpResponse(eq(getUri("bitcoin"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"56999.9728252053067291\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639099994847}");

        when(port.getStringHttpResponse(eq(BASE_URI + "ethereum")))
                .thenReturn("{\"data\":{\"id\":\"ethereum\",\"rank\":\"2\",\"symbol\":\"ETH\",\"name\":\"Ethereum\",\"supply\":\"118674202.0615000000000000\",\"maxSupply\":null,\"marketCapUsd\":\"494322518071.4557851207440989\",\"volumeUsd24Hr\":\"16249115735.5208860319470413\",\"priceUsd\":\"4165.3746937795734363\",\"changePercent24Hr\":\"-6.0960476703653732\",\"vwap24Hr\":\"4228.1859900426686550\",\"explorer\":\"https://etherscan.io/\"},\"timestamp\":1639099995816}");

        when(port.getStringHttpResponse(eq(getUri("ethereum"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"2032.1394325557042107\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639099996794}");

        when(port.getStringHttpResponse(eq(BASE_URI + "xrp")))
                .thenReturn("{\"data\":{\"id\":\"xrp\",\"rank\":\"8\",\"symbol\":\"XRP\",\"name\":\"XRP\",\"supply\":\"45404028640.0000000000000000\",\"maxSupply\":\"100000000000.0000000000000000\",\"marketCapUsd\":\"39545631702.7943895350828800\",\"volumeUsd24Hr\":\"2605297864.8236030260470697\",\"priceUsd\":\"0.8709718693982920\",\"changePercent24Hr\":\"0.6681007864295060\",\"vwap24Hr\":\"0.8817202938864003\",\"explorer\":\"https://xrpcharts.ripple.com/#/graph/\"},\"timestamp\":1639099997810}");

        when(port.getStringHttpResponse(eq(getUri("xrp"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"0.97093845205376820799\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639099998794}");

        when(port.getStringHttpResponse(eq(BASE_URI + "binance-coin")))
                .thenReturn("{\"data\":{\"id\":\"binance-coin\",\"rank\":\"3\",\"symbol\":\"BNB\",\"name\":\"Binance Coin\",\"supply\":\"166801148.0000000000000000\",\"maxSupply\":\"166801148.0000000000000000\",\"marketCapUsd\":\"96815402292.7322256623844392\",\"volumeUsd24Hr\":\"1111806499.8222959165994463\",\"priceUsd\":\"580.4240765341268854\",\"changePercent24Hr\":\"-5.3895033673896543\",\"vwap24Hr\":\"589.4490110866658858\",\"explorer\":\"https://etherscan.io/token/0xB8c77482e45F1F44dE1745F52C74426C631bDD52\"},\"timestamp\":1639099999761}");

        when(port.getStringHttpResponse(eq(getUri("binance-coin"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"388.4379891338453617\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639100000742}");

        when(port.getStringHttpResponse(eq(BASE_URI + "solana")))
                .thenReturn("{\"data\":{\"id\":\"solana\",\"rank\":\"5\",\"symbol\":\"SOL\",\"name\":\"Solana\",\"supply\":\"307347411.0527574400000000\",\"maxSupply\":null,\"marketCapUsd\":\"56176231333.4061623642987464\",\"volumeUsd24Hr\":\"658841773.0973411658390223\",\"priceUsd\":\"182.7776298521131272\",\"changePercent24Hr\":\"-5.7005105330132965\",\"vwap24Hr\":\"185.8989191261145591\",\"explorer\":\"https://explorer.solana.com/\"},\"timestamp\":1639100001674}");

        when(port.getStringHttpResponse(eq(getUri("solana"))))
                .thenReturn("{\"data\":[{\"priceUsd\":\"25.6660172156944524\",\"time\":1617753600000,\"date\":\"2021-04-07T00:00:00.000Z\"}],\"timestamp\":1639100002624}");

        String result = assetController.generateReport();
        String expected = "total = 124864.0848220533, best_asset = SOL, best_performance = 5840.118912885574, worst_asset = BTC, worst_performance = 1.2801150384884363";
        assertNotNull(assetController);
        assertEquals(expected, result);
    }

    String getUri(final String coinId) {
        String filter = "interval=d1&start=1617753600000&end=1617753601000";
        return String.format("%s%s/history?%s", BASE_URI, coinId, filter);
    }
}