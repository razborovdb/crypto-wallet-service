package com.development.fans.cryptocurrency.wallet.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Wallet;
import com.development.fans.cryptocurrency.wallet.exceptions.WalletNotFoundException;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class WalletsDaoTest {
    @Mock
    private DynamoDBMapper dynamoDbMapper;

    private WalletDao walletsDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        walletsDao = new WalletDao(dynamoDbMapper);
    }


    @Test
    public void getWallet_validCustomerIdAndWalletName_returnsWallet() {
        // GIVEN
        String expectedCustomerId = "expectedCustomerId";
        String expectedWalletName = "expectedWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(expectedCustomerId);
        wallet.setWalletName(expectedWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, expectedCustomerId, expectedWalletName)).thenReturn(wallet);

        // WHEN
        Wallet result = walletsDao.getWallet(expectedCustomerId, expectedWalletName);

        // THEN
        assertEquals(expectedCustomerId, result.getCustomerId());
        assertEquals(expectedWalletName, result.getWalletName());
        assertEquals(expectedWalletDescription, result.getWalletDescription());
        assertEquals(expectedCryptoCount, result.getCryptosCount());
        assertEquals(expectedCryptoCost, result.getCryptosCost());
        assertEquals(expectedCryptocurrenciesList, result.getCryptocurrenciesList());
    }

    @Test
    public void getWallet_notValidCustomerId_WalletNotFoundException() {
        // GIVEN
        String notValidCustomerId = "notValidCustomerId";
        String expectedWalletName = "expectedWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(notValidCustomerId);
        wallet.setWalletName(expectedWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, notValidCustomerId, expectedWalletName)).thenReturn(null);

        // WHEN
        // THEN
        assertThrows(WalletNotFoundException.class, () -> walletsDao.getWallet(notValidCustomerId, expectedWalletName));
    }

    @Test
    public void getWallet_notValidWalletName_WalletNotFoundException() {
        // GIVEN
        String expectedCustomerId = "expectedCustomerId";
        String notValidWalletName = "notValidWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(expectedCustomerId);
        wallet.setWalletName(notValidWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, expectedCustomerId, notValidWalletName)).thenReturn(null);

        // WHEN
        // THEN
        assertThrows(WalletNotFoundException.class, () -> walletsDao.getWallet(expectedCustomerId, notValidWalletName));
    }

    @Test
    public void saveWallet_validCustomerIdAndWalletName_returnsWallet() {
        // GIVEN
        String expectedCustomerId = "expectedCustomerId";
        String expectedWalletName = "expectedWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(expectedCustomerId);
        wallet.setWalletName(expectedWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, expectedCustomerId, expectedWalletName)).thenReturn(wallet);

        // WHEN
        Wallet result = walletsDao.saveWallet(wallet);

        // THEN
        verify(dynamoDbMapper).save(wallet);
        assertEquals(expectedCustomerId, result.getCustomerId());
        assertEquals(expectedWalletName, result.getWalletName());
        assertEquals(expectedWalletDescription, result.getWalletDescription());
        assertEquals(expectedCryptoCount, result.getCryptosCount());
        assertEquals(expectedCryptoCost, result.getCryptosCost());
        assertEquals(expectedCryptocurrenciesList, result.getCryptocurrenciesList());
    }

    @Test
    public void saveWallet_notValidCustomerId_WalletNotFoundException() {
        // GIVEN
        String notValidCustomerId = "notValidCustomerId";
        String expectedWalletName = "expectedWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(notValidCustomerId);
        wallet.setWalletName(expectedWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, notValidCustomerId, expectedWalletName)).thenReturn(null);

        // WHEN
        // THEN
        assertThrows(WalletNotFoundException.class, () -> walletsDao.saveWallet(wallet));
        verify(dynamoDbMapper).save(wallet);
    }
    @Test
    public void saveWallet_notValidWalletName_WalletNotFoundException() {
        // GIVEN
        String expectedCustomerId = "expectedCustomerId";
        String notValidWalletName = "notValidWalletName";
        String expectedWalletDescription = "expectedWalletDescription";
        Double expectedCryptoCount = 1.0;
        Double expectedCryptoCost = 0.03;
        List<Cryptocurrency> expectedCryptocurrenciesList = Lists.newArrayList();

        Wallet wallet = new Wallet();
        wallet.setCustomerId(expectedCustomerId);
        wallet.setWalletName(notValidWalletName);
        wallet.setWalletDescription(expectedWalletDescription);
        wallet.setCryptosCount(expectedCryptoCount);
        wallet.setCryptosCost(expectedCryptoCost);
        wallet.setCryptocurrenciesList(expectedCryptocurrenciesList);

        when(dynamoDbMapper.load(Wallet.class, expectedCustomerId, notValidWalletName)).thenReturn(null);

        // WHEN
        // THEN
        assertThrows(WalletNotFoundException.class, () -> walletsDao.saveWallet(wallet));
        verify(dynamoDbMapper).save(wallet);
    }
}
