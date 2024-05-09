package org.culturetripp.tonactiviter;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class Payment {
    private float totalAmount;

    public static void main(String[] args) {

        // Set your secret key here
        Stripe.apiKey = "sk_test_51PECrDDHzZhhE0Midh16ssiZ3G7mQELSMzbcBzruZ7b3PKmnVprQ8oIn5nAm2nLvICJmGc4jZM6KBswEIirfqbBP00GFrXOY86";

        // Retrieve your account information
        Account account = null;
        try {
            account = Account.retrieve();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Account ID: " + account.getId());
        // Print other account information as needed

        processPayment(); // Call the processPayment method
    }

    private static void processPayment() {
        try {
            // Set your secret key here
            Stripe.apiKey = "sk_test_51PECrDDHzZhhE0Midh16ssiZ3G7mQELSMzbcBzruZ7b3PKmnVprQ8oIn5nAm2nLvICJmGc4jZM6KBswEIirfqbBP00GFrXOY86";

            // Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            // If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
            // If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }
}
