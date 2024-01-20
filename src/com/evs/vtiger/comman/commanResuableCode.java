package com.evs.vtiger.comman;

import com.evs.vtiger.util.AllGeneric;

public class commanResuableCode extends commanResualbleCodeOR {
	

	public void goToHomePage() {
		AllGeneric we=	new  AllGeneric();
		          
		we.myBrowserLounch(myBrowserLounch, "chrome");
		we.mySendKeys(username,"username", "admin");
		we.mySendKeys(userpassword,"password", "admin");
	    we.myClick(login, "login");

	}

}