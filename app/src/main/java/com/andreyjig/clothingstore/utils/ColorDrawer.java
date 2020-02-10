package com.andreyjig.clothingstore.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ColorDrawer extends View {

    private int height;
    private int width;
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
        int radius = Math.min(width, height);
        canvas.drawCircle(radius, radius, radius, circle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w/2;
        height = h/2;
    }
}
