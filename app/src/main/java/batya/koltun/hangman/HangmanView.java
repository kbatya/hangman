package batya.koltun.hangman;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
// class for drawing the hangman
//and the gallows on the screen
//used in MainActivity as a view on the activity_main.xml
public class HangmanView extends View {
    private int sizeRhead = 100;
    private int distXL = 200;
    private int distYL = 100;
    private int distXR = 500;
    private int distYR = 1000;
    private int attemptsLeft;

    public HangmanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        attemptsLeft = 6;
    }

    public void setAttemptsLeft(int attempts) {
        attemptsLeft = attempts;
        invalidate(); // Redraw the view with the updated attempts left
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        drawGallows(paint,canvas,200,100,400,900  );
        drawHangman(paint,canvas,200,100,400,900  );
    }
    protected void drawHangman(Paint paint, Canvas canvas, int x, int y, int width, int height) {
        paint.setColor(Color.GREEN);

        int ropeBottom = y + height / 5;  // Bottom of the rope (from drawGallows method)
        int headCenterX = x + width;
        int headCenterY = ropeBottom + 80;  // Center of head 80 pixels below rope
        int bodyTopY = headCenterY + 80;
        int bodyBottomY = y + height - height / 3;  // Raise the bottom of the body

        if (attemptsLeft < 6) {
            // Draw the head
            canvas.drawCircle(headCenterX, headCenterY, 80, paint);
        }
        if (attemptsLeft < 5) {
            // Draw the body
            canvas.drawLine(headCenterX, bodyTopY, headCenterX, bodyBottomY, paint);
        }
        if (attemptsLeft < 4) {
            // Draw the left arm
            canvas.drawLine(headCenterX, bodyTopY + 40, headCenterX - width/3, bodyTopY + height/6, paint);
        }
        if (attemptsLeft < 3) {
            // Draw the right arm
            canvas.drawLine(headCenterX, bodyTopY + 40, headCenterX + width/3, bodyTopY + height/6, paint);
        }
        if (attemptsLeft < 2) {
            // Draw the left leg
            canvas.drawLine(headCenterX, bodyBottomY, headCenterX - width/4, bodyBottomY + height/6, paint);
        }
        if (attemptsLeft < 1) {
            // Draw the right leg
            canvas.drawLine(headCenterX, bodyBottomY, headCenterX + width/4, bodyBottomY + height/6, paint);
        }
    }

    protected void drawGallows(Paint paint,Canvas canvas,int x,int y,int width,int height)
    {
         // Outline only, no fill
        paint.setColor(Color.parseColor("#A52A2A"));// Brown color
        // Draw the gallows
        // Draw the gallows
        canvas.drawLine(x, y, x, y + height, paint); // vertical
        canvas.drawLine(x, y, x + width, y, paint); // horizontal top
        canvas.drawLine(x + width, y, x + width, y + height / 5, paint); // rope
        canvas.drawLine(x-width/2 , y + height , x + width/2, y+height, paint); // base
    }
}
