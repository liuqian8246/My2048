package newer.com.my2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        mainActivity = this;
    }

    /**
     * 定义四个方向
     */
    //左边
    public static final int LEFT = 1;
    //右边
    public static final int RIGHT = 1;
    //上边
    public static final int UP = 1;
    //下边
    public static final int DOWN = 1;


    /**
     * 手势滑动
     */
    GestureDetector gestureDetector;
//    MyListener listener;
    GameView gameView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        MyListener listener = new MyListener();
//        gestureDetector = new GestureDetector(this, listener);
        gameView = (GameView) findViewById(R.id.gameView);
        textView = (TextView) findViewById(R.id.textView_score);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void start(View view) {
        gameView.startGame();
    }

//    class MyListener extends GestureDetector.SimpleOnGestureListener {
//        /**
//         * 滑动(distanceX distanceY是起点减终点)
//         *
//         * @param e1        起点
//         * @param e2        终点
//         * @param distanceX X距离
//         * @param distanceY Y距离
//         * @return true 事件处理完毕(消费)
//         */
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            if (Math.abs(distanceX) >= Math.abs(distanceY)) {
//                if (distanceX > 0) {
//                    Log.d("方向", "左边");
//                } else {
//                    Log.d("方向", "右");
//                }
//            } else if (distanceY > 0) {
//                Log.d("方向", "上");
//            } else {
//                Log.d("方向", "下边");
//            }
//            return true;
//        }
//    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        textView.setText(String.valueOf(score));
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }


    private int score = 0;
    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
