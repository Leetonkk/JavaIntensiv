package homework.task1.currency.controllers;

import homework.task1.currency.services.CurrencyOperationsExecutorService;

import java.util.Scanner;

public class ConsoleController {
    private final CurrencyOperationsExecutorService currencyOperationsExecutorService;

    public ConsoleController(CurrencyOperationsExecutorService currencyOperationsExecutorService) {
        this.currencyOperationsExecutorService = currencyOperationsExecutorService;
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String query = in.nextLine();
            if ("stop".equalsIgnoreCase(query)) {
                break;
            }
            currencyOperationsExecutorService.executeOperation(query);
        }

    }
}
