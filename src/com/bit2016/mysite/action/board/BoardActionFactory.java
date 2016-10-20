package com.bit2016.mysite.action.board;

import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName){
		Action action = null;

		if ("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteAction();
		} else if ("viewform".equals(actionName)) {
			action = new ViewFormAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("commentform".equals(actionName)) {
			action = new CommentFormAction();
		} else if ("comment".equals(actionName)) {
			action = new CommentAction();
		} else {
			action = new ListAction();
		}
		return action;
	}

}
