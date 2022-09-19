package com.development.fans.cryptocurrency.wallet.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.development.fans.cryptocurrency.wallet.dynamodb.models.Cryptocurrency;
import com.development.fans.cryptocurrency.wallet.exceptions.CryptocurrencyNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CryptocurrencyDaoTest {
    @Mock
    private DynamoDBMapper dynamoDbMapper;

    private CryptocurrencyDao cryptocurrencyDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        cryptocurrencyDao = new CryptocurrencyDao(dynamoDbMapper);
    }


    @Test
    public void getCryptocurrency_validCryptoName_returnsCryptocurrency() {
        // GIVEN
        String expectedCryptoName = "expectedCryptoName";
        String expectedCryptoDescription = "expectedCryptoDescription";
        Double expectedCryptoAmount = 25.0;
        Double expectedCryptoCost = 0.02;

        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setCryptoName(expectedCryptoName);
        cryptocurrency.setCryptoDescription(expectedCryptoDescription);
        cryptocurrency.setCryptoAmount(expectedCryptoAmount);
        cryptocurrency.setCryptoCost(expectedCryptoCost);

        when(dynamoDbMapper.load(Cryptocurrency.class, expectedCryptoName)).thenReturn(cryptocurrency);

        // WHEN
        Cryptocurrency result = cryptocurrencyDao.getCryptocurrency(expectedCryptoName);

        // THEN
        assertEquals(expectedCryptoName, result.getCryptoName());
        assertEquals(expectedCryptoDescription, result.getCryptoDescription());
        assertEquals(expectedCryptoAmount, result.getCryptoAmount());
        assertEquals(expectedCryptoCost, result.getCryptoCost());
    }

    @Test
    public void getCryptocurrency_validCryptoName_CryptocurrencyNotFoundException() {
        // GIVEN
        String notValidCryptoName = "notValidCryptoName";
        String expectedCryptoDescription = "expectedCryptoDescription";
        Double expectedCryptoAmount = 25.0;
        Double expectedCryptoCost = 0.02;

        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setCryptoName(notValidCryptoName);
        cryptocurrency.setCryptoDescription(expectedCryptoDescription);
        cryptocurrency.setCryptoAmount(expectedCryptoAmount);
        cryptocurrency.setCryptoCost(expectedCryptoCost);

        when(dynamoDbMapper.load(Cryptocurrency.class, notValidCryptoName)).thenReturn(null);

        // WHEN
        // THEN
        assertThrows(CryptocurrencyNotFoundException.class,
                () -> cryptocurrencyDao.getCryptocurrency(notValidCryptoName));
    }
}
