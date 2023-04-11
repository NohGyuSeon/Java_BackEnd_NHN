package payments;

import java.util.HashMap;
import java.util.Map;
import framework.*;

public class Cash implements Payment {
    int balance = 5000;
    Map<String, Integer> result;

    public void returnChanges(int inputMoney) {
        int cost = balance - inputMoney;
        Map<String, Integer> coinCounter = new HashMap<>();
        while (cost >= 500) {
            cost -= 500;
            coinCounter.put("500", coinCounter.getOrDefault("500", 0) + 1);
        }
        while (cost >= 100) {
            cost -= 100;
            coinCounter.put("100", coinCounter.getOrDefault("100", 0) + 1);
        }

        result = coinCounter;
    }
    
    public boolean canPay(int money) {
        return balance >= money;
    }

    @Override
    public void pay(int money) {
        if (canPay(money)) {
            returnChanges(money);
        }
    }

    @Override
    public void showResult() {
        result.entrySet().stream()
        .forEach(entry -> System.out.printf("%s원 : %d개 ", entry.getKey(), entry.getValue())
        );
        System.out.println();
    }
    
}
