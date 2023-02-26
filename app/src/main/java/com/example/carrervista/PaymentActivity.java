//package com.example.carrervista;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.braintreepayments.api.BraintreeFragment;
//import com.braintreepayments.api.exceptions.InvalidArgumentException;
//import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
//import com.braintreepayments.api.models.PaymentMethodNonce;
//import com.google.android.gms.common.api.internal.ApiKey;
//
//
//public class PaymentActivity extends AppCompatActivity {
//
//    private BraintreeFragment mBraintreeFragment;
//    private EditText mEtCardNumber;
//    private EditText mEtExpirationmonth,mEtExpirationyear;
//    private EditText mEtCvv;
//    private Button mBtnPay;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
//
//
//        mEtCardNumber=findViewById(R.id.credit_card_number);
//        mEtExpirationyear=findViewById(R.id.year_edit_text);
//        mEtExpirationmonth=findViewById(R.id.month_edit_text);
//        mEtCvv=findViewById(R.id.cvv_edit_text);
//        mBtnPay=findViewById(R.id.pay_button);
//
//
//        try {
//            mBraintreeFragment = BraintreeFragment.newInstance(this, String.valueOf);
//        } catch (InvalidArgumentException e) {
//            e.printStackTrace();
//        }
//
//        mBtnPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String cardnumber=mEtCardNumber.getText().toString().trim();
//                String expiration=mEtExpirationmonth.getText().toString().trim()+"/"+mEtExpirationyear.getText().toString().trim();
//                String cvv=mEtCvv.getText().toString().trim();
//
//                if (TextUtils.isEmpty(cardnumber) || TextUtils.isEmpty(expiration) || TextUtils.isEmpty(cvv)) {
//                    Toast.makeText(PaymentActivity.this, "Please enter all payment details", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    PaymentMethodNonceCallback paymentMethodNonceCallback = new PaymentMethodNonceCallback() {
//
//                        public void onResult(PaymentMethodNonce paymentMethodNonce) {
//                            // Handle the payment result
//                            String nonce = paymentMethodNonce.getNonce();
//                            Log.d("Payment", "Payment nonce: " + nonce);
//                            Toast.makeText(PaymentActivity.this, "Payment successful", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void success(PaymentMethodNonce paymentMethodNonce) {
//
//
//                        }
//
//                        @Override
//                        public void failure(Exception e) {
//                            Log.e("Payment", "Error: " + e.getMessage());
//                            Toast.makeText(PaymentActivity.this, "Payment failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    };
//
////                     paymentRequest = new PaymentRequest()
////                            .cardNumber(cardNumber)
////                            .expirationDate(expirationDate)
////                            .cvv(cvv)
////                            .validate(true)
////                            .amount("$10.00")
////                            .options()
////                            .submitForSettlement(true)
////                            .done();
////
////
////                    mBraintreeFragment.tokenize(paymentRequest, paymentMethodNonceCallback);
//
//
//                }
//            }
//        });
//
//
//
//    }
//}