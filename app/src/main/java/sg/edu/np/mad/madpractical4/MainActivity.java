package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Initialize a new User object
        User user = new User("John Doe", "MAD Developer", 1, false);
        //Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);
        tvName.setText(user.name);
        tvDescription.setText(user.description);
        btnFollow.setText("Follow");



        // Access the Intent and retrieve the random integer
        Intent intent = getIntent();
        if (intent.hasExtra("random_integer")) {
            int randomNumber = intent.getIntExtra("random_integer", 0);
            TextView textView = findViewById(R.id.tvName);
            textView.setText(user.name + " " + randomNumber);
        }

        // Set OnClickListener for the button
        btnFollow.setOnClickListener(view -> {
            // Toggle the followed status
            user.toggleFollowStatus();

            // Update the button text accordingly
            btnFollow.setText(user.isFollowed() ? "Unfollow" : "Follow");

            // Show a Toast message indicating the action
            Toast.makeText(MainActivity.this, user.isFollowed() ? "Followed" : "Unfollowed", Toast.LENGTH_SHORT).show();
        });


    }
}