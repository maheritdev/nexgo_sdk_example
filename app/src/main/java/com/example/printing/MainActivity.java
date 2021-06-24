package com.example.printing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nexgo.oaf.apiv3.APIProxy;
import com.nexgo.oaf.apiv3.DeviceEngine;
import com.nexgo.oaf.apiv3.device.printer.AlignEnum;
import com.nexgo.oaf.apiv3.device.printer.BarcodeFormatEnum;
import com.nexgo.oaf.apiv3.device.printer.OnPrintListener;
import com.nexgo.oaf.apiv3.device.printer.Printer;

public class MainActivity extends AppCompatActivity {

    public DeviceEngine deviceEngine;
    private Button print;
    Printer printer;
    static String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(System.getProperty("java.library.path"));
        Log.e("libraryPath", System.getProperty("java.library.path"));

        deviceEngine = APIProxy.getDeviceEngine(MainActivity.this);
        print = findViewById(R.id.print);

        print.setText("print with : " + (Build.MANUFACTURER +" "+ Build.MODEL));

        Log.e(
                "device",
                "\n"+deviceEngine.getDeviceInfo().getModel()
        );
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print();
            }
        });

    }


    public void print() {
        try {
            String[] arr;

            printer = deviceEngine.getPrinter();
            printer.setTypeface(Typeface.DEFAULT);
            printer.initPrinter();
            printer.setTypeface(Typeface.DEFAULT);
            printer.setLetterSpacing(5);
            printer.appendPrnStr(getString(R.string.print_merchantname), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_merchantno), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_terminalno), getString(R.string.print_operator), 24, false);
            printer.appendPrnStr(getString(R.string.print_issurebank), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_shoudan), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_expiredate), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.cardnum), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_cardinfo), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_tradetype), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_batchno), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_voucher), getString(R.string.print_authorcode), 24, false);
            printer.appendPrnStr(getString(R.string.print_refrenceno), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_tradedate), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_amount), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.money), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_beizhu), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_originalvoucher), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_addinfo), 24, AlignEnum.LEFT, false);
            printer.appendBarcode(getString(R.string.print_barcode), 50, 0, 2, BarcodeFormatEnum.CODE_128, AlignEnum.CENTER);
            printer.appendQRcode(getString(R.string.print_qrcode), 200, AlignEnum.CENTER);
            printer.appendPrnStr("---------------------------", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_cardhold), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr("\n", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr("\n", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr("\n", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr("---------------------------", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_cardhold_ensure), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_cardhold_ensure), 24, AlignEnum.LEFT, false);
            printer.appendPrnStr("---------------------------", 24, AlignEnum.LEFT, false);
            printer.appendPrnStr(getString(R.string.print_merchant_dan), 24, AlignEnum.RIGHT, false);
            printer.startPrint(false, new OnPrintListener() {
                @Override
                public void onPrintResult(final int retCode) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, retCode + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}