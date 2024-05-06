package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    // Declare the ArrayList to store User objects
    private ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        //generate and add 20 random users to the list
        generateRandomUsers(20);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView mainimage = findViewById(R.id.mainimage);

        mainimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();}
        });
    }

    //Generate random users and add them to the userList
    private void generateRandomUsers(int numUsers) {
        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emily", "Fiona", "George", "Henry", "Isabella", "Jack"};
        String[] descriptions = {"Gamer", "Musician", "Artist", "Athlete", "Programmer", "Doctor", "Lawyer", "Teacher", "Student", "Entrepreneur"};

        for (int i = 0; i < numUsers; i++) {
            String name = names[random.nextInt(names.length)];
            String description = descriptions[random.nextInt(descriptions.length)];
            boolean followed = random.nextBoolean(); // Generate random boolean for Followed
            int randomId = generateRandomId(); // Call new method to generate ID

            User user = new User(name, description, randomId, followed);
            userList.add(user);
        }
    }

    //Generate a random unique ID (basic implementation)
    private int generateRandomId() {
        return new Random().nextInt(Integer.MAX_VALUE); // Generate a random int within integer range
    }

    // Method to show the AlertDialog
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness");

        //cancel button
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        //view button
        builder.setPositiveButton("View", (dialog, which) -> {
            // Generate a random integer
            int randomNumber = new Random().nextInt();

            // Intent to launch MainActivity
            Intent intent = new Intent(this, MainActivity.class);

            // Put the random integer as an extra in the Intent
            intent.putExtra("random_integer", randomNumber);

            startActivity(intent);
        });

        builder.show();}
}