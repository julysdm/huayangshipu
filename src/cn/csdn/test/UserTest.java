package cn.csdn.test;

import android.test.AndroidTestCase;
import android.util.Log;
import cn.csdn.domain.User;
import cn.csdn.service.DatabaseHelper;
import cn.csdn.service.UserService;

public class UserTest extends AndroidTestCase {
	public void datatest() throws Throwable{
		DatabaseHelper dbhepler=new DatabaseHelper(this.getContext());
		dbhepler.getReadableDatabase();
	}

	public void registerTest() throws Throwable{	
		UserService uService=new UserService(this.getContext());
		User user=new User();
		user.setUsername("drm");
		user.setPassword("123");
		user.setAge(20);
		user.setSex("Å®");
		uService.register(user);
	}
	public void loginTest() throws Throwable{
		UserService uService=new UserService(this.getContext());
		String username="drm";
		String password="123";
		boolean flag=uService.login(username, password);
		if(flag){
			Log.i("TAG","³É¹¦");
		}else{
			Log.i("TAG","Ê§°Ü");
		}
	}
	
}
