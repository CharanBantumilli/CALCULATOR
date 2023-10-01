package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton ac,c,per,div,seven,eight,nine;
    MaterialButton four,five,six,mul,sub,add,one;
    MaterialButton two,three,zero,dot,result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        idassin(ac,R.id.ac);
        idassin(c,R.id.c);
        idassin(per,R.id.per);
        idassin(div,R.id.div);
        idassin(seven,R.id.seven);
        idassin(eight,R.id.eight);
        idassin(nine,R.id.nine);
        idassin(four,R.id.four);
        idassin(five,R.id.five);
        idassin(six,R.id.six);
        idassin(mul,R.id.mul);
        idassin(sub,R.id.sub);
        idassin(add,R.id.add);
        idassin(one,R.id.one);
        idassin(two,R.id.two);
        idassin(three,R.id.three);
        idassin(zero,R.id.zero);
        idassin(dot,R.id.dot);
        idassin(result,R.id.result);
    }

    void idassin(MaterialButton btc , int id){
        btc = findViewById(id);
        btc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String setbutton = button.getText().toString();
        String data = solutionTv.getText().toString();

        if (setbutton.equals("ac")){
            solutionTv.setText("");
            resultTv.setText("");
            return;
        }
        if(setbutton.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (setbutton.equals("c")){
            data = data.substring(0,data.length()-1);

        }else {
            data = data+setbutton;
        }
        solutionTv.setText(data);

        String finalans = getresult(data);

        if (!finalans.equals("Err")){
            resultTv.setText(finalans);
        }

    }
    String getresult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;
        }catch (Exception e){
            return "Err";
        }
    }
}