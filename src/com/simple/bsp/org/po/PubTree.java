/**
 * 
 */
package com.simple.bsp.org.po;

import java.io.Serializable;

/**
 * @author simple
 * 
 */
public class PubTree implements Serializable {

	private static final long serialVersionUID = -8103250022422196134L;
	private String id; // treeID
	private String treeName; // 名称
	private String parentId; // 父节点
	private String deep; //
	private String isParent; // 是否父节点

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

}
