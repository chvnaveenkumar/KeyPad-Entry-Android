package com.example.s530742.chandaluri_assignment03;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ImageButton imgOne,imgTwo,imgThree, imgFour,imgFive,imgSix,imgSeven,imgEight,imgNine,imgZero,imgOpen,imgReset;
    TextView pin,status,attempts;
    ImageView imgview2;
    EditText new_pin;
    Button changePin,button2;
    int count = 0;
    String sec_num ="",pin_number="JIEEB";
    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Success!!");
        alertDialog.setMessage("Welcome !!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        new_pin.setText("");
                    }
                });
        alertDialog.show();

        imgOne = (ImageButton)findViewById(R.id.imageButton1);
        imgTwo = (ImageButton) findViewById(R.id.imageButton2);
        imgThree = (ImageButton) findViewById(R.id.imageButton3);
        imgFour = (ImageButton) findViewById(R.id.imageButton4);
        imgFive = (ImageButton) findViewById(R.id.imageButton5);
        imgSix = (ImageButton) findViewById(R.id.imageButton6);
        imgSeven = (ImageButton) findViewById(R.id.imageButton7);
        imgEight = (ImageButton) findViewById(R.id.imageButton8);
        imgNine = (ImageButton) findViewById(R.id.imageButton9);
        imgZero = (ImageButton) findViewById(R.id.imageButton11);
        imgOpen = (ImageButton) findViewById(R.id.imageButton15);
        imgReset = (ImageButton) findViewById(R.id.imageButton17);
        pin = (TextView) findViewById(R.id.pin);
        status = (TextView) findViewById(R.id.textView);
        attempts = (TextView) findViewById(R.id.attempts);
        imgview2 = (ImageView)findViewById(R.id.imageView2);
        cl = (ConstraintLayout)findViewById(R.id.SecureText);
        changePin = (Button)findViewById(R.id.changePin);
        new_pin = (EditText)findViewById(R.id.newPin);
        button2 = (Button)findViewById(R.id.button2);
        changePin.setEnabled(false);
        new_pin.setEnabled(false);

        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new_pin.getText().length() == 5)
                {
                    int numb = Integer.parseInt(new_pin.getText().toString());
                    System.out.println("hello "+numb);
                    int numbs[]=new int[5];
                    String changed_new ="";
                    numbs[4] = numb%10;
                    numbs[3] = (numb/10)%10;
                    numbs[2] = (numb/100)%10;
                    numbs[1] = (numb/1000)%10;
                    numbs[0] = (numb/10000)%10;
                    for(int i=0;i<5;i++)
                    {
                        int n=numbs[i];
                        switch (n)
                        {
                            case 1: changed_new+="B";break;
                            case 2: changed_new+="C";break;
                            case 3: changed_new+="D";break;
                            case 4: changed_new+="E";break;
                            case 5: changed_new+="F";break;
                            case 6: changed_new+="G";break;
                            case 7: changed_new+="H";break;
                            case 8: changed_new+="I";break;
                            case 9: changed_new+="J";break;
                            case 0: changed_new+="K";break;
                        }
                    }
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Success!!");
                    alertDialog.setMessage("Changed Pin Scuccessfully!!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    new_pin.setText("");
                                }
                            });
                    alertDialog.show();
                    pin_number=changed_new;

                }else
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Pin Number Should be 5 Numbers");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        imgOpen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                System.out.println("Secpin "+sec_num+" "+pin_number);
                if(pin.getText().toString().length() != 5) {
                    attempts.setText("Pin should be 5 digits!!");
                    attempts.setTextColor(Color.MAGENTA);
                }else if(sec_num.equals(pin_number))
                {
                    status.setText("UnLocked");
                    attempts.setTextColor(Color.BLUE);
                    count+=1;
                    attempts.setText("No of Attempts:"+count);
                    status.setTextColor(Color.BLUE);
                    pin.setText("");
                    sec_num="";
                    count = 0;
                    imgview2.setImageResource(R.drawable.unlocked);
                    cl.setBackgroundColor(Color.GREEN);
                    changePin.setEnabled(true);
                    new_pin.setEnabled(true);
                }else
                {
                    status.setText("Locked");
                    count +=1;
                    attempts.setTextColor(Color.BLUE);
                    status.setTextColor(Color.BLUE);
                    attempts.setText("Wrong Pin! No of Attempst "+count);
                    cl.setBackgroundColor(Color.MAGENTA);
                    pin.setText("");
                    sec_num="";
                    if(count == 3)
                    {
                        new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                attempts.setText("Status InActive! "+ millisUntilFinished / 1000+" seconds remaining");
                                attempts.setTextColor(Color.BLUE);
                                pin.setText("");
                                imgOne.setEnabled(false);
                                imgTwo.setEnabled(false);
                                imgThree.setEnabled(false);
                                imgFour.setEnabled(false);
                                imgFive.setEnabled(false);
                                imgSix.setEnabled(false);
                                imgSeven.setEnabled(false);
                                imgEight.setEnabled(false);
                                imgNine.setEnabled(false);
                                imgZero.setEnabled(false);
                                imgReset.setEnabled(false);
                                imgOpen.setEnabled(false);
                                button2.setEnabled(false);
                            }

                            public void onFinish() {
                                attempts.setText("Status Active!");
                                attempts.setTextColor(Color.BLUE);
                                count = 0;
                                imgOne.setEnabled(true);
                                imgTwo.setEnabled(true);
                                imgThree.setEnabled(true);
                                imgFour.setEnabled(true);
                                imgFive.setEnabled(true);
                                imgSix.setEnabled(true);
                                imgSeven.setEnabled(true);
                                imgEight.setEnabled(true);
                                imgNine.setEnabled(true);
                                imgZero.setEnabled(true);
                                imgReset.setEnabled(true);
                                imgOpen.setEnabled(true);
                                button2.setEnabled(true);
                            }
                        }.start();
                    }
                }
            }
        });

        imgReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                count = 0;
                pin.setText("");
                attempts.setTextColor(Color.BLUE);
                attempts.setText("Status Active! No of Attempts:"+count);
                status.setTextColor(Color.BLUE);
                status.setText("LOCKED");
                pin_number="JIEEB";
                imgview2.setImageResource(R.drawable.locked);
                cl.setBackgroundColor(Color.RED);
                changePin.setEnabled(false);
                new_pin.setEnabled(false);

            }});
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                count = 0;
                pin.setText("");
                attempts.setTextColor(Color.BLUE);
                attempts.setText("Status Active! No of Attempts:"+count);
                status.setTextColor(Color.BLUE);
                status.setText("LOCKED");
                imgview2.setImageResource(R.drawable.locked);
                cl.setBackgroundColor(Color.RED);
                changePin.setEnabled(false);
                new_pin.setEnabled(false);

            }});

        imgOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"1");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "B";
                }
            }
        });
        imgTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"2");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "C";
                }
            }
        });
        imgThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"3");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "D";
                }
            }
        });
        imgFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"4");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "E";
                }
            }
        });
        imgFive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"5");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "F";
                }
            }
        });
        imgSix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"6");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "G";
                }
            }
        });
        imgSeven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"7");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "H";
                }
            }
        });
        imgEight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"8");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "I";
                }
            }
        });
        imgNine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"9");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "J";
                }
            }
        });
        imgZero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(pin.getText().length()<5) {
                    if(pin.getText().length()==4)
                        pin.setText(pin.getText()+"0");
                    else
                        pin.setText(pin.getText() + "*");
                    sec_num += "K";
                }
            }
        });
    }
}
