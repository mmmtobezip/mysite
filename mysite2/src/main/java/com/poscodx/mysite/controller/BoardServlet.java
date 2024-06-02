package com.poscodx.mysite.controller;

import java.util.Map;

import com.poscodx.mysite.controller.action.board.BoardAction;
import com.poscodx.mysite.controller.action.board.DeleteAction;
import com.poscodx.mysite.controller.action.board.UpdateAction;
import com.poscodx.mysite.controller.action.board.UpdateFormAction;
import com.poscodx.mysite.controller.action.board.ViewAction;
import com.poscodx.mysite.controller.action.board.WriteAction;
import com.poscodx.mysite.controller.action.board.WriteFormAction;


public class BoardServlet extends ActionServlet {
  private static final long serialVersionUID = 1L;
  
  private Map<String, Action> mapAction = Map.of(
		  "writeform", new WriteFormAction(),
		  "write", new WriteAction(),
		  "view", new ViewAction(),
		  "delete", new DeleteAction(),
		  "updateform", new UpdateFormAction(),
		  "update", new UpdateAction());
  @Override
  protected Action getAction(String actionName) {
    return mapAction.getOrDefault(actionName, new BoardAction());
    
  }
}
