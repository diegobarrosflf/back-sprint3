package br.com.rchlo.store.controller.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.domain.PaymentStatus;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PaymentForm {
    @NotNull
    @Positive
    private BigDecimal value;

    @NotNull
    @NotEmpty
    @Length(min=5, max=100, message = "O tamnaho deve ser entre 5 e 100 caracteres")
    private String cardClientName;

    @NotNull
    @NotEmpty
    @Digits(integer = 16, fraction = 0)
    @Length(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d{4}-\\d{2}")
    private String cardExpiration;

    @NotNull
    @NotEmpty
    @Digits(integer = 3, fraction = 0)
    @Length(min = 3, max = 3)
    private String cardVerificationCode;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCardClientName() {
        return cardClientName;
    }

    public void setCardClientName(String cardClientName) {
        this.cardClientName = cardClientName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiration() {
        return cardExpiration;
    }

    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public String getCardVerificationCode() {
        return cardVerificationCode;
    }

    public void setCardVerificationCode(String cardVerification) {
        this.cardVerificationCode = cardVerification;
    }

    public Payment convert() {
        return new Payment(value, PaymentStatus.CREATED, new Card(
                cardClientName,
                cardNumber,
                cardExpiration,
                cardVerificationCode
        ));
    }

    public boolean dateValidation(){
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        try {
            Date cardDate = df.parse(this.cardExpiration);
            return cardDate.after(today);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
