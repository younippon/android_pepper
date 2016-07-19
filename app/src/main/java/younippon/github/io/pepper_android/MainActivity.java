package younippon.github.io.pepper_android;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.sdk.object.actuation.Actuation;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.actuation.Frame;
import com.aldebaran.qi.sdk.object.actuation.GoTo;
import com.aldebaran.qi.sdk.object.geometry.Quaternion;
import com.aldebaran.qi.sdk.object.geometry.Transform;
import com.aldebaran.qi.sdk.object.geometry.Vector3;
import com.aldebaran.qi.sdk.object.interaction.Say;

/**
 * Created by yo on 16/07/19.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonHello = (Button)findViewById(R.id.button_hello);
        buttonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHello();
            }
        });

        Button buttonTurn = (Button)findViewById(R.id.button_go);
        buttonTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goStraight();
            }
        });

        Button buttonGorilla = (Button)findViewById(R.id.button_gorilla);
        buttonGorilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mimicGorilla();
            }
        });
    }

    private void sayHello() {
        Say say = new Say(this);
        say.run("Hello, World!");
    }

    private void goStraight() {
        Quaternion r = new Quaternion(0, 0, 0, 1);
        Vector3 t = new Vector3(1, 0, 0);
        Transform tf = new Transform(r, t);
        Frame robotFrame = Actuation.get(this).robotFrame();
        Frame robotAtStart = robotFrame.makeDetachedFrame(System.currentTimeMillis());
        Frame targetFrame = robotAtStart.makeStaticChildFrame(tf);

        new GoTo(this).run(targetFrame);
    }

    private void mimicGorilla() {
        Animation animation = Animation.fromResources(this, R.raw.gorilla_b001);
        Animate animate = new Animate(this);
        animate.run(animation);
    }
}
