package com.tajpure.dbms.action;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Schema;

public class ManageSchemaAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String execute() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (curSchema == null || curSchema.getName() == null) {
			curSchema = worker.getSchema(schemaName);
		}
		if (schemas == null) {	// TODO Don't update schemas tree
			schemas = worker.getSchemas();
		}
		worker.drop();
		return "success";
	}
	
	public String insert() {
		return "";
	}
	
	public String update() {
		return "";
	}
	
	public String delete() {
		return "";
	}

	private List<Schema> schemas = null;
	
	private Schema curSchema = null;
	
	private String schemaName;

	public List<Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}

	public Schema getCurSchema() {
		return curSchema;
	}

	public void setCurSchema(Schema curSchema) {
		this.curSchema = curSchema;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}
