package williamking.colorblender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

/**
 *  Assistance and Code Snippets acquired from Android's Developer Website
 *
 *  @author William King
 */
public class MainActivity extends AppCompatActivity {

    // Variables

    // Colors
    int firstBlendingColor;
    int secondBlendingColor;
    int blendedColor;

    // SurfaceViews
    SurfaceView firstColorDisplay;
    SurfaceView secondColorDisplay;
    SurfaceView blendedColorDisplay;

    // SeekBar
    SeekBar mixingBar;
    int barMax = 100000;

    // Overidden Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set initial background color values for all three surface views
        firstBlendingColor = Color.rgb(0,0,0);
        secondBlendingColor = Color.rgb(255,255,255);
        blendedColor = Color.rgb(0,0,0);

        firstColorDisplay = (SurfaceView) findViewById(R.id.viewColorToBlend1);
        secondColorDisplay = (SurfaceView) findViewById(R.id.viewColorToBlend2);
        blendedColorDisplay =(SurfaceView) findViewById(R.id.viewBlendedColor);

        firstColorDisplay.setBackgroundColor(firstBlendingColor);
        secondColorDisplay.setBackgroundColor(secondBlendingColor);
        blendedColorDisplay.setBackgroundColor(firstBlendingColor);


        // Establish listener for seeker bar
        mixingBar = (SeekBar) findViewById(R.id.blenderBar);
        mixingBar.setMax(barMax);

        mixingBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userChange) {
                blendedColor = Color.rgb(
                        (int) ( ( Color.red(firstBlendingColor)
                                * ( (float)( barMax - progress) / barMax ) )
                                + ( Color.red(secondBlendingColor)
                                * ( (float)progress / barMax ) ) ),
                        (int) ( ( Color.green(firstBlendingColor)
                                * ( (float)( barMax - progress) / barMax ) )
                                + ( Color.green(secondBlendingColor)
                                * ( (float)progress / barMax ) ) ),
                        (int) ( ( Color.blue(firstBlendingColor)
                                * ( (float)( barMax - progress) / barMax ) )
                                + ( Color.blue(secondBlendingColor)
                                * ( (float)progress / barMax ) ) ) );

                Log.i("onProgressChanged","mixColor1: " + firstBlendingColor
                        + ", mixColor2: " + secondBlendingColor
                        + ", blendedColor: " + blendedColor
                        + ", progress: " + progress);

                blendedColorDisplay.setBackgroundColor(blendedColor);
            } // onProgressChanged

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Required to be overridden. Not used.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Required to be overridden. Not used.
            }
        });
    } // onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // onCreateOptionsMenu

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
    } // onOptionsItemSelected

} // MainActivity
