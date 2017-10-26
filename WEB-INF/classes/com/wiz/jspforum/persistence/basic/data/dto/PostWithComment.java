
package com.wiz.jspforum.persistence.basic.data.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="V_POST_HAS_COMMENT")
public class PostWithComment implements Serializable {

	private int id = 0;
	private long num = 0;

	public String toString() {
		return "id = " + id + ", num = " + num;
	}

	@Id
    @Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="NUM")
	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}
}
