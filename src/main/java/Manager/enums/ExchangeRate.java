package Manager.enums;

public enum ExchangeRate {

    RON_USD(0.23), RON_EUR(0.21), RON_GBP(0.18),
    USD_RON(4.26), USD_EUR(0.90), USD_GBP(0.77),
    EUR_RON(4.76), EUR_USD(1.12), EUR_GBP(0.86),
    GBP_RON(5.54), GBP_USD(1.30), GBP_EUR(1.17);

    private double rate;

    ExchangeRate(double new_rate) {
        rate = new_rate;
    }

    private double getRate() {
        return this.rate;
    }
}
