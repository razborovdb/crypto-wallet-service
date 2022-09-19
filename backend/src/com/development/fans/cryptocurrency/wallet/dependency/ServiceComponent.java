package com.development.fans.cryptocurrency.wallet.dependency;

import com.development.fans.cryptocurrency.wallet.activity.AddCryptocurrencyToWalletActivity;
import com.development.fans.cryptocurrency.wallet.activity.CreateWalletActivity;
import com.development.fans.cryptocurrency.wallet.activity.GetAllWalletsActivity;
import com.development.fans.cryptocurrency.wallet.activity.GetAvailableCryptocurrenciesActivity;
import com.development.fans.cryptocurrency.wallet.activity.GetWalletActivity;
import com.development.fans.cryptocurrency.wallet.activity.UpdateCryptocurrencyInWalletActivity;
import com.development.fans.cryptocurrency.wallet.activity.UpdateExchangeRateInWalletActivity;
import com.development.fans.cryptocurrency.wallet.activity.UpdateWalletActivity;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DaoModule.class })
/**
 * ServiceComponent.
 */
public interface ServiceComponent {
    /**
     * provideAddCryptocurrencyToWalletActivity.
     * @return - GetWalletActivity
     */
    AddCryptocurrencyToWalletActivity provideAddCryptocurrencyToWalletActivity();

    /**
     * provideCreateWalletActivity.
     * @return - CreateWalletActivity
     */
    CreateWalletActivity provideCreateWalletActivity();

    /**
     * provideGetAllWalletsActivity.
     * @return - GetAllWalletsActivity
     */
    GetAllWalletsActivity provideGetAllWalletsActivity();

    /**
     * provideGetAvailableCryptocurrenciesActivity.
     * @return - GetAvailableCryptocurrenciesActivity
     */
    GetAvailableCryptocurrenciesActivity provideGetAvailableCryptocurrenciesActivity();

    /**
     * provideGetWalletActivity.
     * @return - GetWalletActivity
     */
    GetWalletActivity provideGetWalletActivity();

    /**
     * provideUpdateAvailableCryptocurrenciesActivity.
     * @return - UpdateExchangeRateInWalletActivity
     */
    UpdateExchangeRateInWalletActivity provideUpdateExchangeRateInWalletActivity();


    /**
     * provideUpdateCryptocurrencyAmountInWalletActivity.
     * @return - UpdateCryptocurrencyInWalletActivity
     */
    UpdateCryptocurrencyInWalletActivity provideUpdateCryptocurrencyInWalletActivity();

    /**
     * provideUpdateWalletActivity.
     * @return - UpdateWalletActivity
     */
    UpdateWalletActivity provideUpdateWalletActivity();
}
