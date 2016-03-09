package newer.com.my2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2015/12/13.
 */
public class Card extends FrameLayout {

    private int num;
    private TextView lable;

    public Card(Context context) {
        super(context);

        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setGravity(Gravity.CENTER);
        lable.setBackgroundColor(0x33ffffff);

        //填充满
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(lable, lp);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            lable.setText(" ");
        } else {
            lable.setText(String.valueOf(num));
        }
    }

    public boolean equals(Card c) {
        return getNum() == c.getNum();
    }
}
