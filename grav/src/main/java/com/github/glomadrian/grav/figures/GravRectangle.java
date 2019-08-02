package com.github.glomadrian.grav.figures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.Random;

public class GravRectangle extends Grav {

    private static final int NEUTRAL_VARIANCE = 0;

    private final float width;
    private final float height;

    private float trapeziumBottomHeightVar = NEUTRAL_VARIANCE;
    private float trapeziumTopHeightVar = NEUTRAL_VARIANCE;
    private float trapeziumBottomWidthVar = NEUTRAL_VARIANCE;
    private float trapeziumTopWidthVar = NEUTRAL_VARIANCE;

    private final Random generator = new Random();

    public GravRectangle(PointF startPoint, Paint paint, float width, float height) {
        super(startPoint, paint);
        this.width = width;
        this.height = height;
    }

    public GravRectangle(PointF startPoint, Paint paint, float width, float height, float trapeziumHeightVar, float trapeziumWidthVar) {
        this(startPoint, paint, width, height);
        this.trapeziumBottomHeightVar = generateSizeWithBoundary(trapeziumHeightVar);
        this.trapeziumTopHeightVar = generateSizeWithBoundary(trapeziumHeightVar);
        this.trapeziumBottomWidthVar = generateSizeWithBoundary(trapeziumWidthVar);
        this.trapeziumTopWidthVar = generateSizeWithBoundary(trapeziumWidthVar);
    }

    @Override
    protected void draw(Canvas canvas, PointF drawPoint) {

        float rightTopCornerX = drawPoint.x + width - trapeziumTopWidthVar;
        float rightTopCornerY = drawPoint.y - trapeziumTopHeightVar;

        float bottomLeftCornerX = drawPoint.x - trapeziumBottomWidthVar;
        float bottomLeftCornerY = drawPoint.y + height - trapeziumBottomHeightVar;

        float rightBottomCornerX = drawPoint.x + width - trapeziumBottomWidthVar;
        float rightBottomCornerY = drawPoint.y + height - trapeziumBottomHeightVar;

        Path path = new Path();

        path.moveTo(drawPoint.x, drawPoint.y);
        path.lineTo(rightTopCornerX, rightTopCornerY);
        path.lineTo(rightBottomCornerX, rightBottomCornerY);
        path.lineTo(bottomLeftCornerX, bottomLeftCornerY);
        path.lineTo(drawPoint.x, drawPoint.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    private float generateSizeWithBoundary(float boundary) {
        float result = NEUTRAL_VARIANCE;
        if(boundary != NEUTRAL_VARIANCE) {
            float wideRange = 2 * boundary;

            float generated = (generator.nextFloat() * wideRange) % wideRange;
            result = (boundary - generated) % boundary;
        }
        return result;
    }
}
