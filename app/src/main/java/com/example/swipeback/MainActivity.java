package com.example.swipeback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setRecyclerViewAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setRecyclerViewAdapter() {
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View rootView = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.control_main_recycler_item, viewGroup, false);
                TextView itemText = (TextView) rootView.findViewById(R.id.item_text);
                itemText.setText(datas.get(i));
                RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(rootView) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                ((TextView) viewHolder.itemView.findViewById(R.id.item_text)).setText(datas.get(i));
            }

            @Override
            public int getItemCount() {
                return datas.size();
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private GestureDetector gestureDetector = new GestureDetector(MainActivity.this,
                    new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && gestureDetector.onTouchEvent(e)) {
                    gotoContentActivity(((TextView) childView.findViewById(R.id.item_text)).getText().toString());
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void gotoContentActivity(String content) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("content", content);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_content_slide_in, R.anim.anim_main_keep);
    }

    private RecyclerView recyclerView;

    private static ArrayList<String> datas = new ArrayList<>();
    static {
        datas.add("0");
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("5");
        datas.add("6");
        datas.add("7");
        datas.add("8");
        datas.add("9");
        datas.add("9");
        datas.add("10");
        datas.add("11");
        datas.add("12");
        datas.add("13");
        datas.add("14");
        datas.add("15");
        datas.add("16");
        datas.add("17");
        datas.add("18");
        datas.add("19");
        datas.add("20");
    }
}
