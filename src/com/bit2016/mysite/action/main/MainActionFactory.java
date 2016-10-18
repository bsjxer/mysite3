package com.bit2016.mysite.action.main;

import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
