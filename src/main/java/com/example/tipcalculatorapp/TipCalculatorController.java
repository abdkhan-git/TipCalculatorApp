package com.example.tipcalculatorapp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipCalculatorController {
    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // listener for changes to tipPercentageSlider's value
        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        tipPercentage =
                                BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));

                        BigDecimal amount = new BigDecimal(amountTextField.getText());

                        BigDecimal tip = amount.multiply(tipPercentage);
                        tipTextField.setText(currency.format(tip));

                        totalTextField.setText(currency.format(amount.add(tip)));
                    }
                }
        );

        amountTextField.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                        BigDecimal amount = new BigDecimal(0);
                        if (!amountTextField.getText().isEmpty()) {
                            amount = new BigDecimal(amountTextField.getText());
                        }

                        BigDecimal tip = amount.multiply(tipPercentage);
                        tipTextField.setText(currency.format(tip));

                        totalTextField.setText(currency.format(amount.add(tip)));
                    }
                }
        );
    }
}