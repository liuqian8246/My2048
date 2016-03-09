package newer.com.my2048;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/12/13.
 */
public class GameView extends GridLayout {

    private int w;
    private int h;
    private Card[][] cardMap = new Card[4][4];
    private List<Point> cards = new ArrayList<>();

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
//        startGame();

        setOnTouchListener(new OnTouchListener() {
            float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        Log.d("起始位置", String.valueOf(startX));
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("起始位置", String.valueOf(event.getX()));
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < 0) {
                                turnLeft();
                            } else {
                                turnRight();
                            }
                        } else {
                            if (offsetY > 0) {
                                turnDown();
                            } else {
                                turnUp();
                            }
                        }
                        break;
                }

                return true;
            }

        });
    }

    private void turnDown() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardMap[x][y1].getNum() != 0) {
                        if (cardMap[x][y].getNum() == 0) {
                            //当前位置等于0，交换数值
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            //交换后又重原来的位置开始判断
                            y++;
                            merge = true;
                            //找到交换的就退出
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            //当前位置不等于0，如果相等就合并
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
        }
    }

    private void turnUp() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardMap[x][y1].getNum() != 0) {
                        if (cardMap[x][y].getNum() == 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
        }
    }

    private void turnRight() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardMap[x1][y].getNum() != 0) {
                        if (cardMap[x][y].getNum() == 0) {
                            //当前位置等于0，交换数值
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            //交换后又重原来的位置开始判断
                            x++;
                            merge = true;
                            //找到交换的就退出
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            //当前位置不等于0，如果相等就合并
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
        }
    }

    private void turnLeft() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardMap[x1][y].getNum() != 0) {
                        if (cardMap[x][y].getNum() == 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            merge = true;
                            x--;
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
        }
    }

    public void addCards(int cardW, int cardH) {
        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, cardW, cardH);
                cardMap[x][y] = c;
            }
        }
    }

    private void addRandom() {
        cards.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardMap[x][y].getNum() <= 0) {
                    cards.add(new Point(x, y));
                }
            }
        }
        Point t = cards.remove((int) (Math.random() * cards.size()));
        cardMap[t.x][t.y].setNum(Math.random() > 0.1 ? 2 : 4);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = ((w < h ? w : h) - 10) / 4;
        addCards(cardWidth, cardWidth);
    }

    public void startGame() {
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardMap[x][y].setNum(0);
            }
        }

        addRandom();
        addRandom();
    }
}
