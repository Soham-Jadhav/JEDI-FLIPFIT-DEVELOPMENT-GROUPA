package com.flipfit.bean;

/**
 * Represents a payment method, specifically a credit card, for a transaction.
 * <p>
 * This class encapsulates credit card details. However, it's highly
 * recommended to avoid storing sensitive payment information like this
 * in plain text fields in a real-world application due to security risks.
 * Secure payment processing should use tokenization or a trusted third-party
 * payment gateway.
 *
 * @author Dhruv
 */
public class Payment {
    // It's a security risk to store these as public fields.
    // They should be private with secure handling.

    /**
     * The credit card number.
     * <p>
     * Note: In a real application, this should be handled securely
     * (e.g., encrypted or tokenized) and not stored directly.
     */
    public String creditCardNumber;

    /**
     * The credit card PIN.
     * <p>
     * Note: Storing a PIN is a major security vulnerability and should be
     * avoided at all costs.
     */
    public String creditCardPIN;

    /**
     * The credit card expiry date.
     * <p>
     * Note: This is also sensitive data and should be handled securely.
     */
    public String creditCardExpiry;

    /**
     * Constructs a default Payment object.
     */
    public Payment() {
        super();
    }

    /**
     * Constructs a new Payment object with the specified credit card details.
     *
     * @param creditCardNumber The credit card number.
     * @param creditCardPIN The PIN of the credit card.
     * @param creditCardExpiry The expiry date of the credit card (e.g., "MM/YY").
     */
    public Payment(String creditCardNumber, String creditCardPIN, String creditCardExpiry) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardPIN = creditCardPIN;
        this.creditCardExpiry = creditCardExpiry;
    }

    // Add getters and setters here for better encapsulation and control over data access.
}