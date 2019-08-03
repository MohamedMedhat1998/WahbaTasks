package com.andalus.wahbatasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andalus.wahbatasks.database.AppDatebase;
import com.andalus.wahbatasks.database.TaskEntry;

public class DetailsActivity extends AppCompatActivity {

    private int id_to_choose_update_or_delete= 2;
    private int taskid=id_to_choose_update_or_delete;
    private AppDatebase mDb;
    Button mButton;
    EditText nameEditText;
    EditText eyeColorEditText;
    String nameData;
    String colorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mDb=AppDatebase.getInstance(getApplicationContext());
        initView();
        getDataAndPutInEditView();

    }
    private void initView()
    {
        mButton=(Button)findViewById(R.id.save_or_update);
        nameEditText=(EditText)findViewById(R.id.name_edit_text);
        eyeColorEditText=(EditText)findViewById(R.id.eye_color_edit_text);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    private void saveData()
    {
        String name=nameEditText.getText().toString().trim();
        String eyeColor=eyeColorEditText.getText().toString().trim();
        final TaskEntry task=new TaskEntry(name, eyeColor);
        AppExecuter.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(taskid==id_to_choose_update_or_delete)
                {
                    mDb.taskDao().insertTask(task);
                }
                else
                {
                    task.setId(taskid);
                    mDb.taskDao().updateTask(task);
                }
                finish();
            }
        });
    }
    private void getDataAndPutInEditView()
    {
        Intent intent=getIntent();
        if(intent !=null)
        {
            mButton.setText("update");
            if(intent.hasExtra(MainActivity.ID))
                taskid=intent.getExtras().getInt(MainActivity.ID);

        }
        AddViewModelFactory factory=new AddViewModelFactory(mDb, taskid);
        final AddTaskViewModel viewModel= ViewModelProviders.of(this, factory).get(AddTaskViewModel.class);
        viewModel.getTask().observe(this, new Observer<TaskEntry>() {
            @Override
            public void onChanged(@Nullable TaskEntry taskEntry) {
                viewModel.getTask().removeObserver(this);
                populateUI(taskEntry);
            }
        });
    }
    private void populateUI(TaskEntry task) {
        if (task == null) {
            return;
        }

        nameEditText.setText(task.getName());
        eyeColorEditText.setText(task.getEyeColor());
    }
}