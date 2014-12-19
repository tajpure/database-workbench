package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;

public class ManageValueAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public String execute() {
		
		return "";
	}
	
	public String save() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		User user = (User) map.get("user");
		DatabaseMetaDataWorker worker = factory.getWorker(user);
		worker.updateValues(user, curTable, ListToNestingList(oldList), ListToNestingList(newList));
		worker.drop();
		return "success";
	}
	
	public String delete() {
		return "";
	}
	
	public List<List<Object>> ListToNestingList(List<String[]> list) {
		List<List<Object>> nestingList = new ArrayList<List<Object>>();
		List<Object> tempList = new ArrayList<Object>();
		for (String[] obj : list) {
			if ("{~_~}".equals(StringArrayToString(obj))) {
				if (tempList.size() != 0) {
					nestingList.add(tempList);
					tempList = new ArrayList<Object>();
				}
				continue;
			}
			tempList.add(StringArrayToString(obj));
		}
		return nestingList;
	}
	
	public String StringArrayToString(String[] array) {
		String result = "";
		for (String tmp : array) {
			result = result + tmp;
		}
		return result;
	}

	private List<String[]> newList = new ArrayList<String[]>();

	private List<String[]> oldList = new ArrayList<String[]>();
	
	private List<String> insertObj = new ArrayList<String>();
	
	private Table curTable = new Table();
	
	public List<String[]> getNewList() {
		return newList;
	}

	public void setNewList(List<String[]> newList) {
		this.newList = newList;
	}

	public Table getCurTable() {
		return curTable;
	}

	public void setCurTable(Table curTable) {
		this.curTable = curTable;
	}

	public List<String> getInsertObj() {
		return insertObj;
	}

	public void setInsertObj(List<String> insertObj) {
		this.insertObj = insertObj;
	}

	public List<String[]> getOldList() {
		return oldList;
	}

	public void setOldList(List<String[]> oldList) {
		this.oldList = oldList;
	}
}
