package com.example.a;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Tab2 extends Fragment {

	  private ToDoDB myToDoDB;
	  private Cursor myCursor;
	  private ListView myListView;
	  private EditText myEditText;
	  private Context context;
	  private int _id;
	  protected final static int MENU_ADD = Menu.FIRST;
	  protected final static int MENU_EDIT = Menu.FIRST + 1;
	  protected final static int MENU_DELETE = Menu.FIRST + 2;

	  public boolean onOptionsItemSelected(MenuItem item)
	  {
	    super.onOptionsItemSelected(item);
	    switch (item.getItemId())
	    {
	      case MENU_ADD:
	        this.addTodo();
	        break;
	      case MENU_EDIT:
	        this.editTodo();
	        break;
	      case MENU_DELETE:
	        this.deleteTodo();
	        break;
	    }
	    return true;
	  }

	  public boolean onCreateOptionsMenu(Menu menu)
	  {
	    super.onCreateOptionsMenu(menu, null);
	    menu.add(Menu.NONE, MENU_ADD, 0, R.string.strAddButton);
	    menu.add(Menu.NONE, MENU_EDIT, 0, R.string.strEditButton);
	    menu.add(Menu.NONE, MENU_DELETE, 0, R.string.strDeleteButton);

	    return true;
	  }

	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
	  {
//	    super.onCreate(savedInstanceState);
//	    setContentView(R.layout.activity_tab2);
	    View tab2= inflater.inflate(R.layout.activity_tab2, container,false);

	    myListView = (ListView) tab2.findViewById(R.id.myListView);
	    myEditText = (EditText) tab2.findViewById(R.id.myEditText);

	    myToDoDB = new ToDoDB(this.context);
	    /* 取得DataBase里的数据 */
	    myCursor = myToDoDB.select();

	    /* new SimpleCursorAdapter并将myCursor传入，
	       显示数据的字段为todo_text */
	    SimpleCursorAdapter adapter = 
	    new SimpleCursorAdapter
	    (this.context, R.layout.activity_to_do_db, myCursor, new String[]
	        { ToDoDB.FIELD_TEXT }, new int[]
	        { R.id.listTextView1 });
	    myListView.setAdapter(adapter);

	    /* 将myListView添加OnItemClickListener */
	    myListView.setOnItemClickListener
	    (new AdapterView.OnItemClickListener()
	    {

	      public void onItemClick
	      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
	        /* 将myCursor移到所点击的值 */
	        myCursor.moveToPosition(arg2);
	        /* 取得字段_id的值 */
	        _id = myCursor.getInt(0);
	        /* 取得字段todo_text的值 */
	        myEditText.setText(myCursor.getString(1));
	      }

	    });
	    myListView.setOnItemSelectedListener
	    (new AdapterView.OnItemSelectedListener()
	    {
	      public void onItemSelected
	      (AdapterView<?> arg0, View arg1, int arg2, long arg3)
	      {
	        /* getSelectedItem所取得的是SQLiteCursor */
	        SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
	        _id = sc.getInt(0);
	        myEditText.setText(sc.getString(1));
	      }
	      
	      public void onNothingSelected(AdapterView<?> arg0)
	      {
	      }
	    });
	    return tab2;
	  }
	  
	  private void addTodo()
	  {
	    if (myEditText.getText().toString().equals(""))
	      return;
	    /* 添加数据到数据库 */
	    myToDoDB.insert(myEditText.getText().toString());
	    /* 重新查询 */
	    myCursor.requery();
	    /* 重新整理myListView */
	    myListView.invalidateViews();
	    myEditText.setText("");
	    _id = 0;
	  }

	  private void editTodo()
	  {
	    if (myEditText.getText().toString().equals(""))
	      return;
	    /* 修改数据 */
	    myToDoDB.update(_id, myEditText.getText().toString());
	    myCursor.requery();
	    myListView.invalidateViews();
	    myEditText.setText("");
	    _id = 0;
	  }

	  private void deleteTodo()
	  {
	    if (_id == 0)
	      return;
	    /* 删除数据 */
	    myToDoDB.delete(_id);
	    myCursor.requery();
	    myListView.invalidateViews();
	    myEditText.setText("");
	    _id = 0;
	  	}
		public void onAttach(Activity activity){
			super.onAttach(activity);
			this.context=(MainActivity)activity;
		}
	  }