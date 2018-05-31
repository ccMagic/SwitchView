package io.github.ccmagic.switchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ONE = "ONE";
    public static final String TWO = "TWO";
    public static final String THREE = "THREE";

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwitchView switchView = findViewById(R.id.sw_transfer_use_car);
        mView = findViewById(R.id.view);
        //
        List<SwitchView.SwitchBean> list = new ArrayList<>();
        //添加状态项，无限制
        final SwitchView.SwitchBean switchBean1 = new SwitchView.SwitchBean();
        switchBean1.setName(ONE);
        list.add(switchBean1);
        final SwitchView.SwitchBean switchBean2 = new SwitchView.SwitchBean();
        switchBean2.setName(TWO);
        list.add(switchBean2);
        final SwitchView.SwitchBean switchBean3 = new SwitchView.SwitchBean();
        switchBean3.setName(THREE);
        list.add(switchBean3);
        //
        switchView.setOnStatusChangeListener(new SwitchView.OnStatusChangeListener() {
            @Override
            public void statusChange(String status) {
                switchStatus(status);
            }
        });
        switchView.setAllStatusList(list);
        //设置默认选项
        switchView.setIndex(0);
    }

    private void switchStatus(String status) {
        Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
        switch (status) {
            case ONE:
                mView.setBackgroundResource(R.color.orangeRed);
                break;
            case TWO:
                mView.setBackgroundResource(R.color.violet);
                break;
            case THREE:
                mView.setBackgroundResource(R.color.mediumAquamarine);
                break;
            default:
                break;
        }
    }
}
