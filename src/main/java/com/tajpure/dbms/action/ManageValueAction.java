package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Table;

public class ManageValueAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String insert() {
		List<String> insertObjList = StrArrListToStrList(insertObj);
		if (insertObjList.size() == 0 || isContentNull(insertObjList)) {
			return "failure";
		}
		DatabaseMetaDataWorker worker = factory.getWorker();
		worker.insertValue(curTable, insertObjList);
		worker.drop();
		return "success";
	}
	
	public String update() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		worker.updateValues(curTable, ListToNestingList(oldList), ListToNestingList(newList));
		worker.drop();
		return "success";
	}
	
	public String delete() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		worker.deleteValue(curTable, getListByIndexArr(delIndexStr));
		worker.drop();
		return "success";
	}
	
	public boolean isContentNull(List<String> strList) {
		StringBuilder sb = new StringBuilder();
		for (String str : strList) {
			sb.append(str);
		}
		if (sb.toString().replaceAll(" ", "").length() == 0) {
			return true;
		}
		return false;
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
	
	public List<String> StrArrListToStrList(List<String[]> strArrList) {
		List<String> strList = new ArrayList<String>();
		for (String[] strArr : strArrList) {
			strList.add(StringArrayToString(strArr));
		}
		return strList;
	}
	
	public List<List<Object>> getListByIndexArr(String indexStr) { 
		List<List<Object>> delList = new ArrayList<List<Object>>();
		List<List<Object>> oldList = ListToNestingList(this.oldList);
		String[] indexArr = getIndexArr(indexStr);
		for (String index : indexArr) {
			if ("undefined".equals(index)) {
				continue;
			}
			delList.add(oldList.get(Integer.parseInt(index)));
		}
		return delList;
	}
	
	private String[] getIndexArr(String indexStr) {
		if (indexStr == null || "".equals(indexStr)) return null;
		String[] indexArr = indexStr.split("o");
		return indexArr;
	}

	private List<String[]> newList = new ArrayList<String[]>();

	private List<String[]> oldList = new ArrayList<String[]>();
	
	private List<String[]> insertObj = new ArrayList<String[]>();
	
	private String delIndexStr;
	
	private Table curTable = new Table();
	
	private int page;
	
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

	public List<String[]> getInsertObj() {
		return insertObj;
	}

	public void setInsertObj(List<String[]> insertObj) {
		this.insertObj = insertObj;
	}

	public List<String[]> getOldList() {
		return oldList;
	}

	public void setOldList(List<String[]> oldList) {
		this.oldList = oldList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getDelIndexStr() {
		return delIndexStr;
	}

	public void setDelIndexStr(String delIndexStr) {
		this.delIndexStr = delIndexStr;
	}
}
