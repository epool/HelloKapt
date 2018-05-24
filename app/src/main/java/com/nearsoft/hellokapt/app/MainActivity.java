package com.nearsoft.hellokapt.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nearsoft.hellokapt.app.models.AnnotatedClassBuilder;

public class MainActivity extends AppCompatActivity {

    // TODO: Un-comment the following line to replicate the issue
    // TODO: https://stackoverflow.com/questions/50493424/kotlin-kapt-generated-kotlin-class-is-not-recognized-as-class-member-but-it-doe
//    AnnotatedClassBuilder builder = new AnnotatedClassBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        textView.setText(new AnnotatedClassBuilder().getMessage());
    }

}
