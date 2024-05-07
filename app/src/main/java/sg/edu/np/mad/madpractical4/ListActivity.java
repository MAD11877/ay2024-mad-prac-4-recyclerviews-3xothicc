package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Random;

import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create a list of 20 random users
        ArrayList<User> myUser_List = new ArrayList<>();
        for (int i =0; i < 20; i++){
            int name = new Random().nextInt(999999999);
            int description = new Random().nextInt(999999999);
            boolean followed = new Random().nextBoolean();

            User user = new User("John Doe", "MAD Developer", 1, false);
            user.setName("Name"+String.valueOf(name));
            user.setDescription("Description"+String.valueOf(description));
            user.setFollowed(followed);
            myUser_List.add(user);
        }

        //add recyclerview
        UserAdapter userAdapter= new UserAdapter(myUser_List, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

        ImageView smallimage = findViewById(R.id.SmallImage);

        smallimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();}
        });
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