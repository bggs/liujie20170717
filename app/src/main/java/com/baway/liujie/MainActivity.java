package com.baway.liujie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.liujie.adapter.MyAdapter;
import com.baway.liujie.customer.MySelf;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySelf mAmountView;
    private ListView mListview;
    private List<String> list = new ArrayList<>();
    private CheckBox mCheckBox;
    private MyAdapter adapter;
    private int checkNum; // 记录选中的条目数量
    private TextView tv_show;// 用于显示选中的条目数量
    private Button mSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        adapter = new MyAdapter(this, list);
        mListview.setAdapter(adapter);
        mAmountView.setGoods_storage(50);
        mSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < list.size(); i++) {
                    MyAdapter.getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = list.size();
                // 刷新listview和TextView的显示
                dataChanged();
            }
        });

        // 绑定listView的监听器
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ViewHolder holder= (ViewHolder) parent.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(position, holder.cb.isChecked());
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                // 用TextView显示
                tv_show.setText("已选中" + checkNum + "项");
            }
        });
    }

    private void dataChanged() {
        // 通知listView刷新
        adapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tv_show.setText("已选中" + checkNum + "项");
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "巧克力");
        }
    }

    private void initView() {
        mAmountView = (MySelf) findViewById(R.id.amount_view);
        mListview = (ListView) findViewById(R.id.listview);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        tv_show = (TextView) findViewById(R.id.text);
        mSelect = (Button) findViewById(R.id.select);
    }

}
