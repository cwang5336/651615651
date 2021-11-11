package com.codepath.rkpandey.a651615651;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.rkpandey.a651615651.Adapters.ChatAdapter;
import com.codepath.rkpandey.a651615651.Models.MessageModel;
import com.codepath.rkpandey.a651615651.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    public static final String TAG = "ChatDetailActivity";

    ActivityChatDetailBinding binding;

    FirebaseDatabase database;
    FirebaseAuth auth;

    // For custom toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());

        //the cursed line
        setContentView(binding.getRoot());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String recieveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        binding.userName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.user).into(binding.profileImage);

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
                startActivity(intent);
                Log.d("ChatDetailAct", "Clicking Arrow Button.");
            }
        });



        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final ChatAdapter chatAdapter = new ChatAdapter(messageModels, this);
        binding.chatRecyclarView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclarView.setLayoutManager(layoutManager);


        final String senderRoom = senderId + recieveId;
        final String receiverRoom = recieveId + senderId;

        // something wrong with this part.
        //fix this
        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        messageModels.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                            MessageModel model = snapshot1.getValue(MessageModel.class);
                            messageModels.add(model);
                        }

                        chatAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        ImageView send = (ImageView) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etMessage = findViewById(R.id.etMessage);
                Log.d("ChatDetailAct", "Clicking Arrow Button.");

                String message = etMessage.getText().toString();
               final MessageModel model = new MessageModel(senderId, message);
               model.setTimestamp(new Date().getTime());
               etMessage.setText("");

               database.getReference().child("chats")
                       .child(senderRoom)
                       .push()
                       .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       database.getReference().child("chats")
                               .child(receiverRoom)
                               .push()
                               .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                           public void onSuccess(Void unused) {

                           }
                       });
                  }
               });

            }
        });

//        ImageView ivSend = (ImageView) findViewById(R.id.send);
//        ivSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("ChatDetailAct", "message sent");
//
//                String message = binding.etMessage.getText().toString();
//               final MessageModel model = new MessageModel(senderId, message);
//               model.setTimestamp(new Date().getTime());
//               binding.etMessage.setText("");
//
//               database.getReference().child("chats")
//                       .child(senderRoom)
//                       .push()
//                       .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
//                   @Override
//                   public void onSuccess(Void unused) {
//                       database.getReference().child("chats")
//                               .child(receiverRoom)
//                               .push()
//                               .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
//                           @Override
//                           public void onSuccess(Void unused) {
//
//                           }
//                       });
//                   }
//               });
//
//            }
//        });



    }
}