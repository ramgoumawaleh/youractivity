package org.culturetripp.tonactiviter;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.culturetripp.tonactiviter.ActiviterController.getTotalAmountFromDatabase;
import static org.culturetripp.tonactiviter.ActiviterController.processPayment;


public class Payment {
    private static final String url = "jdbc:mysql://localhost:3306/tonactiviter";

    public static void main(String[] args) {
        // Configurez votre clé secrète Stripe
        Stripe.apiKey = "sk_test_51PECrDDHzZhhE0Midh16ssiZ3G7mQELSMzbcBzruZ7b3PKmnVprQ8oIn5nAm2nLvICJmGc4jZM6KBswEIirfqbBP00GFrXOY86";

        // Récupérez le montant total depuis la base de données
        double totalAmount = getTotalAmountFromDatabase();

        // Créez le paiement avec le montant total
        processPayment(totalAmount);


            try {
// Retrieve your account information
                Account account = Account.retrieve();
                System.out.println("Account ID: " + account.getId());
// Print other account information as needed
            } catch (StripeException e) {
                e.printStackTrace();
            }
        }

  }