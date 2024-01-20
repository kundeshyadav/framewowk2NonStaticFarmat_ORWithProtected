package com.eva.vtiger.marketing;

import com.evs.vtiger.util.AllGeneric;

public class MarketingAccountTextScripts extends MarketingAccountTextScriptsOR {

	
 
    public void verifyVt001SearchAccountsTestCaseStarted() {

		System.out.println("verifyVT001SearchAccounts TestCase Started");
		AllGeneric   we= new AllGeneric();
		/// username
		we.launchBrowser();
		we.openUrl(url);
		we.mySendKeys(username, "Username Textbox", "admin");
		we.mySendKeys(password, "Password Textbox", "admin");

		we.selectByText(selectcolor, "Color Dropdown", "lagoon");

		we.myClick(login_botton, "Login Button");
		we.myMouseOver(marktingTab, "Marketing Tab");
		we.myClick(AccountLickClick, "Accounts Link");
		String expectedText = "Marketing > Accounts";
		String actualText = we.myGetText(MarketingCamign, "Marketing Campaign Header");
		we.verifyString(actualText, expectedText, "Marketing");

		we.mySendKeys(searchTextbox, "Search For Textbox", "Virendra");
		we.selectByText(searchdropdown, "Search Type Dropdown", "Account Name");

	}
}
