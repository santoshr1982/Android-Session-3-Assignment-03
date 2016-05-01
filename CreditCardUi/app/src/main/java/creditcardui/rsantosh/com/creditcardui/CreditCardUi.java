package creditcardui.rsantosh.com.creditcardui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreditCardUi extends AppCompatActivity implements View.OnClickListener {

    //Creating Objects of controls.
    Button button_compute;
    Button button_clear;
    EditText editTextCardBalance;
    EditText editTextYearlyInterestRate;
    EditText editTextMinimumPayment;
    EditText editTextInterestPaid;
    EditText editMonthsRemaining;
    EditText editFinalBalance;


    //Declaring the variables
    float fRateOfInterest;
    long lCardBalance;
    long lMinimumPayment;
    int intMonth;
    float fInterestPaid;
    long lMonthlyPrincple;
    float fMonthlyInterest;
    float fTotalInterest;
    String strAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_ui);


        //Initialisation of the objects.
        button_compute = (Button)findViewById(R.id.button_compute);
        button_compute.setOnClickListener(this);

        button_clear=(Button)findViewById((R.id.button_clear));
        button_clear.setOnClickListener(this);

        editTextCardBalance= (EditText)findViewById(R.id.editText_Card_Balance);
        editTextYearlyInterestRate = (EditText)findViewById(R.id.editText_Yearly_Interest);
        editTextMinimumPayment=(EditText)findViewById(R.id.editText_minimum_payment);
        editTextInterestPaid=(EditText)findViewById(R.id.editText_interest_paid);
        editMonthsRemaining=(EditText)findViewById(R.id.editText_months_remaining);
        editFinalBalance=(EditText)findViewById(R.id.editText_final_card_balance);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button_compute:

                //Validating the input.
                if((editTextCardBalance.getText().toString().trim()).isEmpty())
                {
                    Toast.makeText(this,"Please enter a valid number.",Toast.LENGTH_LONG).show();
                    editTextCardBalance.setText("");
                    break;

                }else {

                    try {

                        lCardBalance = Long.parseLong(editTextCardBalance.getText().toString().trim());

                    }catch(NumberFormatException e) {

                        Toast.makeText(CreditCardUi.this, "Enter numbers only", Toast.LENGTH_SHORT).show();
                        editTextCardBalance.setText("");
                        break;
                    }

                    if(editTextYearlyInterestRate.getText().toString().trim().isEmpty()){

                        Toast.makeText(this,"Please enter a valid rate of interest.",Toast.LENGTH_LONG).show();
                        editTextYearlyInterestRate.setText("");
                        break;

                    }else

                    try {

                        fRateOfInterest = Float.parseFloat(editTextYearlyInterestRate.getText().toString().trim());
                        Log.v("fRateOfInterest","onClick()");

                    }catch(NumberFormatException e) {

                        Toast.makeText(CreditCardUi.this, "Enter numbers only", Toast.LENGTH_SHORT).show();
                        editTextYearlyInterestRate.setText("");
                        break;
                    }

                    if(editTextMinimumPayment.getText().toString().isEmpty()) {

                        Toast.makeText(this, "Please enter minimum payment you paid.", Toast.LENGTH_LONG).show();
                        editTextMinimumPayment.setText("");
                        break;
                    }else{

                        try {

                            lMinimumPayment = Long.parseLong(editTextMinimumPayment.getText().toString().trim());

                        }catch(NumberFormatException e) {

                            Toast.makeText(CreditCardUi.this, "Enter numbers only", Toast.LENGTH_SHORT).show();
                            editTextMinimumPayment.setText("");
                            break;
                        }

                        //Declaring and initialising the variables to calculate.
                        intMonth=0;
                        fMonthlyInterest=0;
                        fTotalInterest=0;

                        //For loop to calculate the interest and months remaining.
                        for (long i = lCardBalance;i>=0;  i=(lCardBalance)){
                            fMonthlyInterest = Math.round(lCardBalance * (fRateOfInterest / (100 * 12)));
                            lMonthlyPrincple = (long) (lMinimumPayment - fMonthlyInterest);
                            intMonth++;
                            fTotalInterest=fTotalInterest+fMonthlyInterest;
                            lCardBalance=lCardBalance-lMonthlyPrincple;


                       }

                        //Printing Output.
                        editFinalBalance.setText(editTextCardBalance.getText().toString().trim());
                        strAnswer=Float.toString(fTotalInterest);
                        editTextInterestPaid.setText(strAnswer);
                        strAnswer=Integer.toString(intMonth);
                        editMonthsRemaining.setText(strAnswer);
                        break;
                    }

                }

                //This case is written to clear all the text boxes.
            case R.id.button_clear:

                editTextCardBalance.setText("");
                editTextYearlyInterestRate.setText("");
                editTextMinimumPayment.setText("");
                editFinalBalance.setText("");
                editMonthsRemaining.setText("");
                editTextInterestPaid.setText("");
                break;
        }
    }
}
