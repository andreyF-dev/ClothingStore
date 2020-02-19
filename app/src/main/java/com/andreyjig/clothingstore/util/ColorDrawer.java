package com.andreyjig.clothingstore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ColorDrawer extends View {

    private int radius;
    private int color;

    public ColorDrawer(Context context, String color) {
        super(context);
        this.color = Color.parseColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint circle = new Paint();
        circle.setFlags(Paint.ANTI_ALIAS_FLAG);
        circle.setColor(color);
        canvas.drawCircle(radius, radius, radius, circle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min( w/2, h/2);
    }
}
